package com.github.alexeybond.spectrum_lost.model.implementation;

import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.model.interfaces.*;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
final class CellImpl implements ICell {
    private final Ray[][] emit = initEmission();
    private final int x, y;
    private ICellType type;
    private ICellView view;
    private final GridImpl grid;
    private Direction direction;
    private Object state;
    private Map<String, Object> attributes;

    // Will be initialized by grid
    final CellImpl[] neighbours = new CellImpl[Direction.NUM];

    private static Ray[][] initEmission() {
        Ray[][] e = new Ray[2][Direction.NUM];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < Direction.NUM; j++) {
                e[i][j] = new Ray();
            }
        }

        return e;
    }

    CellImpl(final GridImpl grid, int x, int y, Direction direction, String type) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.direction = direction;
        this.state = null;
        this.attributes = new HashMap<String, Object>();
        setType(Locator.CELL_TYPES.get(type));
    }

    private Ray emission(Direction dir, int prev) {
        return emit[prev ^ grid.frameCounter & 1][dir.n];
    }

    @Override
    public int x() {
        return x;
    }

    @Override
    public int y() {
        return y;
    }

    @Override
    public Ray emission(Direction dir) {
        return emission(dir, 0);
    }

    void clearEmission() {
        for (int n = 0; n < Direction.NUM; n++) {
            emission(Direction.get(n)).clear();
        }
    }

    @Override
    public Ray receive(Direction dir) {
        return neighbours[dir.n].emission(dir.reverse(), 1);
    }

    @Override
    public Direction direction() {
        return this.direction;
    }

    @Override
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public Object state() {
        return this.state;
    }

    @Override
    public void setState(Object state) {
        this.state = state;
    }

    @Override
    public ICellType type() {
        return this.type;
    }

    @Override
    public void setType(ICellType type) {
        if (type == this.type)
            return;

        ICellView view;

        String viewOverride = (String) attributes.get("viewOverride");
        if (null == viewOverride) {
            view = Locator.CELL_VIEWS.get(type.id());
        } else {
            view = Locator.CELL_VIEWS.get(viewOverride);
        }

        if (null != this.type) {
            this.type.leave(this);
        }

        this.type = type;
        this.view = view;
        this.type.init(this);
    }

    @Override
    public ICellView getView() {
        return this.view;
    }

    @Override
    public Object getAttribute(String name) {
        Object val = attributes.get(name);

        if (null == val)
            return this.type.getAttribute(name);

        return val;
    }

    @Override
    public Map<String, Object> getOwnAttributes() {
        return attributes;
    }

    @Override
    public IGrid grid() {
        return grid;
    }
}
