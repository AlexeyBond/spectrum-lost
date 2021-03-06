package com.github.alexeybond.spectrum_lost.model.implementation;

import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.model.interfaces.*;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

import java.util.*;

/**
 *
 */
public class GridImpl implements IGrid {
    int frameCounter;

    private final int height, width;
    private final IGameState gameState;

    private final ArrayList<CellImpl> cells;
    private final CellImpl noCell;

    private final Map<String, Object> attributes;

    private final ICellType defaultCT;

    private final ArrayList<IGridEventListener> eventListeners;

    public GridImpl(final int width, final int height, final String defaultCellType, final IGameState gameState) {
        this.width = width;
        this.height = height;
        this.gameState = gameState;
        this.attributes = new HashMap<String, Object>();
        this.eventListeners = new ArrayList<IGridEventListener>();
        cells = new ArrayList<CellImpl>(Collections.<CellImpl>nCopies(this.width * this.height, null));

        defaultCT = Locator.CELL_TYPES.get(defaultCellType);
        noCell = new CellImpl(this, -1, -1, Direction.DEFAULT, defaultCellType);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells.set(x + y * this.width, new CellImpl(this, x, y, Direction.DEFAULT, defaultCellType));
            }
        }

        for (CellImpl cell : cells) {
            for (int d = 0; d < Direction.NUM; d++) {
                Direction dir = Direction.get(d);

                cell.neighbours[d] = getCell(cell.x() + dir.h, cell.y() + dir.v);
            }
        }
    }

    @Override
    public CellImpl getCell(int x, int y) {
        if (x < 0 || x >= width)
            return noCell;

        if (y < 0 || y >= height)
            return noCell;

        return cells.get(x + y * width);
    }

    @Override
    public void update() {
        // as get(I) is O(1) for ArrayList this should be faster than foreach
        for (int i = 0; i < cells.size(); i++) {
            CellImpl cell = cells.get(i);
            cell.clearEmission();
            cell.type().update(cell);
        }

        ++frameCounter;
    }

    @Override
    public List<? extends ICell> getCells() {
        return cells;
    }

    @Override
    public IGameState getGameState() {
        return this.gameState;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public Map<String, Object> attributes() {
        return attributes;
    }

    @Override
    public ICellType defaultCellType() {
        return defaultCT;
    }

    @Override
    public void emitEvent(ICell cell, String eventName, Object arg) {
        for (int i = 0; i < eventListeners.size(); i++) eventListeners.get(i).onEvent(cell, eventName, arg);
    }

    @Override
    public void addEventListener(IGridEventListener listener) {
        eventListeners.add(listener);
    }
}
