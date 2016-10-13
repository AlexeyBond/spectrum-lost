package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.github.alexeybond.spectrum_lost.model.interfaces.Locator;
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
        reg("emitter", new $SimpleSpriteView("cells/mirror/bg.png", "cells/emitter/fg.png", false));
        reg("mirror", new $SimpleSpriteView("cells/mirror/bg.png", "cells/mirror/fg.png", true));
        reg("prism", new $SimpleSpriteView("cells/mirror/bg.png", "cells/prism/fg.png", true));
        reg("mixer", new $SimpleSpriteView("cells/mirror/bg.png", "cells/mixer/fg.png", true));
        reg("shifter", new $SimpleSpriteView("cells/mirror/bg.png", "cells/shifter/fg.png", true));
        reg("fader", new $SimpleSpriteView("cells/mirror/bg.png", "cells/fader/fg.png", true));
        reg("multiplier", new $SimpleSpriteView("cells/mirror/bg.png", "cells/multiplier/fg.png", true));
        reg("hmirror", new $SimpleSpriteView("cells/mirror/bg.png", "cells/hmirror/fg.png", true));

        reg("portal:a", new $SimpleSpriteView("cells/mirror/bg.png", "cells/portal/fg-o.png", true));
        reg("portal:b", new $SimpleSpriteView("cells/mirror/bg.png", "cells/portal/fg-b.png", true));
        reg("portal:x", new $SimpleSpriteView("cells/mirror/bg.png", "cells/portal/fg-v.png", true));
        reg("portal:y", new $SimpleSpriteView("cells/mirror/bg.png", "cells/portal/fg-g.png", true));
    }
}
