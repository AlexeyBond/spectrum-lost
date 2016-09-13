package com.github.alexeybond.spectrum_lost;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.cell_types.$CellTypes;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.Renderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.FboRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.LineRayRenderer;
import com.github.alexeybond.spectrum_lost.views.sprite_2d_views.$Sprite2DViews;

public class SpectrumLostGdx extends ApplicationAdapter {
	SpriteBatch batch;

	IGrid grid;
    Renderer renderer;
	IRayRenderer rayRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		$CellTypes.register();
		$Sprite2DViews.register();
		grid = new GridImpl(8, 4, "empty", new GameStateImpl());
        grid.getCell(0, 4).setType(Locator.CELL_TYPES.get("expector"));
		grid.getCell(0, 1).setType(Locator.CELL_TYPES.get("mirror"));
		grid.getCell(0, 1).setDirection(Direction.UP_RT);
		grid.getCell(7, 1).setType(Locator.CELL_TYPES.get("emitter"));
		grid.getCell(7, 1).setDirection(Direction.LF);
		rayRenderer = new FboRayRenderer();
        renderer = new Renderer(grid, rayRenderer);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		grid.update();
		rayRenderer.prepareFrame();
		batch.begin();
        renderer.render(batch, new Vector2(10, 10), 64);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.gl.glViewport(0, 0, width, height);
		batch.getProjectionMatrix()
				.setToOrtho2D(0, 0, width, height);
		batch.setProjectionMatrix(batch.getProjectionMatrix());
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
