package com.github.alexeybond.spectrum_lost.screens.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.resources.Resources;

import java.util.Locale;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 */
public class AnimatedBackground {
    private TextureRegion[] sprites;
    private final int tileSize;

    private float focusX, focusY;

    private float velocityX, velocityY;

    private SpriteBatch spriteBatch;

    private float scale = 1.0f;

    public float opacity = 1f;
    public float kVelocity = 1f;

    public AnimatedBackground(
            final String namePrefix,
            final int nSprites,
            final int tileSize) {
        this.tileSize = tileSize;

        sprites = new TextureRegion[nSprites];

        for (int i = 0; i < nSprites; i++) {
            // Why the fuck this does not work in GWT???
//            sprites[i] = Resources.getSprite(namePrefix.concat(String.format("-%02d", i)));
            sprites[i] = Resources.getSprite(namePrefix.concat("-0").concat(String.valueOf(i)));
        }

        spriteBatch = (SpriteBatch) Locator.RENDERER_OBJECT.get("sprite batch");
    }

    public void draw() {
        int tileSize = (int) (scale * (float)this.tileSize);

        int nTilesX = 2 + Gdx.graphics.getWidth() / tileSize;
        int nTilesY = 2 + Gdx.graphics.getHeight() / tileSize;

        int ofX = (int) Math.floor(focusX);
        int ofY = (int) Math.floor(focusY);

        float frOfX = focusX - (float) ofX;
        float frOfY = focusY - (float) ofY;

        spriteBatch.setColor(1,1,1,opacity);

        for (int i = 0; i < nTilesX; i++) {
            for (int j = 0; j < nTilesY; j++) {
                spriteBatch.draw(
                        pickRegion(i + ofX, j + ofY),
                        (-frOfX + (float) (i)) * tileSize,
                        (-frOfY + (float) (j)) * tileSize,
                        tileSize, tileSize
                );
            }
        }

        float dt = Gdx.graphics.getDeltaTime();

        focusX += velocityX * dt * kVelocity;
        focusY += velocityY * dt * kVelocity;

        float vk = (float) Math.pow(.1f, dt);

        velocityX = vk * velocityX;
        velocityY = vk * velocityY;

        float at = getAnimationTick();

        float vdk = dt * 128f / (float) tileSize;

        velocityX += (sin(6f * at * Math.PI) + cos(24f * at * Math.PI)) * vdk;
        velocityY += (sin(12f * at * Math.PI) - cos(8f * at * Math.PI)) * vdk;
    }

    public void scale(final float scale) {
        this.scale = scale;
    }

    public void applyImpulse(float x, float y) {
        velocityX += x * scale / (float) tileSize;
        velocityY += y * scale / (float) tileSize;
    }

    private TextureRegion pickRegion(int x, int y) {
        int n = x * 29 + y * 83;
        return sprites[(n ^ n >> 3 ^ n >> 6 ^ n >> 9) % sprites.length];
    }

    private float getAnimationTick() {
        long mm = 0x7FFF;
        float d = (float) mm;
        return ((float) (TimeUtils.millis() & mm)) / d;
    }
}
