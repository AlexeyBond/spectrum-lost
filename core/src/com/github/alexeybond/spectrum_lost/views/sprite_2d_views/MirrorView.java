package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * View of a mirror.
 */
public class MirrorView extends $Sprite2DView {
    {
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/mirror/fg.png");
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
