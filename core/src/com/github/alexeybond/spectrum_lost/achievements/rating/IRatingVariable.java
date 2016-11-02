package com.github.alexeybond.spectrum_lost.achievements.rating;

/**
 * Object describing (some part of) game result.
 */
public interface IRatingVariable {
    /**
     * @throws java.util.NoSuchElementException if there is no such nested variable
     */
    IRatingVariable getV(String name);

    /**
     * @throws java.util.NoSuchElementException if variable cannot be represented as a number
     */
    double getN();

    /**
     * @throws java.util.NoSuchElementException if variable cannot be represented as a string
     */
    String getS();
}
