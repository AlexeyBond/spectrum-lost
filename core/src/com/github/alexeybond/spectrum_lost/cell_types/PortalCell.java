package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;

/**
 *
 */
public class PortalCell implements ICellType {
    private final String typeName;
    private final String key;
    private final String otherKey;

    private PortalCell(String typeName, String key, String otherKey) {
        this.typeName = typeName;
        this.key = key;
        this.otherKey = otherKey;
    }

    @Override
    public void init(ICell cell) {
        // Remove previous portal of this type
        ICell prev = (ICell) cell.grid().attributes().get(key);

        if (null != prev) {
            prev.setType(cell.grid().defaultCellType());
        }

        // Save this portal
        cell.grid().attributes().put(key, cell);
    }

    @Override
    public void leave(ICell cell) {
        cell.grid().attributes().remove(key);
    }

    private void teleport(ICell cur, ICell partner, Direction thisDir, Direction pDir, int s) {
        Ray e = cur.emission(thisDir.next(s));
        e.set(partner.receive(pDir.next(s)));
        e.fade(1);
    }

    @Override
    public void update(ICell cell) {
        ICell partner = (ICell) cell.grid().attributes().get(otherKey);

        if (null != partner) {
            Direction thisDir = cell.direction();
            Direction pDir = partner.direction();
            teleport(cell, partner, thisDir, pDir, 0);
            teleport(cell, partner, thisDir, pDir, -1);
            teleport(cell, partner, thisDir, pDir, +1);
        }
    }

    @Override
    public String id() {
        return typeName;
    }

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    public static ICellType TYPE_A = new PortalCell("portal:a", "a", "b");
    public static ICellType TYPE_B = new PortalCell("portal:b", "b", "a");

    public static ICellType TYPE_X = new PortalCell("portal:x", "x", "y");
    public static ICellType TYPE_Y = new PortalCell("portal:y", "y", "x");
}
