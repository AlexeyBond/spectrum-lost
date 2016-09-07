package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.interfaces.IExpectation;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

/**
 *
 */
public class ExpectorCell implements ICellType {
    @Override
    public void init(ICell cell) {
        IExpectation expectation = cell.grid().getGameState().addExpectation();
        cell.setState(expectation);
    }

    @Override
    public void leave(ICell cell) {
        IExpectation expectation = (IExpectation)cell.state();
        expectation.remove();
        cell.setState(null);
    }

    private boolean isExpectationDone(final ICell cell) {
        for (int n = 0; n < Direction.NUM; n++) {
            if (!cell.receive(Direction.get(n)).isDark())
                return true;
        }

        return false;
    }

    @Override
    public void update(ICell cell) {
        IExpectation expectation = (IExpectation)cell.state();
        expectation.setDone(isExpectationDone(cell));
    }

    @Override
    public String id() {
        return "expector";
    }

    @Override
    public Object getAttribute(String name) {
        if ("noTurn".equals(name))
            return true;

        return null;
    }
}
