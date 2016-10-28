package com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;

/**
 * Draw rays as a lines.
 */
public class LineRayRenderer implements IRayRenderer {
    private ShapeRenderer shapeRenderer = (ShapeRenderer) Locator.RENDERER_OBJECT.get("shape renderer");

    @Override
    public void prepareFrame() {

    }

    @Override
    public void beginRays(final SpriteBatch batch) {
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE);
        Gdx.gl.glBlendEquation(GL20.GL_FUNC_ADD);
        Gdx.gl.glLineWidth(5.f);
    }

    @Override
    public void endRays(final SpriteBatch batch) {
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public void drawRay(
            final SpriteBatch batch,
            final Vector2 fromPos,
            final Direction dir,
            final Ray ray,
            final float cellSize) {
        shapeRenderer.setColor(
                (float) ray.getR() / (float) Ray.MAX_BRIGHTNESS,
                (float) ray.getG() / (float) Ray.MAX_BRIGHTNESS,
                (float) ray.getB() / (float) Ray.MAX_BRIGHTNESS,
                1.f);
        shapeRenderer.line(
                fromPos.x, fromPos.y,
                fromPos.x + cellSize * (float) dir.h,
                fromPos.y + cellSize * (float) dir.v);
    }

    @Override
    public void drawFlare(SpriteBatch batch, Vector2 pos, float cellSize, Ray raySum) {
        // do nothing
    }
}
