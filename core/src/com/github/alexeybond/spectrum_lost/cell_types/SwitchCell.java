package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class SwitchCell extends MirrorCell {
    public static class State {
        private final static long framesToTurn = Ray.MAX_BRIGHTNESS / Ray.FADE_STEP;

        public boolean isTurned = false;

        public float fChanged() {
            if (nTurning == -1) return 0;
            return ((float) nTurning) / ((float) framesToTurn);
        }

        private long nTurning = -1;

        void turnTo(boolean turn) {
            if (turn == isTurned) {
                nTurning = -1;
                return;
            }

            if (nTurning >= framesToTurn) {
                isTurned = turn;
                nTurning = -1;
                return;
            }

            ++nTurning;
        }
    }

    @Override
    public void init(ICell cell) {
        cell.setState(new State());
    }

    @Override
    public void leave(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void update(ICell cell) {
        Boolean ns = !cell.receive(cell.direction().reverse()).isDark();
        State state = (State) cell.state();

        state.turnTo(ns);

        Direction n = state.isTurned ? cell.direction().prev() : cell.direction().next();

        reflect(cell, n);
    }

    @Override
    public String id() {
        return "switch";
    }

    @Override
    public Object getAttribute(String name) {
        if ("noTurn".equals(name) || "editorTurn".equals(name))
            return true;

        return null;
    }
}
