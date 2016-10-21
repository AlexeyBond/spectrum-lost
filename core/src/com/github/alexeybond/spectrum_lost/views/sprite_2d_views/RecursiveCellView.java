package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.cell_types.RecursiveCell;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.resources.Resources;

/**
 * View of recursive cell.
 */
public class RecursiveCellView extends $Sprite2DView {
    private TextureRegion fgRedRegion = Resources.getSprite("game/cells/recursive/fg-red");
    private TextureRegion fgGreenRegion = Resources.getSprite("game/cells/recursive/fg-green");
    private TextureRegion fgYellowRegion = Resources.getSprite("game/cells/recursive/fg-yellow");
    private TextureRegion fgErrorRegion = Resources.getSprite("game/cells/recursive/fg-error");

    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {
        if (layer == 1) {
            RecursiveCell.State state = (RecursiveCell.State) cell.state();

            TextureRegion sprite = fgRedRegion;

            if (state.isError()) {
                sprite = fgErrorRegion;
            } else if (state.isCompleted()) {
                sprite = fgGreenRegion;
            } else if (state.isOpen()) {
                sprite = fgYellowRegion;
            }

            drawSprite(batch, pos, size, sprite, 0);
        } else {
            super.draw(batch, cell, pos, size, layer);
        }
    }
}
