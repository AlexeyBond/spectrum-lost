package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * View of HMirror cell.
 */
public class HMirrorView extends $Sprite2DView {
    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/hmirror/fg.png");
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
