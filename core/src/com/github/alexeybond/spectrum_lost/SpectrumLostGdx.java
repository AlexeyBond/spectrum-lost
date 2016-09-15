package com.github.alexeybond.spectrum_lost;

import com.badlogic.gdx.ApplicationAdapter;
import com.github.alexeybond.spectrum_lost.cell_types.$CellTypes;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.screens.$Screen;
import com.github.alexeybond.spectrum_lost.screens.GameScreen;
import com.github.alexeybond.spectrum_lost.views.sprite_2d_views.$Sprite2DViews;

public class SpectrumLostGdx extends ApplicationAdapter {
	private $Screen currentScreen;
	
	@Override
	public void create () {
		$CellTypes.register();
		$Sprite2DViews.register();

		IGrid grid;
		grid = new GridImpl(8, 4, "empty", new GameStateImpl());
        grid.getCell(0, 4).setType(Locator.CELL_TYPES.get("expector"));
		grid.getCell(0, 1).setType(Locator.CELL_TYPES.get("mirror"));
		grid.getCell(0, 1).setDirection(Direction.UP_RT);
		grid.getCell(7, 1).setType(Locator.CELL_TYPES.get("emitter"));
		grid.getCell(7, 1).setDirection(Direction.LF);

		currentScreen = new GameScreen(grid);
		currentScreen.show(null);
		currentScreen.unpause();
	}

	@Override
	public void render () {
		currentScreen.draw();

		while (null != currentScreen.next()) {
			$Screen next = currentScreen.next();

			currentScreen.pause();
			currentScreen.leave(next);
			next.show(currentScreen);
			next.unpause();
			currentScreen = next;
		}
	}

	@Override
	public void pause() {
		currentScreen.pause();
	}

	@Override
	public void resume() {
		currentScreen.unpause();
	}

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	@Override
	public void dispose () {
	}
}
