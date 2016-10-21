package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.alexeybond.spectrum_lost.achievements.AchievementStatus;
import com.github.alexeybond.spectrum_lost.achievements.Achievements;
import com.github.alexeybond.spectrum_lost.cell_types.RecursiveCell;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.GridPositioner2D;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.Renderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.FboRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.LineRayRenderer;
import com.github.alexeybond.spectrum_lost.resources.Resources;

/**
 *
 */
public class GameScreen extends com.github.alexeybond.spectrum_lost.screens.base.$Screen {
    protected IGrid grid;
    protected Renderer renderer;
    protected GridPositioner2D positioner;
    protected boolean showNextButton;
    protected Rectangle nextBtnRect = new Rectangle();
    protected ILevelsSource levelsSource;
    protected AchievementStatus achievementStatus;
    private float timeSinceLastUpdate = 0;

    private final static float simulationRate = 1.f/32.f;

    private static TextureRegion nextButtonTexture;

    private static IRayRenderer rayRenderer;

    public GameScreen(final ILevelsSource levelsSource, final String levelId) {
        super();
        rememberWayBack();

        achievementStatus = Achievements.get("level:".concat(levelId));

        if (rayRenderer == null) {
            try {
                rayRenderer = new FboRayRenderer();
            } catch (Exception e) {
                rayRenderer = new LineRayRenderer();
            }
        }

        if (nextButtonTexture == null) nextButtonTexture = Resources.getSprite("ui/button-next");

        this.levelsSource = levelsSource;

        initLevel(levelId);
    }

    protected void initLevel(final String id) {
        goToGrid(levelsSource.initLevel(id));
    }

    protected void goToGrid(IGrid grid) {
        this.grid = grid;

        if (positioner == null) {
            positioner = new GridPositioner2D(grid);
        } else {
            positioner.reset(grid);
        }

        renderer = new Renderer(grid, rayRenderer);

        this.showNextButton = false;
    }

    @Override
    protected void onDrag(float x, float y, float dx, float dy) {
        positioner.drag(dx, dy);
    }

    @Override
    protected void onClick(float x, float y) {
        if (showNextButton && nextBtnRect.contains(x, y)) {
            // TODO: Go to win screen.
//            nextLevel();
            return;
        }

        ICell cell = positioner.cellAt((int) x, (int) y);

        if (cell == null) {
            return;
        }

        if (cell.type().id().equals("recursive")) {
            RecursiveCell.State state = (RecursiveCell.State) cell.state();

            if (!state.isError() && state.isOpen()) {
                callLevel((String) cell.getAttribute("levelId"));
            }
        } else if (cell.getAttribute("noTurn") == null) {
            cell.setDirection(cell.direction().next());
        }
    }

    protected void callLevel(final String levelId) {
        next(newLevelScreen(levelId));
    }

    protected com.github.alexeybond.spectrum_lost.screens.base.$Screen newLevelScreen(final String levelId) {
        return new GameScreen(levelsSource, levelId);
    }

    @Override
    protected void onScroll(int amount) {
        if (amount == 0) return;
        positioner.scale((amount < 0)?1.1f:.9f);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        positioner.reset(grid);
        nextBtnRect.set(width - 20 - nextButtonTexture.getRegionWidth(), 20,
                nextButtonTexture.getRegionWidth(), nextButtonTexture.getRegionHeight());
    }

    private void updateGame() {
        timeSinceLastUpdate += Gdx.graphics.getDeltaTime();
        if (timeSinceLastUpdate >= simulationRate) {
            grid.update();
            timeSinceLastUpdate -= simulationRate;
        }

        if (grid.getGameState().isCompleted()) {
            if (!showNextButton) {
                showNextButton = true;
            }
        } else {
            showNextButton = false;
        }
    }

    @Override
    public void draw() {
        updateGame();

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        rayRenderer.prepareFrame();
        spriteBatch.begin();
        renderer.render(spriteBatch, positioner.position(), positioner.size());

        if (showNextButton) {
            spriteBatch.draw(nextButtonTexture, nextBtnRect.x, nextBtnRect.y, nextBtnRect.width, nextBtnRect.height);
        }

        spriteBatch.end();
    }
}
