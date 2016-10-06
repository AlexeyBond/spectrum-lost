package com.github.alexeybond.spectrum_lost;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.github.alexeybond.spectrum_lost.cell_types.$CellTypes;
import com.github.alexeybond.spectrum_lost.levels.hardcoded.Chapter0;
import com.github.alexeybond.spectrum_lost.levels.hardcoded.Chapter1;
import com.github.alexeybond.spectrum_lost.levels.hardcoded.Chapter3;
import com.github.alexeybond.spectrum_lost.screens.$Screen;
import com.github.alexeybond.spectrum_lost.screens.GameScreen;
import com.github.alexeybond.spectrum_lost.views.sprite_2d_views.$Sprite2DViews;

public class SpectrumLostGdx extends ApplicationAdapter {
	private $Screen currentScreen;
	private Music music;
	
	@Override
	public void create () {
		$CellTypes.register();
		$Sprite2DViews.register();

		currentScreen = new GameScreen(/*new Chapter0()*/new Chapter1()/*new Chapter3()*/);
		currentScreen.show(null);
		currentScreen.unpause();

		music = Gdx.audio.newMusic(Gdx.files.internal("sound/music/0xB-00.mp3"));
		music.setLooping(true);
//		music.play();
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
		music.dispose();
		music = null;
	}
}
