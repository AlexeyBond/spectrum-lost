package com.github.alexeybond.spectrum_lost.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellView;

/**
 *
 */
public interface CellView2D extends ICellView {
    /**
     * Draw the cell.
     *
     * @param batch   the batch to use to draw sprites
     * @param cell    the cell to draw
     * @param pos     position of bottom left corner of cell
     * @param size    size of cell (height and width)
     * @param layer   # of layer to draw: 0 - background, 1 - foreground
     */
    void draw(final SpriteBatch batch, final ICell cell, final Vector2 pos, final float size, final int layer);

    /**
     * True if no default background should be drawn under cell using this view.
     */
    boolean ignoreBackground();

    /**
     * True if a flare sprite should be drawn if cell with this view emits any ray.
     */
    boolean enableFlare();
}
