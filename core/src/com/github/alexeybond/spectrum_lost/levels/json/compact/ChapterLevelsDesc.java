package com.github.alexeybond.spectrum_lost.levels.json.compact;

import com.github.alexeybond.spectrum_lost.levels.json.GridDesc;

import java.util.LinkedHashMap;

/**
 * List of levels of a single chapter.
 */
public class ChapterLevelsDesc {
    // Map from level name to its description
    public LinkedHashMap<String, GridDesc> levels = new LinkedHashMap<String, GridDesc>();

    // Name of root level
    public String rootLevelName = "";
}
