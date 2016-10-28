package com.github.alexeybond.spectrum_lost.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
     * TODO: Implement the following:
     * Resource manager returns a texture region that is a wrapper around real region so if a atlas is unloaded internal
     * reference of that wrapper may be reassigned to region from another atlas.
     *
     * @param name    name of the texture region
     */
    TextureRegion getSprite(String name);

    /**
     *
     *
     * @param path    path to atlas file
     * TODO: Define format of paths for external files.
     */
    void preloadAtlas(String path);

    /**
     * Unload the atlas loaded from given path.
     */
    void unloadAtlas(String path);

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
