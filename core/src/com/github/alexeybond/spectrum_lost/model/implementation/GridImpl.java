package com.github.alexeybond.spectrum_lost.model.implementation;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGameState;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 */
public class GridImpl implements IGrid {
    int frameCounter;

    private final int height, width;
    private final IGameState gameState;

    private final ArrayList<CellImpl> cells;
    private final CellImpl noCell;

    public GridImpl(final int width, final int height, final String defaultCellType, final IGameState gameState) {
        this.width = width;
        this.height = height;
        this.gameState = gameState;
        cells = new ArrayList<CellImpl>(Collections.<CellImpl>nCopies(this.width * this.height, null));

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
        for (CellImpl cell : cells) {
            cell.clearEmission();
            cell.type().update(cell);
        }

        ++frameCounter;
    }

    @Override
    public Collection<? extends ICell> getCells() {
        return cells;
    }

    @Override
    public IGameState getGameState() {
        return this.gameState;
    }
}
