package com.github.alexeybond.spectrum_lost.renderer.two_dimensional;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 * Interface for object drawing the rays emitted by cells.
 */
public interface IRayRenderer {
    /**
     * Update animation before drawing the next frame.
     */
    void prepareFrame();

    /**
     * Begin drawing rays.
     */
    void beginRays(final SpriteBatch batch);

    /**
     * End drawing rays.
     */
    void endRays(final SpriteBatch batch);

    /**
     * Draw a ray.
     *
     * @param batch    sprite batch to use
     * @param fromPos  position of the center of cell on the screen
     * @param dir      direction where the cell emits the ray
     * @param ray      brightness of the ray
     * @param cellSize size of a cell
     */
    void drawRay(
            final SpriteBatch batch,
            final Vector2 fromPos,
            final Direction dir,
            final Ray ray,
            final float cellSize);

    /**
     * Draw a flare sprite in cell emitting a ray.
     *
     * @param batch    sprite batch to use
     * @param pos      position of cell center
     * @param cellSize size of cell
     * @param raySum   sum of rays emitted by the cell
     */
    void drawFlare(
            final SpriteBatch batch,
            final Vector2 pos,
            final float cellSize,
            final Ray raySum);
}
