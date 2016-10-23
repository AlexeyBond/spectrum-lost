package com.github.alexeybond.spectrum_lost.achievements;

/**
 *
 */
public interface IAchievementsStorage {
    void init();

    AchievementStatus get(String id);

    void save();
}
