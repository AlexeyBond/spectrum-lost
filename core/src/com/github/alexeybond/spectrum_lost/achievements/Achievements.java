package com.github.alexeybond.spectrum_lost.achievements;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Achievements.
 */
public class Achievements {
    private static class StatusesList {
        private HashMap<String, AchievementStatus> achievements = new HashMap<String, AchievementStatus>();
    }

    private static StatusesList statusesList;
    private static Json json;

    private static FileHandle mainFileHandle;
    private static FileHandle backupFileHandle;

    public static void init() {
        try {
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
        } catch (Exception e) {
            statusesList = new StatusesList();
            json = null;
            Gdx.app.setLogLevel(Application.LOG_INFO);
            Gdx.app.log("[ACHIEVEMENTS]", "Could not access achievements files.");
        }
    }

    public static void save() {
        if (null != json) {
            String js = json.toJson(statusesList);
            backupFileHandle.writeString(js, false);
            mainFileHandle.writeString(js, false);
        }
    }

    public static AchievementStatus get(String id) {
        AchievementStatus status = statusesList.achievements.get(id);

        if (status == null) {
            status = new AchievementStatus();
            statusesList.achievements.put(id, status);
        }

        return status;
    }
}
