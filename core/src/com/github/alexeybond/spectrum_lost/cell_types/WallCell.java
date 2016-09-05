package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;

/**
 *
 */
public class WallCell implements ICellType {
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
        // do nothing
    }

    @Override
    public String id() {
        return "wall";
    }

    @Override
    public Object getAttribute(String name) {
        if ("noTurn".equals(name))
            return true;

        return null;
    }
}
