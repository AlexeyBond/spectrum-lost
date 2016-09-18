package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class MixerCell implements ICellType {
    @Override
    public void init(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void leave(ICell cell) {
        // --
    }

    @Override
    public void update(ICell cell) {
        Direction dir = cell.direction();
        Ray e = cell.emission(dir);

        dir = dir.next(2);
        e.add(cell.receive(dir));
        dir = dir.next(2);
        e.add(cell.receive(dir));
        dir = dir.next(2);
        e.add(cell.receive(dir));
    }

    @Override
    public String id() {
        return "mixer";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
