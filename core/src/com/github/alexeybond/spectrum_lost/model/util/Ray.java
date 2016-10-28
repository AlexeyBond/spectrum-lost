package com.github.alexeybond.spectrum_lost.model.util;

/**
 *
 */
public final class Ray {
    private int r, g, b;

    public Ray() {
        clear();
    }

    /** Brightness of red color */
    public int getR() {return r;}

    /** Brightness of green color */
    public int getG() {return g;}

    /** Brightness of blue color */
    public int getB() {return b;}

    /**
     * Set brightness of the ray.
     */
    public void set(int r, int g, int b) {
        this.r = clamp(r);
        this.g = clamp(g);
        this.b = clamp(b);
    }

    /**
     * Set brightness of the ray to brightness of given ray.
     */
    public void set(final Ray v) {
        r = v.r;
        g = v.g;
        b = v.b;
    }

    /**
     * Add brightness to current brightness of this ray.
     */
    public void add(final int ar, final int ag, final int ab) {
        set(r + ar, g + ag, b + ab);
    }

    /**
     * Add brightness of given ray to current brightness of this ray.
     */
    public void add(final Ray v) {
        add(v.r, v.g, v.b);
    }

    /**
     * "Multiply" this ray by another one.
     */
    public void mult(final Ray v) {
        set(Math.min(v.r, r), Math.min(v.g, g), Math.min(v.b, b));
    }

    /**
     * Reset all brightness components to 0.
     */
    public void clear() {
        r = g = b = 0;
    }

    /**
     * Decrease the brightness of the ray as if it has passed {@code n} cells.
     */
    public void fade(final int n) {
        final int amount = n * FADE_STEP;
        set(r - amount, g - amount, b - amount);
    }

    /**
     * Shift the colors.
     */
    public void shift() {
        int r_ = r;
        r = g; g = b; b = r_;
    }

    /**
     * Check if this ray represents darkness i.e. all brightness components are 0's.
     */
    public boolean isDark() {
        return (r == 0) && (g == 0) && (b == 0);
    }

    public int maxComponentBrightness() {
        int mcb = r;

        if (g > mcb) mcb = g;
        if (b > mcb) mcb = b;

        return mcb;
    }

    /** The maximal value of a brightness component */
    public static final int MAX_BRIGHTNESS = 16;

    /** How much each component of ray brightness fades when ray passes one cell */
    public static final int FADE_STEP = 1;

    public static int clamp(final int x) {
        if (x > MAX_BRIGHTNESS)
            return MAX_BRIGHTNESS;

        if (x < 0)
            return 0;

        return x;
    }
}
