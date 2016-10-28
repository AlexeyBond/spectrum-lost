package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
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
import com.github.alexeybond.spectrum_lost.screens.base.$Screen;
import com.github.alexeybond.spectrum_lost.screens.base.Button;
import com.github.alexeybond.spectrum_lost.screens.base.ButtonListener;

/**
 *
 */
public class GameScreen extends com.github.alexeybond.spectrum_lost.screens.base.$Screen {
    protected IGrid grid;
    protected Renderer renderer;
    protected GridPositioner2D positioner;
    protected ILevelsSource levelsSource;
    protected AchievementStatus achievementStatus;
    private float timeSinceLastUpdate = 0;

    private final static float simulationRate = 1.f/32.f;

    private IRayRenderer rayRenderer;

    private Button nextButton;

    private String finalAchievementName;

    public GameScreen(final ILevelsSource levelsSource, final String levelId) {
        this(levelsSource, levelId, null);
    }

    public GameScreen(final ILevelsSource levelsSource, final String levelId, final String finalAchievementName) {
        super();
        rememberWayBack();

        this.finalAchievementName = finalAchievementName;

        achievementStatus = Achievements.get("level:".concat(levelId));

        try {
            rayRenderer = new FboRayRenderer();
        } catch (Exception e) {
            rayRenderer = new LineRayRenderer();
        }

        this.levelsSource = levelsSource;

        nextButton = addButton(-1, 0, new ButtonListener() {
            @Override
            public String getSprite(Button button) {
                return "ui/button-next";
            }

            @Override
            public void press(Button button) {
                recordAchievement();
                // TODO: Go to achievement screen (?)
                goBack();
            }
        });

        nextButton.show(false);

        addButton(0, 0, new ButtonListener() {
            @Override
            public String getSprite(Button button) {
                return "ui/button-back";
            }

            @Override
            public void press(Button button) {
                goBack();
            }
        });

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
    }

    private void recordAchievement() {
        // TODO: Calculate actual points (?)
        achievementStatus.set(achievementStatus.getMaximumPoints(), achievementStatus.getMaximumPoints());

        if (finalAchievementName != null) {
            AchievementStatus achievementStatus = Achievements.get(finalAchievementName);
            achievementStatus.set(achievementStatus.getMaximumPoints(), achievementStatus.getMaximumPoints());
        }

        Achievements.save();
    }

    @Override
    protected void onDrag(float x, float y, float dx, float dy) {
        positioner.drag(dx, dy);
    }

    @Override
    protected void onClick(float x, float y) {
        super.onClick(x,y);

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
    protected void onZoom(int x, int y, float zoom) {
        positioner.scale(zoom);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        positioner.reset(grid);
    }

    private void updateGame() {
        timeSinceLastUpdate += Gdx.graphics.getDeltaTime();
        if (timeSinceLastUpdate >= simulationRate) {
            grid.update();
            timeSinceLastUpdate -= simulationRate;
        }

        nextButton.show(grid.getGameState().isCompleted());
    }

    @Override
    public void draw() {
        updateGame();

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        rayRenderer.prepareFrame();
        spriteBatch.begin();
        renderer.render(spriteBatch, positioner.position(), positioner.size());

        drawButtons();

        spriteBatch.end();
    }

    @Override
    public void leave($Screen next) {
        super.leave(next);
    }
}
