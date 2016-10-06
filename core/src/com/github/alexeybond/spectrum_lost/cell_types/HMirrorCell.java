package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 * Half-transparent mirror.
 */
public class HMirrorCell implements ICellType {
    private static final Ray acc = new Ray();

    @Override
    public void init(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void leave(ICell cell) {
        //
    }

    private Ray half1(Ray src, ICell cell) {
        acc.set(src.getR() >> 1, src.getG() >> 1, src.getB() >> 1);
        return acc;
    }

    private Ray half2(Ray src, ICell cell) {
        return half1(src, cell);
    }

    private void hreflect0(ICell cell, Direction from, Direction to) {
        Direction to2 = from.reverse();

        Ray e = cell.receive(from);

        cell.emission(to).add(half1(e, cell));
        cell.emission(to2).add(half2(e, cell));
    }

    private void hreflect(ICell cell, Direction norm) {
        Direction a = norm.next(), b = norm.prev();
        hreflect0(cell, a, b);
        hreflect0(cell, b, a);
        hreflect0(cell, norm, norm);
    }

    @Override
    public void update(ICell cell) {
        hreflect(cell, cell.direction());
        hreflect(cell, cell.direction().reverse());
    }

    @Override
    public String id() {
        return "hmirror";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
