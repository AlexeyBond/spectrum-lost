package com.github.alexeybond.spectrum_lost.resources.impl;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Queue;
import com.github.alexeybond.spectrum_lost.resources.IResourceManager;

import java.util.*;

/**
 *
 */
public class DefaultResourceManager implements IResourceManager {
    private AssetManager assetManager;

    // true if there are resources that are not loaded yet
    private boolean isInProgress = false;

    private List<TextureAtlas> atlases = new LinkedList<TextureAtlas>();
    private Queue<String> atlasesQueue = new Queue<String>();
    private Map<String, TextureRegionReference> cachedRegions = new HashMap<String, TextureRegionReference>();

    @Override
    public void init() {
        assetManager = new AssetManager();
    }

    @Override
    public TextureRegion getSprite(String name) {
        TextureRegionReference reference = new TextureRegionReference();

        if (isInProgress || pickRegionFor(reference, name)) {
            cachedRegions.put(name, reference);
            return reference;
        }

        throw new NoSuchElementException("No such sprite: ".concat(name));
    }

    @Override
    public void preloadAtlas(String path) {
        assetManager.load(path, TextureAtlas.class);
        atlasesQueue.addLast(path);
    }

    @Override
    public void unloadAtlas(String path) {
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
        }

        return result;
    }

    @Override
    public float getLoadProgress() {
        return assetManager.getProgress();
    }

    @Override
    public void shutdown() {
        assetManager.dispose();
    }

    private void afterDoneLoading() {
        isInProgress = false;

        while (atlasesQueue.size != 0) {
            String atlasName = atlasesQueue.removeFirst();
            TextureAtlas atlas = assetManager.get(atlasName, TextureAtlas.class);

            atlases.add(0, atlas);

            for (Map.Entry<String, TextureRegionReference> entry : cachedRegions.entrySet()) {
                TextureRegion newReg = atlas.findRegion(entry.getKey());

                entry.getValue().setReference(newReg, atlas);
            }
        }
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
}
