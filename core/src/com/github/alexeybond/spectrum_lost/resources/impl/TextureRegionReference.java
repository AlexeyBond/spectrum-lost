package com.github.alexeybond.spectrum_lost.resources.impl;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 */
class TextureRegionReference extends TextureRegion {
//    private TextureRegion reference;
    private TextureAtlas atlas;

    void setReference(TextureRegion reg, TextureAtlas atlas) {
        this.atlas = atlas;
//        this.reference = reg;

        this.setRegion(reg);
    }

    TextureAtlas getAtlas() {
        return atlas;
    }
}
