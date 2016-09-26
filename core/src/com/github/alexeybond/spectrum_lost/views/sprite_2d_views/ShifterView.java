package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * View of a shifter cell.
 */
public class ShifterView extends $Sprite2DView {
    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/shifter/fg.png");
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
