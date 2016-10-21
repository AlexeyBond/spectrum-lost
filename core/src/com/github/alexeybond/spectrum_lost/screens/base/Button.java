package com.github.alexeybond.spectrum_lost.screens.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 */
public class Button {
    private TextureRegion texture;
    boolean visible;
    private ButtonListener listener;
    Rectangle rect;

    Button()

    public int width() {
        return texture.getRegionWidth();
    }

    public int height() {
        return texture.getRegionHeight();
    }

    public void show(boolean show) {
        this.visible = show;
    }
}
