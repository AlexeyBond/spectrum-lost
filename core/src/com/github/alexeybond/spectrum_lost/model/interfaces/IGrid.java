package com.github.alexeybond.spectrum_lost.model.interfaces;

import java.util.List;
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
    List<? extends ICell> getCells();

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

    /**
     * Emit event on the given cell.
     */
    void emitEvent(ICell cell, String eventName, Object arg);

    /**
     * Add event listener. The listeners will be notified in the order they were added.
     */
    void addEventListener(IGridEventListener listener);
}
