package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.interfaces.IExpectation;

/**
 * Expector cell view.
 */
public class ExpectorView extends $Sprite2DView {
    private Texture gearTexture;
    private Texture failTexture;
    private Texture successTexture;

    {
        // TODO: Do not use mirror's background
        bgTexture = loadTexture("cells/mirror/bg.png");
        fgTexture = loadTexture("cells/expector/fg.png");
        gearTexture = loadTexture("cells/expector/fg-gear.png");
        failTexture = loadTexture("cells/expector/symbol-fail.png");
        successTexture = loadTexture("cells/expector/symbol-success.png");
    }

    @Override
    public void draw(SpriteBatch batch, ICell cell, Vector2 pos, float size, int layer) {
        if (layer == 0) {
            super.draw(batch, cell, pos, size, layer);
            return;
        }

        float r = .001f * (float)TimeUtils.millis();
        Texture symbolTexture;

        if (((IExpectation) cell.state()).isDone()) {
            symbolTexture = successTexture;
        } else {
            symbolTexture = failTexture;
            r = -r;
        }

        drawSprite(batch, pos, size, fgTexture, 0);
        drawSprite(batch, pos, size, gearTexture, r);
        drawSprite(batch, pos, size, symbolTexture, 0);
    }
}
