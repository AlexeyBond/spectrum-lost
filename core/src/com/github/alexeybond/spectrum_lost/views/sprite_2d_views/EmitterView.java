package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 * View for emitter cell.
 */
public class EmitterView extends $Sprite2DView {
    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/emitter/fg.png");
    }
}
