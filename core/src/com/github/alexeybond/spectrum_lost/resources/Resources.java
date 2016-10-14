package com.github.alexeybond.spectrum_lost.resources;

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

    public static TextureRegion getSprite(final String name) {
        for (TextureAtlas atlas : atlasList) {
            TextureRegion region = atlas.findRegion(name);

            if (null != region) return region;
        }

        throw new NoSuchElementException("No such sprite: ".concat(name));
    }

    public static void use(final TextureAtlas atlas) {
        atlasList.add(atlas);
    }
}
