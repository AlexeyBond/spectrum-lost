package com.github.alexeybond.spectrum_lost.views.sprite_2d_views;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.model.interfaces.ICell;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.views.CellView2D;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public abstract class $Sprite2DView implements CellView2D {
    private final static float[] spriteVertices;

    private static final float _2_SQRT2 = (float)(2. / Math.sqrt(2.));

    static {
        float white = Color.WHITE.toFloatBits();

        spriteVertices = new float[] {
                // x, y, color, u, v
                //  0, 1, 2, 3, 4
                0, 0, white, 0, 0,
                //  5, 6, 7, 8, 9
                0, 0, white, 1, 0,
                //  10,11,12,13,14
                0, 0, white, 1, 1,
                //  15,16,17,18,19
                0, 0, white, 0, 1,
        };
    }

    protected TextureRegion bgTexture = null;
    protected TextureRegion fgTexture = null;

    private final TextureRegion spinnerTexture = Resources.getSprite("game/fx/spinner-segment");

    private static final Matrix4 transformMatrix = new Matrix4();

    private static Map<String, Texture> textures = new HashMap<String, Texture>();

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

    protected TextureRegion loadTexture(final String name) {
        return Resources.getSprite(name);
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    protected void drawSprite(
            final SpriteBatch batch,
            final Vector2 pos,
            final float size,
            final TextureRegion tx,
            float rotation) {
        float hSize = size * .5f;

        if (rotation != 0.f) {
//            transformMatrix
//                    .translate(pos.x + hSize, pos.y + hSize, 0)
//                    .rotate(.0f, .0f, 1.f, rotation)
//                    .translate(-hSize, -hSize, 0);
//
//            batch.setTransformMatrix(transformMatrix);
//            batch.draw(tx, 0, 0, size, size);
//
//            // Keep matrix identity
//            transformMatrix.idt();
//            batch.setTransformMatrix(transformMatrix);

            // Manual calculation of vertex coordinates causes cpu load change from ~40% to ~23% as sprite batch is not
            // flushed after every sprite.
            rotation += 45f;
            rotation = MathUtils.degreesToRadians * rotation;
            // Tried to use MathUtils#(sin|cos)Deg but loss of accuracy causes artifacts @90,180,270 degrees
            float c = _2_SQRT2 * hSize * (float) Math.cos(-rotation);
            float s = _2_SQRT2 * hSize * (float) Math.sin(-rotation);

            spriteVertices[0*5+3] = tx.getU();
            spriteVertices[0*5+4] = tx.getV();

            spriteVertices[1*5+3] = tx.getU2();
            spriteVertices[1*5+4] = tx.getV();

            spriteVertices[2*5+3] = tx.getU2();
            spriteVertices[2*5+4] = tx.getV2();

            spriteVertices[3*5+3] = tx.getU();
            spriteVertices[3*5+4] = tx.getV2();

            spriteVertices[0*5+0] = pos.x + s + hSize;
            spriteVertices[0*5+1] = pos.y + c + hSize;

            spriteVertices[1*5+0] = pos.x + c + hSize;
            spriteVertices[1*5+1] = pos.y - s + hSize;

            spriteVertices[2*5+0] = pos.x - s + hSize;
            spriteVertices[2*5+1] = pos.y - c + hSize;

            spriteVertices[3*5+0] = pos.x - c + hSize;
            spriteVertices[3*5+1] = pos.y + s + hSize;

            batch.draw(tx.getTexture(), spriteVertices, 0, spriteVertices.length);
        } else {
            batch.draw(tx, pos.x, pos.y, size, size);
        }
    }

    protected float angleFromDirection(final Direction dir) {
        return -45.f * (float)dir.n;
    }

    protected void drawSpinner(final SpriteBatch batch, final Vector2 pos, final float fill, final float size, final int d) {
        for (int i = 0; i < 8; i++) {
            if (fill < ((float)i) * .125) break;

            drawSprite(batch, pos, size, spinnerTexture, (float)(d * i * 45));
        }
    }

    @Override
    public void draw(final SpriteBatch batch, final ICell cell, final Vector2 pos, final float size, final int layer) {
        TextureRegion tx = (layer==0)?bgTexture:fgTexture;

        if (tx == null) {
            return;
        }

        drawSprite(batch, pos, size, tx, (layer == 0)?0:angleFromDirection(cell.direction()));
    }
}
