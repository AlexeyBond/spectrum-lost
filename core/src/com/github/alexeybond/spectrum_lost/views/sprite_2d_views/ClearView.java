package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.views.CellView2D;

/**
 * View of a cell where is no cell.
 */
public class ClearView implements CellView2D {
    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {

    }

    @Override
    public boolean ignoreBackground() {
        return true;
    }

    @Override
    public boolean enableFlare() {
        return false;
    }
}
