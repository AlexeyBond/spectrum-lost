package com.github.alexeybond.spectrum_lost.achievements.rating.impl;

import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;

import java.util.NoSuchElementException;

/**
 *
 */
public class CellVariable implements IRatingVariable {
    private final ICell cell;

    public CellVariable(final ICell cell) {
        this.cell = cell;
    }

    @Override
    public IRatingVariable getV(String name) {
        if ("direction".equals(name)) {
            return new StringVariable(cell.direction().toString());
        }

        if ("type".equals(name)) {
            return new StringVariable(cell.type().id());
        }

        if ("state".equals(name)) {
            if (!(cell.state() instanceof IRatingVariable)) {
                throw new NoSuchElementException("Cell state is not a rating variable.");
            }

            return (IRatingVariable) cell.state();
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
