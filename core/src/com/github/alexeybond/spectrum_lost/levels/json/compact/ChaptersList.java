package com.github.alexeybond.spectrum_lost.levels.json.compact;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

/**
 * List of all chapters available in the game.
 */
public class ChaptersList {
    public ArrayList<CompactChapterDesc> chapters = new ArrayList<CompactChapterDesc>();

    public static ChaptersList readFrom(FileHandle baseDir) {
        Json json = new Json();
        return json.fromJson(ChaptersList.class, baseDir.child("chapters.json"));
    }
}
