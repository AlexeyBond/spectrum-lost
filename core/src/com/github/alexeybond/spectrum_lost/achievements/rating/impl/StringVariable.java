package com.github.alexeybond.spectrum_lost.achievements.rating.impl;

import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;

import java.util.NoSuchElementException;

/**
 *
 */
public class StringVariable implements IRatingVariable {
    private String val;

    public StringVariable(final String val) {
        this.val = val;
    }
    @Override
    public IRatingVariable getV(String name) {
        throw new NoSuchElementException();
    }

    @Override
    public double getN() {
        throw new NoSuchElementException();
    }

    @Override
    public String getS() {
        return val;
    }
}
