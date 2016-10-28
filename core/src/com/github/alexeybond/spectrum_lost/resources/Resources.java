package com.github.alexeybond.spectrum_lost.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 */
public class Resources {
    private static List<TextureAtlas> atlasList = new ArrayList<TextureAtlas>();

    private static AssetManager assetManager = new AssetManager();

    static {
        Texture.setAssetManager(assetManager);
    }

    public static TextureRegion getSprite(final String name) {
        for (TextureAtlas atlas : atlasList) {
            TextureRegion region = atlas.findRegion(name);

            if (null != region) return region;
        }

        throw new NoSuchElementException("No such sprite: ".concat(name));
    }

    public static void useAtlas(final String atlasName) {
        atlasList.add(new TextureAtlas(atlasName));
    }

    public static void awaitAll() {
        assetManager.finishLoading();
    }
}
