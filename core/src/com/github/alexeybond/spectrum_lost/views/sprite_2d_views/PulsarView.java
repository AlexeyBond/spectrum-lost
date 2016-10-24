package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.cell_types.PulsarCell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;

/**
 *
 */
public class PulsarView extends $Sprite2DView {
    {bgTexture = loadTexture("game/cells/background/bg-common");}

    public PulsarView(final String fgName) {
        fgTexture = loadTexture(fgName);
    }

    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {
        super.draw(batch, cell, pos, size, layer);

        if (layer == 1) {
            PulsarCell.State state = (PulsarCell.State) cell.state();

            int dch = state.direct.charge;
            int rch = state.reverse.charge;

            PulsarCell.State.SubState mss = (dch > rch) ? state.direct : state.reverse;

            if (mss.charge > 0) {
                if (mss.emitting) {
                    drawSpinner(batch, pos, mss.getCharge(), size, 1);
                } else {
                    drawSpinner(batch, pos, mss.getCharge(), size, -1);
                }
            }
        }
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
