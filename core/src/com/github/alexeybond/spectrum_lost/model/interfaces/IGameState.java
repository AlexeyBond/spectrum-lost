package com.github.alexeybond.spectrum_lost.model.interfaces;

/**
 *
 */
public interface IGameState {
    /**
     * Create new expectation.
     */
    IExpectation addExpectation();

    /**
     * True if all expected results (if any) are reached by player.
     */
    boolean isCompleted();
}
