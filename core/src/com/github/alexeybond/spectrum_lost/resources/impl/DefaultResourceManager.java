package com.github.alexeybond.spectrum_lost.resources.impl;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.resources.IMusicPlayer;
import com.github.alexeybond.spectrum_lost.resources.IResourceManager;
import com.github.alexeybond.spectrum_lost.resources.ISoundVariants;
import com.github.alexeybond.spectrum_lost.resources.json.PreloadConfig;

import java.util.*;

/**
 *
 */
public class DefaultResourceManager implements IResourceManager {
    private final static long MIN_LOADING_TIME = 500;

    private AssetManager assetManager;

    // true if there are resources that are not loaded yet
    private boolean isInProgress = false;
    private long progressStartTime;

    private List<TextureAtlas> atlases = new LinkedList<TextureAtlas>();
    private Queue<String> atlasesQueue = new Queue<String>();
    private Map<String, TextureRegionReference> cachedRegions = new HashMap<String, TextureRegionReference>();

    private MusicPlayerImpl musicPlayer = new MusicPlayerImpl();

    private Map<String, SoundVariantsProxy> soundVariantsProxies = new HashMap<String, SoundVariantsProxy>();
    private Map<PreloadConfig, Map<String, ISoundVariants>> realSoundVariants = new HashMap<PreloadConfig, Map<String, ISoundVariants>>();

    private PreloadConfig lastNewPreloadConfig = null;

    @Override
    public void init() {
        assetManager = new AssetManager();
    }

    @Override
    public TextureRegion getSprite(String name) {
        if (cachedRegions.containsKey(name)) {
            return cachedRegions.get(name);
        }

        TextureRegionReference reference = new TextureRegionReference();

        if (pickRegionFor(reference, name) || isInProgress) {
            cachedRegions.put(name, reference);
            return reference;
        }

        throw new NoSuchElementException("No such sprite: ".concat(name));
    }

    @Override
    public ISoundVariants getSoundsFor(String eventName) {
        SoundVariantsProxy soundVariants = soundVariantsProxies.get(eventName);

        if (null == soundVariants) {
            soundVariants = new SoundVariantsProxy();
            soundVariantsProxies.put(eventName, soundVariants);
        }

        return soundVariants;
    }

    @Override
    public IMusicPlayer getMusicPlayer() {
        return musicPlayer;
    }

    @Override
    public void preloadAtlas(String path) {
        assetManager.load(path, TextureAtlas.class);
        atlasesQueue.addLast(path);
        onResourceAdded();
    }

    private void preloadMusic(String path) {
        assetManager.load(path, Music.class);
        onResourceAdded();
    }

    private void preloadSoundEvents(Map<String, String[]> events) {
        for (Map.Entry<String, String[]> entry : events.entrySet()) {
            for (String sound : entry.getValue()) {
                assetManager.load(sound, Sound.class);
                onResourceAdded();
            }
        }
    }

    @Override
    public void preload(PreloadConfig config) {
        if (null != lastNewPreloadConfig)
            throw new IllegalStateException("New preload config added before previous config load completed.");

        for (String atlasPath : config.atlases)
            preloadAtlas(atlasPath);

        if (null != config.music)
            preloadMusic(config.music);

        preloadSoundEvents(config.soundEvents);

        lastNewPreloadConfig = config;
    }

    @Override
    public void unload(PreloadConfig config) {
        // TODO: Implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadAll() {
        assetManager.finishLoading();
        afterDoneLoading();
    }

    @Override
    public boolean loadNext() {
        boolean result = assetManager.update();

        if (result) {
            afterDoneLoading();
        } else if (!isInProgress) {
            isInProgress = true;
            progressStartTime = TimeUtils.millis();
        }

        return result && (TimeUtils.millis() - progressStartTime) >= MIN_LOADING_TIME;
    }

    @Override
    public float getLoadProgress() {
        float minProgress = ((float) (TimeUtils.millis() - progressStartTime)) / ((float) MIN_LOADING_TIME);
        float realProgress = assetManager.getProgress();
        return Math.min(minProgress, realProgress);
    }

    @Override
    public void shutdown() {
        assetManager.dispose();
    }

    private void afterDoneLoading() {
        if (!isInProgress) return;
        isInProgress = false;

        while (atlasesQueue.size != 0) {
            String atlasName = atlasesQueue.removeFirst();
            TextureAtlas atlas = assetManager.get(atlasName, TextureAtlas.class);

            atlases.add(0, atlas);

            for (Map.Entry<String, TextureRegionReference> entry : cachedRegions.entrySet()) {
                TextureRegion newReg = atlas.findRegion(entry.getKey());

                if (newReg != null) {
                    entry.getValue().setReference(newReg, atlas);
                }
            }
        }

        if (null != lastNewPreloadConfig.music) {
            musicPlayer.pushMusic(lastNewPreloadConfig.music,
                    assetManager.get(lastNewPreloadConfig.music, Music.class));
        }

        Map<String, ISoundVariants> newVariants = new HashMap<String, ISoundVariants>();

        for (Map.Entry<String, String[]> entry :
                lastNewPreloadConfig.soundEvents.entrySet()) {
            List<Sound> sounds = new ArrayList<Sound>(entry.getValue().length);

            for (String sound : entry.getValue()) {
                sounds.add(assetManager.get(sound, Sound.class));
            }

            ISoundVariants realVariants = new SoundVariantsImpl(sounds);
            newVariants.put(entry.getKey(), realVariants);
            ((SoundVariantsProxy) getSoundsFor(entry.getKey())).addVariants(realVariants);
        }

        realSoundVariants.put(lastNewPreloadConfig, newVariants);

        lastNewPreloadConfig = null;
    }

    private boolean pickRegionFor(final TextureRegionReference reference, final String name) {
        for (TextureAtlas atlas : atlases) {
            TextureRegion region = atlas.findRegion(name);

            if (null != region) {
                reference.setReference(region, atlas);

                return true;
            }
        }

        return false;
    }

    private void onResourceAdded() {
        if (isInProgress) return;

        isInProgress = true;
        progressStartTime = TimeUtils.millis();
    }
}
