package com.github.alexeybond.spectrum_lost.client;

import com.badlogic.gdx.utils.Json;
import com.github.alexeybond.spectrum_lost.achievements.AchievementStatus;
import com.github.alexeybond.spectrum_lost.achievements.IAchievementsStorage;
import com.github.alexeybond.spectrum_lost.achievements.impl.StatusesList;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

/**
 *
 */
public class LocalStorageAchievementsStorage implements IAchievementsStorage {
    private Storage storage;
    private Json json;
    private StatusesList statusesList;

    private static final String STORAGE_KEY = "sl/achievements";

    @Override
    public void init() {
        storage = Storage.getLocalStorageIfSupported();

        if (storage == null) {
            Window.alert("Your browser does not support local storage. Your achievements will not be saved.");
            statusesList = new StatusesList();
            return;
        }

        json = new Json();

        String saved = storage.getItem(STORAGE_KEY);

        if (null != saved) {
            statusesList = json.fromJson(StatusesList.class, saved);
        } else {
            statusesList = new StatusesList();
        }
    }

    @Override
    public AchievementStatus get(String id) {
        AchievementStatus status = statusesList.achievements.get(id);

        if (status == null) {
            status = new AchievementStatus();
            statusesList.achievements.put(id, status);
        }

        return status;
    }

    @Override
    public void save() {
        if (null != storage) {
            storage.setItem(STORAGE_KEY, json.toJson(statusesList));
        }
    }
}
