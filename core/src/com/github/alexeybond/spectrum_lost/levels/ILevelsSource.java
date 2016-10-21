package com.github.alexeybond.spectrum_lost.levels;

import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;

import java.util.List;

/**
 *
 */
public interface ILevelsSource {
    /**
     * Initialize grid for level with given name.
     *
     * @throws java.util.NoSuchElementException if there is no level with given name.
     */
    IGrid initLevel(String name);

    /**
     * Get name of the root level.
     */
    String rootLevelName();

    /**
     * Get attribute ao the level set.
     */
    Object getAttribute(String name);

    /**
     * Get list of all available level identifiers.
     */
    List<String> enumLevels();
}
