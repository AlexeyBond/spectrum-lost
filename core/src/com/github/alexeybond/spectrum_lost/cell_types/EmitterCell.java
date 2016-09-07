package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 * Cell emitting a ray in single direction.
 */
public class EmitterCell implements ICellType {
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
        Ray emission = cell.emission(cell.direction());
        emission.set(Ray.MAX_BRIGHTNESS, Ray.MAX_BRIGHTNESS, Ray.MAX_BRIGHTNESS);
    }

    @Override
    public String id() {
        return "emitter";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
