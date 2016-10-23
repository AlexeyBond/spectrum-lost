package com.github.alexeybond.spectrum_lost.achievements;

/**
 * Achievements.
 */
public class Achievements {
    private static IAchievementsStorage storage;

    public static void use(IAchievementsStorage storage) {
        if (null == Achievements.storage) {
            Achievements.storage = storage;
            storage.init();
        }
    }

    public static void save() {
        storage.save();
    }

    public static AchievementStatus get(String id) {
        return storage.get(id);
    }
}
