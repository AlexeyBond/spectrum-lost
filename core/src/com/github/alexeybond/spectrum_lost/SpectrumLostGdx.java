package com.github.alexeybond.spectrum_lost;

import com.badlogic.gdx.ApplicationAdapter;
import com.github.alexeybond.spectrum_lost.cell_types.$CellTypes;
import com.github.alexeybond.spectrum_lost.levels.hardcoded.Chapter0;
import com.github.alexeybond.spectrum_lost.screens.$Screen;
import com.github.alexeybond.spectrum_lost.screens.GameScreen;
import com.github.alexeybond.spectrum_lost.views.sprite_2d_views.$Sprite2DViews;

public class SpectrumLostGdx extends ApplicationAdapter {
	private $Screen currentScreen;
	
	@Override
	public void create () {
		$CellTypes.register();
		$Sprite2DViews.register();

		currentScreen = new GameScreen(new Chapter0());
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
