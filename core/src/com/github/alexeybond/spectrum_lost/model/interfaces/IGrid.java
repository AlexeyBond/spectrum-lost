package com.github.alexeybond.spectrum_lost.model.interfaces;

import java.util.Collection;
import java.util.Map;

/**
 *
 */
public interface IGrid {
    /**
     * Get cell at given position.
     */
    ICell getCell(final int x, final int y);

    /**
     * Perform simulation of one step of game time.
     */
    void update();

    /**
     * Get collection of all cells.
     */
    Collection<? extends ICell> getCells();

    /**
     * Get state of the game played on this grid.
     */
    IGameState getGameState();

    /**
     * Get width of this grid (in cells).
     */
    int width();

    /**
     * Get height of this grid (in cells).
     */
    int height();

    /**
     * Attributes of the grid. May include references to the cells that should be available by name.
     */
    Map<String, Object> attributes();

    /**
     * Default cell type.
     */
    ICellType defaultCellType();
}