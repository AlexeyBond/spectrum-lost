package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;

/**
 *
 */
public class $Sprite2DViews {
    private static void reg(final String type, final $Sprite2DView view) {
        Locator.CELL_VIEWS.set(type, view);
    }

    public static void register() {
        reg("empty", new EmptyCellView());
        reg("wall", new WallView());
        reg("emitter", new EmitterView());
        reg("expector", new ExpectorView());
        reg("prism", new PrismView());
        reg("mirror", new MirrorView());
        reg("mixer", new MixerView());
        reg("switch", new SwitchView());
        reg("shifter", new ShifterView());
    }
}
