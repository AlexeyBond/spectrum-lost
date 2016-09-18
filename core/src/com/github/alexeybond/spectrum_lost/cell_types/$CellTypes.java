package com.github.alexeybond.spectrum_lost.cell_types;

import com.github.alexeybond.spectrum_lost.model.interfaces.ICellType;
import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;

/**
 *
 */
public class $CellTypes {
    private static void reg(final ICellType type) {
        Locator.CELL_TYPES.set(type.id(), type);
    }

    public static void register() {
        reg(new EmptyCell());
        reg(new EmitterCell());
        reg(new PrismCell());
        reg(new MirrorCell());
        reg(new ExpectorCell());
        reg(new WallCell());
        reg(new MixerCell());
        reg(new SwitchCell());
    }
}
