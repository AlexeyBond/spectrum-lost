package com.github.alexeybond.spectrum_lost.model.implementation;

import com.github.alexeybond.spectrum_lost.model.interfaces.IExpectation;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGameState;

/**
 * Implementation of {@link IGameState}.
 */
public class GameStateImpl implements IGameState {
    private int nExpectCount = 0;
    private int nExpectationsDone = 0;

    private class Expectation implements IExpectation {
        private boolean done = false;
        private boolean alive = true;

        {++nExpectCount;}

        @Override
        public void setDone(boolean done) {
            if (!alive) {
                throw new IllegalStateException("Expectation is removed.");
            }

            if (done != this.done) {
                nExpectationsDone = nExpectationsDone + (done?1:-1);
                this.done = done;
            }
        }

        @Override
        public boolean isDone() {
            return this.done;
        }

        @Override
        public void remove() {
            if (!alive) return;

            alive = false;

            if (isDone()) {
                --nExpectationsDone;
            }

            --nExpectCount;
        }
    }

    @Override
    public IExpectation addExpectation() {
        return new Expectation();
    }

    @Override
    public boolean isCompleted() {
        return (nExpectCount != 0) && (nExpectCount == nExpectationsDone);
    }
}
