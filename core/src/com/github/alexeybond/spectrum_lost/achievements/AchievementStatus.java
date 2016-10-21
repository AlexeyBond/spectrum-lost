package com.github.alexeybond.spectrum_lost.achievements;

/**
 *
 */
public class AchievementStatus {
    private int ap = 0;
    private int mp = 1;

    public void set(int done, int max) {
        ap = done;
        mp = max;
    }

    public boolean isStarted() {
        return ap != 0;
    }

    public boolean isCompleted() {
        return ap >= mp;
    }

    public int getAchievedPoints() {
        return ap;
    }

    public int getMaximumPoints() {
        return mp;
    }
}
