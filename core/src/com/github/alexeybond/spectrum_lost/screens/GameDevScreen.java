package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.GridDesc;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

import java.util.Date;

/**
 * Game screen with developer features.
 */
public class GameDevScreen extends GameScreen {
    public GameDevScreen(ILevelsSource levelsSource) {
        super(levelsSource);
    }

    @Override
    protected void onClick(float x, float y) {
        if (showNextButton && nextBtnRect.contains(x, y)) {
            nextLevel();
            return;
        }

        ICell cell = positioner.cellAt((int) x, (int) y);

        if (cell != null) {
            cell.setDirection(cell.direction().next());
        }
    }

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

    private void createGrid(int dx, int dy) {
        int createx = Math.max(3, grid.width() + dx);
        int createy = Math.max(3, grid.height() + dy);

        int cx = Math.min(createx, grid.width());
        int cy = Math.min(createy, grid.height());

        IGrid ng = new GridImpl(createx, createy, "empty", new GameStateImpl());

        for (int x = 0; x < cx; x++) {
            for (int y = 0; y < cy; y++) {
                ICell o = grid.getCell(x, y);
                ICell n = ng.getCell(x, y);

                n.setDirection(o.direction());
                n.setType(o.type());
            }
        }

        goToGrid(ng);
    }

    private void clearGrid() {
        for (int x = 0; x < grid.width(); x++) {
            for (int y = 0; y < grid.height(); y++) {
                grid.getCell(x,y).setType(Locator.CELL_TYPES.get("empty"));
                grid.getCell(x,y).setDirection(Direction.UP);
            }
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
            case Input.Keys.GRAVE:
                setCell("expector");
                break;
            case Input.Keys.NUM_1:
                setCell("expector:r");
                break;
            case Input.Keys.NUM_2:
                setCell("expector:g");
                break;
            case Input.Keys.NUM_3:
                setCell("expector:b");
                break;
            case Input.Keys.NUM_0:
                setCell("expector:none");
                break;
            case Input.Keys.ENTER:
                nextLevel();
                break;

            case Input.Keys.NUMPAD_9:
                createGrid(1,1);
                break;
            case Input.Keys.NUMPAD_8:
                createGrid(0,1);
                break;
            case Input.Keys.NUMPAD_7:
                createGrid(-1,1);
                break;
            case Input.Keys.NUMPAD_4:
                createGrid(-1,0);
                break;
            case Input.Keys.NUMPAD_1:
                createGrid(-1,-1);
                break;
            case Input.Keys.NUMPAD_2:
                createGrid(0,-1);
                break;
            case Input.Keys.NUMPAD_3:
                createGrid(1,-1);
                break;
            case Input.Keys.NUMPAD_6:
                createGrid(1,0);
                break;
            case Input.Keys.NUMPAD_5:
                createGrid(0,0);
                break;

            case Input.Keys.NUMPAD_0:
                clearGrid();
                break;
        }
    }
}
