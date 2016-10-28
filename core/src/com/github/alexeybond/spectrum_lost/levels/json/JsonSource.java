package com.github.alexeybond.spectrum_lost.levels.json;

import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChapterLevelsDesc;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.locator.Locator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 *
 */
public class JsonSource implements ILevelsSource {
    private final ChapterLevelsDesc levels;
    private final Map<String, Object> attrs;

    @Override
    public IGrid initLevel(String name) {
        GridDesc desc = levels.levels.get(name);

        if (desc == null) {
            throw new NoSuchElementException("No such level: ".concat(name));
        }

        IGrid grid = new GridImpl(desc.width, desc.height, "empty", new GameStateImpl());

        for (CellDesc cd : desc.cells) {
            ICell cell = grid.getCell(cd.x, cd.y);

            cell.getOwnAttributes().clear();

            if (cd.attrs != null) {
                cell.getOwnAttributes().putAll(cd.attrs);
            }

            cell.setDirection(cd.direction);
            cell.setType(Locator.CELL_TYPES.get(cd.type));
        }

        grid.attributes().putAll(desc.attrs);

        return grid;
    }

    @Override
    public String rootLevelName() {
        return levels.rootLevelName;
    }

    @Override
    public Object getAttribute(String name) {
        return attrs.get(name);
    }

    @Override
    public List<String> enumLevels() {
        return new LinkedList<String>(levels.levels.keySet());
    }

    public JsonSource(final ChapterLevelsDesc levels, final Map<String, Object> attrs) {
        this.levels = levels;
        this.attrs = attrs;
    }
}
