package com.github.alexeybond.spectrum_lost;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.alexeybond.spectrum_lost.SpectrumLostGdx;
import com.github.alexeybond.spectrum_lost.achievements.Achievements;
import com.github.alexeybond.spectrum_lost.achievements.impl.LocalFilesStorage;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new SpectrumLostGdx(), config);

        Gdx.input.setCatchBackKey(true);
        Achievements.use(new LocalFilesStorage(Gdx.files.local(".")));
    }
}
