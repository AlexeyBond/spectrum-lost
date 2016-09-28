package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.interfaces.IExpectation;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class ExpectorCell implements ICellType {
    public static class State {
        private static final int nChargeTicks = 2 * Ray.MAX_BRIGHTNESS / Ray.FADE_STEP;
        public final IExpectation expectation;

        private int nFramesCharging;

        State(IExpectation expectation) {
            this.expectation = expectation;
            nFramesCharging = 0;
        }

        public float getCharge() {
            if (0 >= nFramesCharging) return 0;
            return ((float) nFramesCharging) / ((float) nChargeTicks);
        }

        void update(boolean solved) {
            if (!solved) nFramesCharging = 0;
            if (expectation.isDone() && !solved) {
                expectation.setDone(false);
                nFramesCharging = 0;
            } else if (solved && !expectation.isDone()) {
                if (nChargeTicks <= ++nFramesCharging) {
                    expectation.setDone(true);
                }
            }
        }

        void dispose() {
            expectation.remove();
        }
    }

    private final int kr, kg, kb, kz;
    private final String id;

    private ExpectorCell(final String id, final int kr, final int kg, final int kb, final int kz) {
        this.id = id;
        this.kr = kr;
        this.kg = kg;
        this.kb = kb;
        this.kz = kz;
    }

    @Override
    public void init(ICell cell) {
        IExpectation expectation = cell.grid().getGameState().addExpectation();
        cell.setState(new State(expectation));
    }

    @Override
    public void leave(ICell cell) {
        State state = (State) cell.state();
        state.dispose();
        cell.setState(null);
    }

    private boolean isExpectationDone(final ICell cell) {
        int r = 0, g = 0, b = 0;
        for (int n = 0; n < Direction.NUM; n++) {
            Ray ray = cell.receive(Direction.get(n));
            r += ray.getR();
            g += ray.getG();
            b += ray.getB();
        }

        return ((r==0?0:kr) + (g==0?0:kg) + (b==0?0:kb) + kz) > 0;
    }

    @Override
    public void update(ICell cell) {
        State state = (State)cell.state();
        state.update(isExpectationDone(cell));
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Object getAttribute(String name) {
        if ("noTurn".equals(name))
            return true;

        return null;
    }

    public static ICellType ANY = new ExpectorCell("expector", 1, 1, 1, 0);
    public static ICellType NONE = new ExpectorCell("expector:none", -1, -1, -1, 1);
}
