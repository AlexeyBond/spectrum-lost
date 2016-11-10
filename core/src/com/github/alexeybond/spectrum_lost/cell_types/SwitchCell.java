package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;
import com.github.alexeybond.spectrum_lost.achievements.rating.impl.NumberVariable;
import com.github.alexeybond.spectrum_lost.achievements.rating.impl.StringVariable;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

import java.util.NoSuchElementException;

/**
 *
 */
public class SwitchCell extends MirrorCell {
    public static class State implements IRatingVariable {
        private final static long framesToTurn = Ray.MAX_BRIGHTNESS / (Ray.FADE_STEP * 2);

        public boolean isTurned = false;

        private int timesTurned = 0;

        public float fChanged() {
            if (nTurning == -1) return 0;
            return ((float) nTurning) / ((float) framesToTurn);
        }

        private long nTurning = -1;

        boolean turnTo(boolean turn) {
            if (turn == isTurned) {
                nTurning = -1;
                return false;
            }

            if (nTurning >= framesToTurn) {
                isTurned = turn;
                ++timesTurned;
                nTurning = -1;
                return true;
            }

            ++nTurning;
            return false;
        }

        @Override
        public IRatingVariable getV(String name) {
            if ("timesTurned".equals(name)) {
                return new NumberVariable(timesTurned);
            }

            if ("isTurned".equals(name)) {
                return new StringVariable(isTurned?"true":"false");
            }

            throw new NoSuchElementException(name);
        }

        @Override
        public double getN() {
            throw new NoSuchElementException();
        }

        @Override
        public String getS() {
            throw new NoSuchElementException();
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

        if (state.turnTo(ns)) {
            cell.grid().emitEvent(cell,
                    ns?"switchTurnOn":"switchTurnOff",
                    null);
        }

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
