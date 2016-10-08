package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;

/**
 *
 */
public class ClearCell implements ICellType {
    @Override
    public void init(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void leave(ICell cell) {
        // ...
    }

    @Override
    public void update(ICell cell) {
        // ...
    }

    @Override
    public String id() {
        return "clear";
    }

    @Override
    public Object getAttribute(String name) {
        if ("noTurn".equals(name)) {
            return true;
        }

        return null;
    }
}
