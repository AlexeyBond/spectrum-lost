package com.github.alexeybond.spectrum_lost;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Json;
import com.github.alexeybond.spectrum_lost.cell_types.$CellTypes;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.JsonSource;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChapterLevelsDesc;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChaptersList;
import com.github.alexeybond.spectrum_lost.levels.json.compact.CompactChapterDesc;
import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.resources.impl.DefaultResourceManager;
import com.github.alexeybond.spectrum_lost.resources.json.PreloadConfig;
import com.github.alexeybond.spectrum_lost.screens.ChapterSelectScreen;
import com.github.alexeybond.spectrum_lost.screens.base.$Screen;
import com.github.alexeybond.spectrum_lost.screens.GameDevScreen;
import com.github.alexeybond.spectrum_lost.views.sprite_2d_views.$Sprite2DViews;

public class SpectrumLostGdx extends ApplicationAdapter {
    private $Screen currentScreen;
    private boolean paused = false;

    private ILevelsSource getDevLevelSource(String chapterId) {
        FileHandle levelsDir = Gdx.files.internal("levels");
        ChaptersList chaptersList = ChaptersList.readFrom(levelsDir);

        CompactChapterDesc chapterDesc = null;

        for (CompactChapterDesc desc : chaptersList.chapters) {
            if (desc.id.equals(chapterId)) {
                chapterDesc = desc;
            }
        }

        ChapterLevelsDesc levelsDesc = chapterDesc.readLevels(levelsDir);
        return new JsonSource(levelsDesc, chapterDesc.attrs);
    }

    @Override
    public void create() {
        Resources.use(new DefaultResourceManager());
        Resources.manager().preload(new Json().fromJson(
                PreloadConfig.class, Gdx.files.internal("preload-default.json")));

        $CellTypes.register();
        $Sprite2DViews.register();

        Locator.RENDERER_OBJECT.set("sprite batch", new SpriteBatch());
        Locator.RENDERER_OBJECT.set("shape renderer", new ShapeRenderer());

        if (Gdx.app.getType() == Application.ApplicationType.Desktop
                && System.getProperty("sl.devmode") != null) {
            ILevelsSource levelsSource = getDevLevelSource(System.getProperty("sl.devch"));
            setCurrentScreen(new GameDevScreen(levelsSource, levelsSource.rootLevelName()));
        } else {
            setCurrentScreen(new ChapterSelectScreen());
        }

        if ("1".equals(System.getProperty("sl.devmode.nomusic"))) {
            Resources.manager().getMusicPlayer().setPlaying(false);
        }
    }

    @Override
    public void render() {
        while (null != currentScreen.next()) {
            setCurrentScreen(currentScreen.next());
        }

        if (paused) {
            resume();
        }

        currentScreen.draw();

//        Gdx.graphics.setTitle(String.valueOf(Gdx.graphics.getFramesPerSecond()));
    }

    private void setCurrentScreen($Screen screen) {
        if (null != currentScreen) {
            currentScreen.next(null);
            currentScreen.pause();
            currentScreen.leave(screen);
        }

        screen.show(currentScreen);
        screen.unpause();
        screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentScreen = screen;
    }

    @Override
    public void pause() {
        currentScreen.pause();
        paused = true;
    }

    @Override
    public void resume() {
        currentScreen.unpause();
        paused = false;
    }

    @Override
    public void resize(int width, int height) {
        currentScreen.resize(width, height);
    }

    @Override
    public void dispose() {
        Locator.CELL_TYPES.clear();
        Locator.CELL_VIEWS.clear();
        Locator.RENDERER_OBJECT.clear();
        Resources.use(null);
    }
}
