package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class PulsarCell implements ICellType {
    private static int CAPASITY = Ray.MAX_BRIGHTNESS * 3 * 20;
    private static int DISCHARGE = Ray.MAX_BRIGHTNESS * 3 * 2;

    private final boolean keepCharge;
    private final String id;

    public static class State {
        public static class SubState {
            public boolean emitting = false;
            public int charge = 0;

            public float getCharge() {
                return ((float) charge) / ((float) CAPASITY);
            }
        }

        public final SubState direct = new SubState();
        public final SubState reverse = new SubState();
    }

    private PulsarCell(boolean keepCharge, String id) {
        this.keepCharge = keepCharge;
        this.id = id;
    }

    @Override
    public void init(ICell cell) {
        cell.setState(new State());
    }

    @Override
    public void leave(ICell cell) {
        cell.setState(null);
    }

    public void update(ICell cell, State.SubState subState, Direction srcDir, Direction dstDir) {
        Ray receive = cell.receive(srcDir);
        Ray emit = cell.emission(dstDir);

        if (!keepCharge && receive.isDark()) {
            subState.charge = 0;
        }

        subState.charge += receive.getR() + receive.getG() + receive.getB();

        if (subState.charge >= CAPASITY) {
            subState.emitting = true;
        }

        if (subState.emitting) {
            emit.set(Ray.MAX_BRIGHTNESS, Ray.MAX_BRIGHTNESS, Ray.MAX_BRIGHTNESS);

            subState.charge -= DISCHARGE;

            if (subState.charge <= 0) {
                subState.emitting = false;
                subState.charge = 0;
            }
        }
    }

    @Override
    public void update(ICell cell) {
        Direction d = cell.direction();
        Direction r = d.reverse();
        State state = (State) cell.state();

        update(cell, state.direct, r, d);
        update(cell, state.reverse, d, r);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    public final static ICellType TYPE_A = new PulsarCell(false, "pulsar");
    public final static ICellType TYPE_B = new PulsarCell(true, "pulsar-b");
}
