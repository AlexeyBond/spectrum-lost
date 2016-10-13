package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

/**
 *
 */
public class $SimpleSpriteView extends $Sprite2DView {
    private final boolean flare;

    public $SimpleSpriteView(String bgTexture, String fgTexture, boolean flare) {
        this.bgTexture = loadTexture(bgTexture);
        this.fgTexture = loadTexture(fgTexture);
        this.flare = flare;
    }

    @Override
    public boolean enableFlare() {
        return flare;
    }
}
