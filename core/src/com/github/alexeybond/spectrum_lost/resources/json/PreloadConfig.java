package com.github.alexeybond.spectrum_lost.resources.json;

import java.util.HashMap;

/**
 * Descriptions of resources that should be pre-loaded for a game (or for separate chapter/level).
 */
public class PreloadConfig {
    /**
     * Map from sound event name to array of sound variants for that event.
     */
    public HashMap<String, String[]> soundEvents = new HashMap<String, String[]>();

    /**
     * Path to music file to play.
     */
    public String music = null;

    /**
     * List of texture atlases to preload.
     */
    public String[] atlases = new String[0];
}
