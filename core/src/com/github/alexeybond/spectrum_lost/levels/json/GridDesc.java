package com.github.alexeybond.spectrum_lost.levels.json;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description of a grid (level).
 */
public class GridDesc {
    public ArrayList<CellDesc> cells;
    public int width, height;

    public HashMap<String, Object> attrs = new HashMap<String, Object>();

    public static GridDesc dump(final IGrid grid) {
        GridDesc gd = new GridDesc();

        gd.width = grid.width();
        gd.height = grid.height();
        gd.cells = new ArrayList<CellDesc>();
        gd.attrs = new HashMap<String, Object>(grid.attributes());

        for (int x = 0; x < gd.width; x++) {
            for (int y = 0; y < gd.height; y++) {
                ICell cell = grid.getCell(x, y);
                if ("empty".equals(cell.type().id())) continue;

                gd.cells.add(CellDesc.dump(cell));
            }
        }

        return gd;
    }
}
