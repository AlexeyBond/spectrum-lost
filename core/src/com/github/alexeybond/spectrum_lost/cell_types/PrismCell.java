package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 * Prism.
 */
public class PrismCell implements ICellType {
    @Override
    public void init(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void leave(ICell cell) {
        // do nothing
    }

    @Override
    public void update(ICell cell) {
        Ray r = cell.receive(cell.direction().reverse());

        // Green goes straight
        Ray e = cell.emission(cell.direction());
        e.set(0, r.getG(), 0);
        e.fade(1);

        // Blue turns one way
        e = cell.emission(cell.direction().next());
        e.set(0, 0, r.getB());
        e.fade(1);

        // Red turns another
        e = cell.emission(cell.direction().prev());
        e.set(r.getR(), 0, 0);
        e.fade(1);
    }

    @Override
    public String id() {
        return "prism";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
