package com.github.alexeybond.spectrum_lost.renderer.two_dimensional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.views.CellView2D;

/**
 * Renders a grid using 2D views ({@link CellView2D}) of cells.
 */
public class Renderer {
    private final IGrid grid;
    private static Texture commonBgTexture;

    private static Vector2 tv0 = new Vector2();

    public Renderer(final IGrid grid) {
        this.grid = grid;

        if (null == commonBgTexture) {
            commonBgTexture = new Texture("background/stones-00-00.png");
        }
    }

    private void renderCommonBackground(final SpriteBatch batch, final Vector2 pos0, final float cellSize) {
        for (int x = 0; x < grid.width(); x++) {
            for (int y = 0; y < grid.height(); y++) {
                CellView2D view = (CellView2D) grid.getCell(x, y).getView();

                if (view.ignoreBackground()) {
                    continue;
                }

                batch.draw(commonBgTexture,
                        pos0.x + cellSize * (float) x,
                        pos0.y + cellSize * (float) y,
                        cellSize, cellSize);
            }
        }
    }

    private void renderCellsLayer(final SpriteBatch batch, final Vector2 pos0, final float cellSize, final int layer) {
        for (ICell cell: grid.getCells()) {
            CellView2D view = (CellView2D) cell.getView();

            tv0.set(pos0).add(cellSize * (float) cell.x(), cellSize * (float) cell.y());

            view.draw(batch, cell, tv0, cellSize, layer);
        }
    }

    public void render(final SpriteBatch batch, final Vector2 pos0, final float cellSize) {
        renderCommonBackground(batch, pos0, cellSize);
        renderCellsLayer(batch, pos0, cellSize, 0);
        renderCellsLayer(batch, pos0, cellSize, 1);
    }
}
