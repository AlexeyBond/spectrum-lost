package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class ShifterCell implements ICellType {
    @Override
    public void init(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void leave(ICell cell) {
        // Do nothing.
    }

    private void shift(final ICell cell, final Direction from, final Direction to) {
        Ray r = cell.emission(to);
        r.set(cell.receive(from));
        r.shift();
        r.fade(1);
    }

    @Override
    public void update(ICell cell) {
        Direction a = cell.direction().perpendicular();
        Direction b = a.reverse();

        shift(cell, a, b);
        shift(cell, b, a);
    }

    @Override
    public String id() {
        return "shifter";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
