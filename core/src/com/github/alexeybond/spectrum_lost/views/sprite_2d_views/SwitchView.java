package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.cell_types.SwitchCell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;

/**
 * View of a switch.
 */
public class SwitchView extends $Sprite2DView {
    private Texture mgTexture;

    {
        bgTexture = loadTexture("cells/switch/bg.png");
        mgTexture = loadTexture("cells/switch/mg.png");
        fgTexture = loadTexture("cells/switch/fg.png");
    }

    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {
        if (layer == 0) {
            drawSprite(batch, pos, size, bgTexture, angleFromDirection(cell.direction()));
            return;
        }

        drawSprite(batch, pos, size, mgTexture, angleFromDirection(cell.direction()));

        SwitchCell.State state = (SwitchCell.State) cell.state();

        Direction n = state.isTurned ? cell.direction().prev() : cell.direction().next();

        drawSprite(batch, pos, size, fgTexture, angleFromDirection(n));

        if (state.fChanged() != 0) {
            drawSpinner(batch, pos, state.fChanged(), size, state.isTurned ? -1 : 1);
        }
    }

    @Override
    public boolean enableFlare() {
        return true;
    }
}
