package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 *
 */
public class MultiplierView extends $Sprite2DView {
    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/multiplier/fg.png");
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
