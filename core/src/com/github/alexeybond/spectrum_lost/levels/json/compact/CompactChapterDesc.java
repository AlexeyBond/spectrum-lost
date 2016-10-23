package com.github.alexeybond.spectrum_lost.levels.json.compact;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Short description of a chapter. Does not include list of levels or level files.
 */
public class CompactChapterDesc {
    public String id;
    public HashMap<String, Object> attrs;
    public ArrayList<String> after;

    public ChapterLevelsDesc readLevels(FileHandle baseDir) {
        Json json = new Json();
        return json.fromJson(
                ChapterLevelsDesc.class,
                baseDir.child("levels-".concat(id).concat(".json")));
    }
}
