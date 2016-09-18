package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * View of a mixer.
 */
public class MixerView extends $Sprite2DView {
    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/mixer/fg.png");
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
