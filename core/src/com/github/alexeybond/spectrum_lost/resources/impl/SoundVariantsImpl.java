package com.github.alexeybond.spectrum_lost.resources.impl;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.github.alexeybond.spectrum_lost.resources.ISoundVariants;

import java.util.List;

/**
 *
 */
public class SoundVariantsImpl implements ISoundVariants {
    private final List<Sound> variants;

    SoundVariantsImpl(List<Sound> variants) {
        this.variants = variants;
    }

    @Override
    public void playRandom() {
        if (variants.size() == 0) return;
        variants.get(MathUtils.random(variants.size()-1)).play();
    }

    @Override
    public void play(int seed) {
        if (variants.size() == 0) return;
        variants.get(seed % variants.size()).play();
    }
}
