package com.github.alexeybond.spectrum_lost.renderer.two_dimensional;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.views.CellView2D;

import java.util.Collection;
import java.util.List;

/**
 * Renders a grid using 2D views ({@link CellView2D}) of cells.
 */
public class Renderer {
    private final IGrid grid;
    private final IRayRenderer rayRenderer;
    private final TextureRegion[] commonBgTextures;

    private static Vector2 tv0 = new Vector2();
    private static Ray tr0 = new Ray();

    private static float[] flarePositions;

    public Renderer(final IGrid grid, final IRayRenderer rayRenderer) {
        this.grid = grid;
        this.rayRenderer = rayRenderer;

        if (flarePositions == null || flarePositions.length < grid.width() * grid.height() * 2) {
            flarePositions = new float[grid.width() * grid.height() * 2];
        }

        commonBgTextures = new TextureRegion[] {
                Resources.getSprite("game/cells/background/stones-00-00"),
                Resources.getSprite("game/cells/background/stones-00-01")
        };
    }

    private int bgTileId(int w, int h, int x, int y) {
        int i = (w * 13 + h * 7 - x * 3 - y * 5) % 17;

        i ^= (i>>2) ^ (i>>4);

        return i & 1;
    }

    private void renderCommonBackground(final SpriteBatch batch, final Vector2 pos0, final float cellSize) {
        for (int x = 0; x < grid.width(); x++) {
            for (int y = 0; y < grid.height(); y++) {
                CellView2D view = (CellView2D) grid.getCell(x, y).getView();

                if (view.ignoreBackground()) {
                    continue;
                }

                batch.draw(commonBgTextures[bgTileId(grid.width(), grid.height(), x, y)],
                        pos0.x + cellSize * (float) x,
                        pos0.y + cellSize * (float) y,
                        cellSize, cellSize);
            }
        }
    }

    private void renderCellsLayer(final SpriteBatch batch, final Vector2 pos0, final float cellSize, final int layer) {
        List<? extends ICell> cells = grid.getCells();

        for (int i = 0; i < cells.size(); i++) {
            ICell cell = cells.get(i);
            CellView2D view = (CellView2D) cell.getView();

            tv0.set(pos0).add(cellSize * (float) cell.x(), cellSize * (float) cell.y());

            view.draw(batch, cell, tv0, cellSize, layer);
        }
    }

    private void renderRays(final SpriteBatch batch, final Vector2 pos0, final float cellSize) {
        int fi = 0;

        rayRenderer.beginRays(batch);

        List<? extends ICell> cells = grid.getCells();

        for (int i = 0; i < cells.size(); i++) {
            ICell cell = cells.get(i);

            CellView2D view = (CellView2D) cell.getView();

            tv0.set(pos0).add(cellSize * (.5f + (float) cell.x()), cellSize * (.5f + (float) cell.y()));
            tr0.clear();

            for (int n = 0; n < Direction.NUM; n++) {
                Direction dir = Direction.get(n);

                Ray e = cell.emission(dir);

                if (e.isDark()) continue;

                rayRenderer.drawRay(batch, tv0, dir, e, cellSize);

                tr0.add(e);
            }

            if (view.enableFlare() && !tr0.isDark()) {
//                rayRenderer.drawFlare(batch, tv0, cellSize, tr0);
                flarePositions[fi++] = tv0.x;
                flarePositions[fi++] = tv0.y;
            }
        }

        rayRenderer.endRays(batch);

        for (int i = 0; i < fi; i += 2) {
            tv0.set(flarePositions[i], flarePositions[i+1]);
            rayRenderer.drawFlare(batch, tv0, cellSize, null);
        }
    }

    public void render(final SpriteBatch batch, final Vector2 pos0, final float cellSize) {
        batch.setTransformMatrix(batch.getTransformMatrix().idt());
        renderCommonBackground(batch, pos0, cellSize);
        renderCellsLayer(batch, pos0, cellSize, 0);
        renderRays(batch, pos0, cellSize);
        renderCellsLayer(batch, pos0, cellSize, 1);
    }
}
