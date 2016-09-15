package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.GridPositioner2D;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.Renderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.FboRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.LineRayRenderer;

/**
 *
 */
public class GameScreen extends $Screen {
    private IGrid grid;
    private Renderer renderer;
    private GridPositioner2D positioner;

    private static IRayRenderer rayRenderer;

    public GameScreen(final IGrid grid) {
        super();

        if (rayRenderer == null) {
            try {
                rayRenderer = new FboRayRenderer();
            } catch (Exception e) {
                rayRenderer = new LineRayRenderer();
            }
        }

        this.grid = grid;
        this.renderer = new Renderer(grid, rayRenderer);
        this.positioner = new GridPositioner2D(grid);
    }

    @Override
    protected void onDrag(float x, float y, float dx, float dy) {
        positioner.drag(dx, dy);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        positioner.reset(grid);
    }

    private void updateGame() {
        grid.update();

        if (grid.getGameState().isCompleted()) {
            // TODO: Add [Next>] button or something like that
        }
    }

    @Override
    public void draw() {
        updateGame();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        rayRenderer.prepareFrame();
        spriteBatch.begin();
        renderer.render(spriteBatch, positioner.position(), positioner.size());
        spriteBatch.end();
    }
}
