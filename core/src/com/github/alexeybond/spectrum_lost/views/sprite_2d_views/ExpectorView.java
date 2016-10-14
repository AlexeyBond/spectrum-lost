package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.cell_types.ExpectorCell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IExpectation;

/**
 * Expector cell view.
 */
public class ExpectorView extends $Sprite2DView {
    private TextureRegion gearTexture;
    private TextureRegion failTexture;
    private TextureRegion successTexture;
    private TextureRegion waitTexture;

    protected ExpectorView(final String fgImg) {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("game/cells/background/bg-common");
        fgTexture = loadTexture(fgImg);
        gearTexture = loadTexture("game/cells/expector/fg-gear");
        failTexture = loadTexture("game/cells/expector/symbol-fail");
        successTexture = loadTexture("game/cells/expector/symbol-success");
        waitTexture = loadTexture("game/cells/expector/symbol-wait");
    }

    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {
        if (layer == 0) {
            super.draw(batch, cell, pos, size, layer);
            return;
        }

        float r = .1f * (float) (TimeUtils.millis() % 3600);
        TextureRegion symbolTexture;

        ExpectorCell.State state = (ExpectorCell.State) cell.state();

        if (state.expectation.isDone()) {
            symbolTexture = successTexture;
            r = -r;
        } else if (state.getCharge() != 0) {
            symbolTexture = waitTexture;
        } else {
            symbolTexture = failTexture;
        }

        drawSprite(batch, pos, size, fgTexture, 0);
        drawSprite(batch, pos, size, gearTexture, r);
        drawSprite(batch, pos, size, symbolTexture, 0);

        if (!state.expectation.isDone() && state.getCharge() != 0) {
            drawSpinner(batch, pos, state.getCharge(), size, -1);
        }
    }

    public static class AnyExpectorView extends ExpectorView {
        public AnyExpectorView() {
            super("game/cells/expector/fg");
        }
    }

    public static class NoneExpectorView extends ExpectorView {
        public NoneExpectorView() {
            super("game/cells/expector/fg-type-none");
        }
    }

    public static class RExpectorView extends ExpectorView {
        public RExpectorView() {
            super("game/cells/expector/fg-type-r");
        }
    }

    public static class GExpectorView extends ExpectorView {
        public GExpectorView() {
            super("game/cells/expector/fg-type-g");
        }
    }

    public static class BExpectorView extends ExpectorView {
        public BExpectorView() {
            super("game/cells/expector/fg-type-b");
        }
    }
}
