package com.github.alexeybond.spectrum_lost.levels.json;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.github.alexeybond.spectrum_lost.levels.ILevel;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class JsonSource implements ILevelsSource {
    private ArrayList<ILevel> levels;

    private class JSLevel implements ILevel {
        private final GridDesc desc;

        JSLevel(final GridDesc desc) {
            this.desc = desc;
        }

        @Override
        public IGrid init() {
            IGrid grid = new GridImpl(desc.width, desc.height, "empty", new GameStateImpl());

            for (CellDesc cd : desc.cells) {
                ICell cell = grid.getCell(cd.x, cd.y);

                cell.getOwnAttributes().clear();
                cell.getOwnAttributes().putAll(cd.attrs);
                cell.setDirection(cd.direction);
                cell.setType(Locator.CELL_TYPES.get(cd.type));
            }

            return grid;
        }
    }

    public JsonSource(final FileHandle handle) {
        String str = handle.readString();
        Json json = new Json();
        ChapterDesc cd = json.fromJson(ChapterDesc.class, str);
        levels = new ArrayList<ILevel>(cd.levelNames.size());
        FileHandle ph = handle.parent();

        for (String lLoc : cd.levelNames) {
            String strL = ph.child(lLoc.concat(".json")).readString();
            levels.add(new JSLevel(json.fromJson(GridDesc.class, strL)));
        }
    }

    public JsonSource(final String intName) {
        this(Gdx.files.internal(intName));
    }

    @Override
    public Iterator<ILevel> iterator() {
        return levels.iterator();
    }
}