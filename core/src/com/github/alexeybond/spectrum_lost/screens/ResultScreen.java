package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.alexeybond.spectrum_lost.achievements.AchievementStatus;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.screens.base.$Screen;
import com.github.alexeybond.spectrum_lost.screens.base.Button;
import com.github.alexeybond.spectrum_lost.screens.base.ButtonListener;

/**
 *
 */
public class ResultScreen extends $Screen {
    private final static float SEC_PER_SYMBOL = .3f;
    private final static float SEC_TO_FADE = .2f;

    private final AchievementStatus achievementStatus;

    private final TextureRegion successRegion;
    private final TextureRegion failureRegion;

    private float showSymbols = 0f;
    private int prevSymbols = 0;

    private final GameScreen gameScreen;
    private final $Screen nextScreen;

    public ResultScreen(final AchievementStatus achievementStatus,
                        final $Screen next,
                        final GameScreen gameScreen) {
        this.achievementStatus = achievementStatus;
        this.nextScreen = next;
        this.gameScreen = gameScreen;

        successRegion = Resources.getSprite("ui/rating-success");
        failureRegion = Resources.getSprite("ui/rating-fail");

        addButton(-1, 0, new ButtonListener() {
            @Override
            public String getSprite(Button button) {
                return "ui/button-next";
            }

            @Override
            public void press(Button button) {
                if (prevSymbols != achievementStatus.getMaximumPoints()) {
                    prevSymbols = achievementStatus.getMaximumPoints();
                    showSymbols = prevSymbols;
                } else {
                    next(next);
                }
            }
        });

        addButton(0, 0, new ButtonListener() {
            @Override
            public String getSprite(Button button) {
                return "ui/button-reset";
            }

            @Override
            public void press(Button button) {
                gameScreen.resetGame();
                next(gameScreen);
            }
        });
    }

    @Override
    public void unpause() {
        super.unpause();

        if (awaitResources()) {
            return;
        }

        showSymbols = 0;
        prevSymbols = 0;
    }

    @Override
    public void draw() {
        showSymbols += Gdx.graphics.getDeltaTime() / SEC_PER_SYMBOL;
        showSymbols = Math.min((float) achievementStatus.getMaximumPoints(), showSymbols);
        int curSymbols = Math.min(achievementStatus.getMaximumPoints(), (int) Math.floor(showSymbols));

        if (curSymbols != prevSymbols) {
            if (curSymbols > achievementStatus.getAchievedPoints()) {
                // Just appeared "fail" icon
            } else {
                // Just appeared "success" icon
            }

            prevSymbols = curSymbols;
        }

        gameScreen.draw();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float fade = Math.min(showSymbols * SEC_PER_SYMBOL / SEC_TO_FADE, 1f);

        shapeRenderer.setColor(0,0,0,.8f * fade);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.rect(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        shapeRenderer.end();

        spriteBatch.begin();

        float cx = .5f * Gdx.graphics.getWidth();
        float cy = .5f * Gdx.graphics.getHeight();

        float ih = successRegion.getRegionHeight();
        float iw = successRegion.getRegionWidth();
        float y = cy - .5f * ih;

        for (int i = 0; i < curSymbols; i++) {
            float x = cx - showSymbols * iw * .5f + iw * i;

            spriteBatch.draw(
                    i >= achievementStatus.getAchievedPoints() ? failureRegion : successRegion,
                    x, y
            );
        }

        super.drawButtons();

        spriteBatch.end();
    }
}
