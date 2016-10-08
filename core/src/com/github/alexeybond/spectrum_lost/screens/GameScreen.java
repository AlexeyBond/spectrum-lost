package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.alexeybond.spectrum_lost.levels.ILevel;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.GridDesc;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.GridPositioner2D;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.Renderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.FboRayRenderer;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray.LineRayRenderer;

import java.util.Date;
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
    private float timeSinceLastUpdate = 0;

    private final static float simulationRate = 1.f/32.f;

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

    // --- DEV.MODE METHODS ---

    private void dumpLevel() {
        Json json = new Json(JsonWriter.OutputType.json);
        Gdx.files.local(new Date().toString().concat(".json")).writeString(json.prettyPrint(GridDesc.dump(grid)), false);
    }

    private void setCell(final String type) {
        ICell cell = positioner.cellAt(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        if (cell != null) {
            cell.setType(Locator.CELL_TYPES.get(type));
        }
    }

    @Override
    protected void onKeyPress(int code) {
        switch (code) {
            case Input.Keys.S:
                dumpLevel();
                break;
            case Input.Keys.W:
                setCell("wall");
                break;
            case Input.Keys.SPACE:
                setCell("empty");
                break;
            case Input.Keys.Q:
                setCell("expector");
                break;
            case Input.Keys.E:
                setCell("emitter");
                break;
            case Input.Keys.X:
                setCell("shifter");
                break;
            case Input.Keys.F:
                setCell("fader");
                break;
            case Input.Keys.H:
                setCell("hmirror");
                break;
            case Input.Keys.M:
                setCell("mirror");
                break;
            case Input.Keys.N:
                setCell("mixer");
                break;
            case Input.Keys.K:
                setCell("multiplier");
                break;
            case Input.Keys.P:
                setCell("prism");
                break;
            case Input.Keys.I:
                setCell("switch");
                break;
            case Input.Keys.C:
                setCell("clear");
                break;
        }
    }
}
