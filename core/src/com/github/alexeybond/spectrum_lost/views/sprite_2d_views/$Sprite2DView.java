package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.views.CellView2D;

/**
 *
 */
public abstract class $Sprite2DView implements CellView2D {
    protected Texture bgTexture = null;
    protected Texture fgTexture = null;

    private static final Matrix4 transformMatrix = new Matrix4();

    @Override
    public boolean ignoreBackground() {
        // By default draw the background
        return false;
    }

    @Override
    public boolean enableFlare() {
        // By default disable flare sprite
        return false;
    }

    protected Texture loadTexture(final String name) {
        Texture tx = new Texture(Gdx.files.internal(name), true);
        tx.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
        return tx;
    }

    protected void drawSprite(
            final SpriteBatch batch,
            final Vector2 pos,
            final float size,
            final Texture tx,
            final float rotation) {
        float hSize = size * .5f;

        if (rotation != 0.f) {
            transformMatrix
                    .translate(pos.x + hSize, pos.y + hSize, 0)
                    .rotate(.0f, .0f, 1.f, rotation)
                    .translate(-hSize, -hSize, 0);

            batch.setTransformMatrix(transformMatrix);
            batch.draw(tx, 0, 0, size, size);

            // Keep matrix identity
            transformMatrix.idt();
            batch.setTransformMatrix(transformMatrix);
        } else {
            batch.draw(tx, pos.x, pos.y, size, size);
        }
    }

    protected float angleFromDirection(final Direction dir) {
        return -45.f * (float)dir.n;
    }

    @Override
    public void draw(final SpriteBatch batch, final ICell cell, final Vector2 pos, final float size, final int layer) {
        Texture tx = (layer==0)?bgTexture:fgTexture;

        if (tx == null) {
            return;
        }

        drawSprite(batch, pos, size, tx, (layer == 0)?0:angleFromDirection(cell.direction()));
    }
}
