package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class MirrorCell implements ICellType {
    @Override
    public void init(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void leave(ICell cell) {
        // do nothing
    }

    private void reflect0(final ICell cell, final Direction from, final Direction to) {
        Ray r = cell.receive(from);
        Ray e = cell.emission(to);

        e.set(r);
        e.fade(1);
    }

    private void reflect(final ICell cell, final Direction norm) {
        Direction a = norm.next(), b = norm.prev();
        reflect0(cell, a, b);
        reflect0(cell, b, a);
        reflect0(cell, norm, norm);
    }

    @Override
    public void update(ICell cell) {
        reflect(cell, cell.direction());
        reflect(cell, cell.direction().reverse());
    }

    @Override
    public String id() {
        return "mirror";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
