package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class FaderCell implements ICellType {
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
        for (int n = 0; n < Direction.NUM; n++) {
            Direction d = Direction.get(n);

            Ray e = cell.emission(d);
            Ray r = cell.receive(d.reverse());

            e.set(r);
            e.fade(6);
        }
    }

    @Override
    public String id() {
        return "fader";
    }

    @Override
    public Object getAttribute(String name) {
        // Do not let controller turn a cell
        if ("noTurn".equals(name))
            return true;

        return null;
    }
}
