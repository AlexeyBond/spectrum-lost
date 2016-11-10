package com.github.alexeybond.spectrum_lost.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.alexeybond.spectrum_lost.resources.json.PreloadConfig;

/**
 *
 */
public interface IResourceManager {
    /**
     * Initialize this resource manager.
     */
    void init();

    /**
     * Get texture region with given name. When region with given name is available in multiple atlases the region from
     * the last loaded atlas will be returned.
     *
     * Resource manager returns a texture region that is a proxy to real region so if a atlas is unloaded internal
     * reference of that wrapper may be reassigned to region from another atlas.
     *
     * @param name    name of the texture region
     */
    TextureRegion getSprite(String name);

    /**
     * Get {@link ISoundVariants} instance that will play sounds for specified event.
     *
     * @param eventName    name of event
     */
    ISoundVariants getSoundsFor(String eventName);

    IMusicPlayer getMusicPlayer();

    /**
     *
     *
     * @param path    path to atlas file
     * TODO: Define format of paths for external files.
     */
    @Deprecated
    void preloadAtlas(String path);

    /**
     * Load resources described in given config.
     */
    void preload(PreloadConfig config);

    /**
     * Unload resources described in given config.
     */
    void unload(PreloadConfig config);

    /**
     * Blocking load all resources.
     *
     * TODO: Deprecate when loading screen will be done (?)
     */
    void loadAll();

    /**
     * Perform next iteration of resource loading.
     */
    boolean loadNext();

    /**
     * Get load progress (in range [0f,1.f]).
     */
    float getLoadProgress();

    /**
     *
     */
    void shutdown();
}
