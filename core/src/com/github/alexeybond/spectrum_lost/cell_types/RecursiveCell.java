package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.achievements.AchievementStatus;
import com.github.alexeybond.spectrum_lost.achievements.Achievements;
import com.github.alexeybond.spectrum_lost.achievements.rating.IRatingVariable;
import com.github.alexeybond.spectrum_lost.achievements.rating.impl.NumberVariable;
import com.github.alexeybond.spectrum_lost.achievements.rating.impl.StringVariable;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

import java.util.NoSuchElementException;

/**
 *
 */
public class RecursiveCell implements ICellType {
    public static class State implements IRatingVariable {
        private AchievementStatus status;
        private boolean highlighted;
        private boolean error;
        private int nExpectRays;
        private Direction[] emit;

        public boolean isCompleted() {
            return status != null && status.isStarted();
        }

        public boolean isOpen() {
            return highlighted || isCompleted();
        }

        public boolean isError() {
            return error;
        }

        @Override
        public IRatingVariable getV(String name) {
            if ("completed".equals(name))
                return new StringVariable(String.valueOf(isCompleted()));

            if ("highlighted".equals(name))
                return new StringVariable(String.valueOf(highlighted));

            if ("rating".equals(name))
                return new NumberVariable(isCompleted()?status.getAchievedPoints():0);

            if ("fullyCompleted".equals(name))
                return new StringVariable(String.valueOf(isCompleted() && status.isCompleted()));

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
        State state = new State();

        String levelId = (String) cell.getAttribute("levelId");
        Number expect = (Number) cell.getAttribute("expectRays");

        state.nExpectRays = expect.intValue();

        if (levelId != null) {
            state.status = Achievements.get("level:".concat(levelId));
        } else {
            state.error = true;
        }

        cell.setState(state);
    }

    @Override
    public void leave(ICell cell) {
        cell.setState(null);
    }

    @Override
    public void update(ICell cell) {
        int nFoundRays = 0;

        for (int n = 0; n < Direction.NUM; n++) {
            Direction d = Direction.get(n);

            if (!cell.receive(d).isDark()) ++nFoundRays;
        }

        State state = (State) cell.state();

        state.highlighted = (nFoundRays >= state.nExpectRays);

        if (state.isCompleted()) {
            cell.emission(cell.direction())
                    .set(Ray.MAX_BRIGHTNESS, Ray.MAX_BRIGHTNESS, Ray.MAX_BRIGHTNESS);
        }
    }

    @Override
    public String id() {
        return "recursive";
    }

    @Override
    public Object getAttribute(String name) {
        if ("expectRays".equals(name))
            return 1;
        return null;
    }
}
