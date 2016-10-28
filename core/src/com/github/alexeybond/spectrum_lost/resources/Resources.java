package com.github.alexeybond.spectrum_lost.resources;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 */
public class Resources {
    private static IResourceManager resourceManager;

    public static TextureRegion getSprite(final String name) {
        return resourceManager.getSprite(name);
    }

    public static IResourceManager manager() {
        return resourceManager;
    }

    public static void use(IResourceManager manager) {
        if (null != resourceManager) {
            resourceManager.shutdown();
        }

        if (null != manager) {
            manager.init();
        }

        resourceManager = manager;
    }
}
