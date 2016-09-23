package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.cell_types.ExpectorCell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IExpectation;

/**
 * Expector cell view.
 */
public class ExpectorView extends $Sprite2DView {
    private Texture gearTexture;
    private Texture failTexture;
    private Texture successTexture;
    private Texture waitTexture;

    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/expector/fg.png");
        gearTexture = loadTexture("cells/expector/fg-gear.png");
        failTexture = loadTexture("cells/expector/symbol-fail.png");
        successTexture = loadTexture("cells/expector/symbol-success.png");
        waitTexture = loadTexture("cells/expector/symbol-wait.png");
    }

    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {
        if (layer == 0) {
            super.draw(batch, cell, pos, size, layer);
            return;
        }

        float r = .1f * (float) (TimeUtils.millis() % 3600);
        Texture symbolTexture;

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
}
