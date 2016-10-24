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
        reg(ExpectorCell.ANY);
        reg(ExpectorCell.NONE);
        reg(ExpectorCell.R);
        reg(ExpectorCell.G);
        reg(ExpectorCell.B);
        reg(new WallCell());
        reg(new MixerCell());
        reg(new SwitchCell());
        reg(new ShifterCell());
        reg(new FaderCell());
        reg(new MultiplierCell());
        reg(new HMirrorCell());
        reg(new ClearCell());
        reg(PortalCell.TYPE_A);
        reg(PortalCell.TYPE_B);
        reg(PortalCell.TYPE_X);
        reg(PortalCell.TYPE_Y);
        reg(new RecursiveCell());
        reg(PulsarCell.TYPE_A);
        reg(PulsarCell.TYPE_B);
    }
}
