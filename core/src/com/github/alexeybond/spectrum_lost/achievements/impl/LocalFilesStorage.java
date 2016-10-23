package com.github.alexeybond.spectrum_lost.achievements.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.alexeybond.spectrum_lost.achievements.AchievementStatus;
import com.github.alexeybond.spectrum_lost.achievements.IAchievementsStorage;

/**
 *
 */
public class LocalFilesStorage implements IAchievementsStorage {

    private StatusesList statusesList;
    private Json json;

    private FileHandle mainFileHandle;
    private FileHandle backupFileHandle;

    @Override
    public void init() {
        FileHandle dir = Gdx.files.external("spectrum-lost");
        dir.mkdirs();
        mainFileHandle = dir.child("achievements.json");
        backupFileHandle = dir.child("achievements.json~");
        json = new Json(JsonWriter.OutputType.minimal);

        try {
            statusesList = json.fromJson(StatusesList.class, mainFileHandle);
        } catch (Exception e) {
            try {
                statusesList = json.fromJson(StatusesList.class, backupFileHandle);
            } catch (Exception ee) {
                statusesList = new StatusesList();
                String js = json.toJson(statusesList);
                mainFileHandle.writeString(js, false);
                backupFileHandle.writeString(js, false);
            }
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
        if (null != json) {
            String js = json.toJson(statusesList);
            backupFileHandle.writeString(js, false);
            mainFileHandle.writeString(js, false);
        }
    }
}
