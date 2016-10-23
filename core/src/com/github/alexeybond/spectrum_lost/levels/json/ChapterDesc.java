package com.github.alexeybond.spectrum_lost.levels.json;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description of a chapter (group of levels).
 */
public class ChapterDesc {
    public ArrayList<String> levelNames;
    public String rootLevel;
    public HashMap<String, Object> attrs;
    public ArrayList<String> afterChapters = new ArrayList<String>();
}
