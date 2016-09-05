package com.github.alexeybond.spectrum_lost.model.util;

/**
 *
 */
public enum Direction {
    UP_LF(7, -1, 1),            UP(0, 0, 1),            UP_RT(1, 1, 1),
    LF(6, -1, 0),                                       RT(2, 1, 0),
    DN_LF(5, -1, -1),           DN(4, 0, -1),           DN_RT(3, 1, -1);

    public final int h, v, n;
    public final double a;

    Direction(int n, int h, int v) {
        this.n = n;
        this.h = h;
        this.v = v;
        this.a = Math.PI * .25 * (float)n;
    }

    public Direction next(final int turns) {
        return _all[(n + turns) & 0x7];
    }

    public Direction next() {
        return next(1);
    }

    public Direction prev() {
        return next(-1);
    }

    public Direction reverse() {
        return next(4);
    }

    public Direction perpendicular() {
        return next(2);
    }

    private static final Direction[] _all = new Direction[] {UP, UP_RT, RT, DN_RT, DN, DN_LF, LF, UP_LF};

    public static final int NUM = 8;
    public static final Direction DEFAULT = UP;

    public static Direction get(final int n) {
        return _all[n];
    }
}
