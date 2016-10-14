package com.github.alexeybond.spectrum_lost;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.github.alexeybond.spectrum_lost.cell_types.$CellTypes;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.JsonSource;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.screens.$Screen;
import com.github.alexeybond.spectrum_lost.screens.GameDevScreen;
import com.github.alexeybond.spectrum_lost.screens.GameScreen;
import com.github.alexeybond.spectrum_lost.views.sprite_2d_views.$Sprite2DViews;

public class SpectrumLostGdx extends ApplicationAdapter {
	private $Screen currentScreen;
	private Music music;

	private ILevelsSource getLevelSource() {
		return new JsonSource("levels/levels-chapter4.json");
	}
	
	@Override
	public void create () {
		Resources.use(new TextureAtlas("sprites/sprites-common.atlas"));
		$CellTypes.register();
		$Sprite2DViews.register();

		if (Gdx.app.getType() == Application.ApplicationType.Desktop
			&& System.getProperty("sl.devmode") != null) {
			currentScreen = new GameDevScreen(getLevelSource());
		} else {
			currentScreen = new GameScreen(getLevelSource());
		}

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
