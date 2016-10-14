package com.github.alexeybond.spectrum_lost.levels.json;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

import java.util.HashMap;

/**
 * Description of a single cell.
 */
public class CellDesc {
    public int x, y;
    public String type;
    public Direction direction = Direction.DEFAULT;
    public HashMap<String, Object> attrs;

    public static CellDesc dump(final ICell cell) {
        CellDesc cd = new CellDesc();

        cd.x = cell.x();
        cd.y = cell.y();
        cd.type = cell.type().id();

        if (cell.type().getAttribute("noTurn") == null
                || cell.type().getAttribute("editorTurn") != null) {
            cd.direction = cell.direction();
        }

        if (cell.getOwnAttributes().size() != 0) {
            cd.attrs = new HashMap<String, Object>();
            cd.attrs.putAll(cell.getOwnAttributes());
        }

        return cd;
    }
}
