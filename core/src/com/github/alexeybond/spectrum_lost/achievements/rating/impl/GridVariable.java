package com.github.alexeybond.spectrum_lost.achievements.rating.impl;

import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;

import java.util.NoSuchElementException;

/**
 *
 */
public class GridVariable implements IRatingVariable {
    private final IGrid grid;

    public GridVariable(final IGrid grid) {
        this.grid = grid;
    }

    @Override
    public IRatingVariable getV(String name) {

        String[] spl = name.split(";");

        if (spl.length == 2) {
            int x = Integer.parseInt(spl[0]);
            int y = Integer.parseInt(spl[1]);

            return new CellVariable(grid.getCell(x,y));
        }

        throw new NoSuchElementException(name);
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
