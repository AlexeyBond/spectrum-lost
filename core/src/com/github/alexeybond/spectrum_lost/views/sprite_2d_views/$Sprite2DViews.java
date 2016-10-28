package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.views.CellView2D;

/**
 *
 */
public class $Sprite2DViews {
    private static void reg(final String type, final CellView2D view) {
        Locator.CELL_VIEWS.set(type, view);
    }

    public static void register() {
        reg("clear", new ClearView());
        reg("empty", new EmptyCellView());
        reg("wall", new WallView());

        // Expectors
        reg("expector", new ExpectorView.AnyExpectorView());
        reg("expector:none", new ExpectorView.NoneExpectorView());
        reg("expector:r", new ExpectorView.RExpectorView());
        reg("expector:g", new ExpectorView.GExpectorView());
        reg("expector:b", new ExpectorView.BExpectorView());

        reg("switch", new SwitchView());

        // Simple views
        reg("emitter", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/emitter/fg", false));
        reg("mirror", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/mirror/fg", true));
        reg("prism", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/prism/fg", true));
        reg("mixer", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/mixer/fg", true));
        reg("shifter", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/shifter/fg", true));
        reg("fader", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/fader/fg", true));
        reg("multiplier", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/multiplier/fg", true));
        reg("hmirror", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/hmirror/fg", true));

        reg("portal:a", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/portal/fg-o", true));
        reg("portal:b", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/portal/fg-b", true));
        reg("portal:x", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/portal/fg-v", true));
        reg("portal:y", new $SimpleSpriteView("game/cells/background/bg-common", "game/cells/portal/fg-g", true));

        reg("recursive", new RecursiveCellView());

        reg("pulsar", new PulsarView("game/cells/pulsar/fg"));
        reg("pulsar-b", new PulsarView("game/cells/pulsar/fg-b"));
        reg("pulsar-c", new PulsarView("game/cells/pulsar/fg-c"));
    }
}
