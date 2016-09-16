package com.github.alexeybond.spectrum_lost.renderer.two_dimensional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;

/**
 *
 */
public class GridPositioner2D {
    private IGrid grid;
    private int prevVPWidth, prevVPHeight;
    private final Vector2 pos = new Vector2();
    private final Vector2 minPos = new Vector2();
    private final Vector2 maxPos = new Vector2();
    private float cellSize;

    private final static float MAX_CELL_SIZE = 128;

    public GridPositioner2D(final IGrid grid) {
        reset(grid);
    }

    private void refreshMinMaxPos() {
        float w = (float) prevVPWidth;
        float h = (float) prevVPHeight;

        float dw = .3f * w, szX = cellSize * (float) grid.width();
        float dh = .3f * h, szY = cellSize * (float) grid.height();

        minPos.x = w - dw - szX;
        minPos.y = h - dh - szY;

        maxPos.x = dw;
        maxPos.y = dh;

        drag(0, 0);
    }

    private void setCellSize(final float sz) {
        float minSize = Math.max(
                ((float) prevVPWidth) * .4f / (float) grid.width(),
                ((float) prevVPHeight) * .4f / (float) grid.height());
        cellSize = Math.min(MAX_CELL_SIZE, Math.max(minSize, sz));
    }

    public ICell cellAt(final int x, final int y) {
        int xPos = (int)Math.floor(((((float) x) - pos.x) / cellSize));
        int yPos = (int)Math.floor((((float) y) - pos.y) / cellSize);

        if (xPos >= 0 && yPos >= 0 &&
            xPos < grid.width() &&
            yPos < grid.height()) {
            return grid.getCell(xPos, yPos);
        }

        return null;
    }

    public void drag(final float dx, final float dy) {
        pos.x = Math.max(minPos.x, Math.min(maxPos.x, pos.x + dx));
        pos.y = Math.max(minPos.y, Math.min(maxPos.y, pos.y + dy));
    }

    public void scale(final float ds) {
        float pcs = cellSize;
        setCellSize(cellSize * ds);
        pos
                .scl(2.f).sub(prevVPWidth, prevVPHeight)
                .scl(cellSize / pcs)
                .add(prevVPWidth, prevVPHeight).scl(.5f);
        refreshMinMaxPos();
    }

    public void reset(final IGrid grid) {
        this.grid = grid;
        prevVPWidth = Gdx.graphics.getWidth();
        prevVPHeight = Gdx.graphics.getHeight();

        float gw = (float) grid.width() + 2;
        float gh = (float) grid.height() + 2;

        setCellSize(Math.min(((float) prevVPWidth) / gw, ((float) prevVPHeight) / gh));

        pos.x = (((float) prevVPWidth) - gw * cellSize) * .5f + cellSize;
        pos.y = (((float) prevVPHeight) - gh * cellSize) * .5f + cellSize;

        refreshMinMaxPos();
    }

    public void update() {
        if (prevVPHeight != Gdx.graphics.getHeight() ||
            prevVPWidth != Gdx.graphics.getWidth()) {
            reset(grid);
        }
    }

    public Vector2 position() {
        return pos;
    }

    public float size() {
        return cellSize;
    }
}
