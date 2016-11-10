package com.github.alexeybond.spectrum_lost.resources.impl;

import com.github.alexeybond.spectrum_lost.resources.ISoundVariants;

import java.util.LinkedList;
import java.util.List;

/**
 * Sound variants Proxy delegates sound playing to re-l object if such object is present.
 */
public class SoundVariantsProxy implements ISoundVariants {
    private ISoundVariants real = null;
    private List<ISoundVariants> allVariants = new LinkedList<ISoundVariants>();

    @Override
    public void playRandom() {
        if (null != real) real.playRandom();
    }

    @Override
    public void play(int seed) {
        if (null != real) real.play(seed);
    }

    void addVariants(ISoundVariants variants) {
        allVariants.add(0, variants);
        real = variants;
    }

    void removeVariants(ISoundVariants variants) {
        allVariants.remove(variants);
        real = (allVariants.size() == 0) ? null : allVariants.get(0);
    }
}
