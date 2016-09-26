package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * View of fader cell.
 */
public class FaderView extends $Sprite2DView {
    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/fader/fg.png");
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
