package com.github.alexeybond.spectrum_lost.resources.json;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Descriptions of resources that should be pre-loaded for a game (or for separate chapter/level).
 */
public class PreloadConfig {
    /**
     * Map from sound event name to array of sound variants for that event.
     */
    public HashMap<String, ArrayList<String>> soundEvents = new HashMap<String, ArrayList<String>>();

    /**
     * Path to music file to play.
     */
    public String music = null;

    /**
     * List of texture atlases to preload.
     */
    public ArrayList<String> atlases = new ArrayList<String>();
}
