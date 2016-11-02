package com.github.alexeybond.spectrum_lost.achievements.rating.impl;

import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;

import java.util.NoSuchElementException;

/**
 *
 */
public class NumberVariable implements IRatingVariable {
    private double val;

    public NumberVariable(final double val) {
        this.val = val;
    }

    @Override
    public IRatingVariable getV(String name) {
        throw new NoSuchElementException();
    }

    @Override
    public double getN() {
        return val;
    }

    @Override
    public String getS() {
        throw new NoSuchElementException();
    }
}
