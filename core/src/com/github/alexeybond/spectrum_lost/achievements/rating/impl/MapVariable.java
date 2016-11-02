package com.github.alexeybond.spectrum_lost.achievements.rating.impl;

import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 */
public class MapVariable implements IRatingVariable {
    private final Map<String, IRatingVariable> map = new HashMap<String, IRatingVariable>();

    public void add(final String name, final IRatingVariable var) {
        map.put(name, var);
    }

    @Override
    public IRatingVariable getV(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }

        throw new NoSuchElementException();
    }

    @Override
    public double getN() {
        throw new NoSuchElementException();
    }

    @Override
    public String getS() {
        throw new NoSuchElementException();
    }
}
