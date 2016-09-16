package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.github.alexeybond.spectrum_lost.levels.ILevel;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.GridPositioner2D;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.Renderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.FboRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.LineRayRenderer;

import java.util.Iterator;

/**
 *
 */
public class GameScreen extends $Screen {
    private IGrid grid;
    private Renderer renderer;
    private GridPositioner2D positioner;
    private boolean showNextButton;
    private Rectangle nextBtnRect = new Rectangle();
    private ILevelsSource levelsSource;
    private Iterator<ILevel> levelIterator;

    private static Texture nextButtonTexture;

    private static IRayRenderer rayRenderer;

    public GameScreen(final ILevelsSource levelsSource) {
        super();

        if (rayRenderer == null) {
            try {
                rayRenderer = new FboRayRenderer();
            } catch (Exception e) {
                rayRenderer = new LineRayRenderer();
            }
        }

        if (nextButtonTexture == null) nextButtonTexture = new Texture("ui/button-next.png");

        this.levelsSource = levelsSource;

        nextLevel();
    }

    private void nextLevel() {
        if (levelIterator == null || !levelIterator.hasNext())
            levelIterator = levelsSource.iterator();

        ILevel level = levelIterator.next();

        grid = level.init();

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
            nextLevel();
            return;
        }

        ICell cell = positioner.cellAt((int) x, (int) y);

        if (cell != null && cell.getAttribute("noTurn") == null) {
            cell.setDirection(cell.direction().next());
        }
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
        nextBtnRect.set(width - 20 - nextButtonTexture.getWidth(), 20,
                nextButtonTexture.getWidth(), nextButtonTexture.getHeight());
    }

    private void updateGame() {
        grid.update();

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
