package com.github.alexeybond.spectrum_lost.levels.hardcoded;

import com.github.alexeybond.spectrum_lost.levels.ILevel;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.model.implementation.GameStateImpl;
import com.github.alexeybond.spectrum_lost.model.implementation.GridImpl;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IGrid;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class Source implements ILevelsSource {
    private List<ILevel> levels = new ArrayList<ILevel>();

    protected boolean backwardCompatible = false;

    protected final class ObjectDesc {
        final int x, y;
        final Direction dir;
        final String type;

        ObjectDesc(int x_, int y_, Direction dir_, String t_) {
            this.x = x_;
            this.y = y_;
            this.dir = dir_;
            this.type = t_;
        }
    }

    private class HardcodedLevel implements ILevel {
        private final int w, h;
        private final ObjectDesc[] objs;

        HardcodedLevel(int w_, int h_, ObjectDesc... o) {
            w = w_; h = h_;
            objs = o;
        }

        @Override
        public IGrid init() {
            IGrid grid = new GridImpl(w, h, "empty", new GameStateImpl());

            for (ObjectDesc od : objs) {
                ICell cell = grid.getCell(od.x, backwardCompatible?(h - 1 - od.y):od.y);

                cell.setDirection(od.dir);
                cell.setType(Locator.CELL_TYPES.get(od.type));
            }

            return grid;
        }
    }

    @Override
    public Iterator<ILevel> iterator() {
        return levels.iterator();
    }


    ObjectDesc O(int x, int y, String o) {
        return new ObjectDesc(x, y, Direction.DEFAULT, o);
    }

    ObjectDesc W(int x, int y) {return O(x, y, WALLCELL);}

    ObjectDesc O(int x, int y, String o, Direction dir) {
        return new ObjectDesc(x, y, dir, o);
    }

    void L(int xsz_, int ysz_, ObjectDesc... objs_) {
        levels.add(new HardcodedLevel(xsz_, ysz_, objs_));
    }

    protected static final String WALLCELL = "wall";
    protected static final String MIRRORCELL = "mirror";
    protected static final String PRISMCELL = "prism";
    protected static final String ANYEXPECTORCELL = "expector";
    protected static final String EMITTERCELL = "emitter";
}
