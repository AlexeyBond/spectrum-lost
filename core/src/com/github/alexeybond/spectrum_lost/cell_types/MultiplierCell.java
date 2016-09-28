package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class MultiplierCell implements ICellType {
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
        Direction dir = cell.direction();

        Ray e = cell.emission(dir);

        e.set(cell.receive(dir.reverse()));

        e.mult(cell.receive(dir.perpendicular()));
        e.mult(cell.receive(dir.perpendicular().reverse()));
    }

    @Override
    public String id() {
        return "multiplier";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
