package com.github.alexeybond.spectrum_lost.model.interfaces;

/**
 * Represents a result expected by a game from a player.
 */
public interface IExpectation {
    /**
     * Notify a game if the expected result is reached by player or not.
     *
     * @throws IllegalStateException if this expectation is removed by {@link #remove()} call
     */
    void setDone(final boolean done);

    /**
     * True if the expectation is done ({@link #setDone(boolean)} was last time called with {@code true}).
     */
    boolean isDone();

    /**
     * Do not expect the result.
     */
    void remove();
}
