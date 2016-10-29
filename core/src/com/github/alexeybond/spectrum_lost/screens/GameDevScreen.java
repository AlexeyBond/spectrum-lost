package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.alexeybond.spectrum_lost.achievements.Achievements;
import com.github.alexeybond.spectrum_lost.cell_types.RecursiveCell;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.GridDesc;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.screens.base.Button;
import com.github.alexeybond.spectrum_lost.screens.base.ButtonListener;

import java.util.Date;
import java.util.List;

/**
 * Game screen with developer features.
 */
public class GameDevScreen extends GameScreen {
    private String currentRecursiveLevelId;

    public GameDevScreen(ILevelsSource levelsSource, String levelId) {
        super(levelsSource, levelId);

        Gdx.graphics.setTitle(levelId);

        addButton(-1, -2, new ButtonListener() {
            @Override
            public String getSprite(Button button) {
                return "ui/button-save";
            }

            @Override
            public void press(Button button) {
                dumpLevel();
            }
        });
    }

    @Override
    protected void onClick(float x, float y) {
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            super.onClick(x, y);
            return;
        }

        ICell cell = positioner.cellAt((int) x, (int) y);

        if (cell == null) {
            super.onClick(x,y);
            return;
        }

        if (cell.type().id().equals("recursive") && !Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            if (((RecursiveCell.State) cell.state()).isError()) {
                return;
            }

            callLevel((String) cell.getAttribute("levelId"));
        } else {
            cell.setDirection(cell.direction().next());
        }
    }

    private void dumpLevel() {
        Json json = new Json(JsonWriter.OutputType.json);
        Gdx.files
                .local("../../_raw-assets/levels/_save/")
                .child(new Date().toString().concat(".json")).writeString(json.prettyPrint(GridDesc.dump(grid)), false);
    }

    private void setCell(final String type) {
        ICell cell = positioner.cellAt(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        if (cell != null) {
            cell.getOwnAttributes().clear();
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

                n.getOwnAttributes().putAll(o.getOwnAttributes());
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
                grid.getCell(x,y).getOwnAttributes().clear();
            }
        }
    }

    @Override
    protected com.github.alexeybond.spectrum_lost.screens.base.$Screen newLevelScreen(String levelId) {
        return new GameDevScreen(levelsSource, levelId);
    }

    private void setRecursive() {
        ICell cell = positioner.cellAt(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        List<String> levels = levelsSource.enumLevels();

//        String cid = (String) cell.getAttribute("levelId");

        int delta = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)?-1:1;

        currentRecursiveLevelId = levels.get(
                (levels.indexOf(currentRecursiveLevelId)+levels.size()+delta)%levels.size());

        Gdx.graphics.setTitle("Recursive: ".concat(currentRecursiveLevelId));

        cell.getOwnAttributes().put("levelId", currentRecursiveLevelId);

        cell.setType(cell.grid().defaultCellType());
        cell.setType(Locator.CELL_TYPES.get("recursive"));
    }

    private void toggleAchievement() {
        if (achievementStatus.getAchievedPoints() == 0) {
            achievementStatus.set(achievementStatus.getMaximumPoints(), achievementStatus.getMaximumPoints());
        } else {
            achievementStatus.set(0, achievementStatus.getMaximumPoints());
        }

        Achievements.save();
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
            case Input.Keys.Y:
                setCell("pulsar");
                break;
            case Input.Keys.U:
                setCell("pulsar-b");
                break;
            case Input.Keys.T:
                setCell("pulsar-c");
                break;

            case Input.Keys.NUM_4:
                setCell("portal:a");
                break;
            case Input.Keys.NUM_5:
                setCell("portal:b");
                break;
            case Input.Keys.NUM_6:
                setCell("portal:x");
                break;
            case Input.Keys.NUM_7:
                setCell("portal:y");
                break;
            case Input.Keys.R:
                setRecursive();
                break;

            case Input.Keys.A:
                toggleAchievement();
                break;

            case Input.Keys.ENTER:
                // TODO: Go somewhere ...
//                nextLevel();
                break;

            case Input.Keys.BACKSPACE:
                goBack();
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
