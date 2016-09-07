package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * Wall. Some could say it does not look like a wall. I donut care.
 */
public class WallView extends $Sprite2DView {
    {
        bgTexture = loadTexture("cells/wall/bg.png");
        fgTexture = loadTexture("cells/wall/fg.png");
    }

    @Override
    public boolean ignoreBackground() {
        return true;
    }
}
