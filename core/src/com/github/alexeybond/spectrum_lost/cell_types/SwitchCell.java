package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

/**
 *
 */
public class SwitchCell extends MirrorCell {
    @Override
    public void init(ICell cell) {
        cell.setState(Boolean.FALSE);
    }

    @Override
    public void leave(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void update(ICell cell) {
        Boolean ns = !cell.receive(cell.direction().reverse()).isDark();

        if (ns != cell.state()) {
            cell.setState(ns);
        }

        Direction n = ns ? cell.direction().prev() : cell.direction().next();

        reflect(cell, n);
    }

    @Override
    public String id() {
        return "switch";
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }
}
