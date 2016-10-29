package com.github.alexeybond.spectrum_lost.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.alexeybond.spectrum_lost.SpectrumLostGdx;
import com.github.alexeybond.spectrum_lost.achievements.Achievements;
import com.github.alexeybond.spectrum_lost.achievements.impl.LocalFilesStorage;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Achievements.use(new LocalFilesStorage(Gdx.files.external("spectrum-lost")));
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SpectrumLostGdx(), config);
	}
}
