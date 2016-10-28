package com.github.alexeybond.spectrum_lost.renderer.two_dimensional.ray;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.model.util.Direction;
import com.github.alexeybond.spectrum_lost.model.util.Ray;
import com.github.alexeybond.spectrum_lost.renderer.two_dimensional.IRayRenderer;
import com.github.alexeybond.spectrum_lost.resources.Resources;

import java.util.NoSuchElementException;

import static java.lang.Math.min;
import static java.lang.Math.sin;

/**
 * Ray renderer that draws animated ray to FBO and uses it to draw rays.
 */
public class FboRayRenderer implements IRayRenderer {
    private static int FBO_SIZE_X = 128;
    private static int FBO_SIZE_Y = 128;

    private FrameBuffer frameBuffer;
    private TextureRegion flareTexture;
    private ShapeRenderer shapeRenderer = (ShapeRenderer) Locator.RENDERER_OBJECT.get("shape renderer");

    private static float[] animation = new float[FBO_SIZE_X];

    private static final float[] sv = new float[] {
            // x, y, color, u, v
            //  0, 1, 2, 3, 4
                0, 0, 0, 0, 0,
            //  5, 6, 7, 8, 9
                0, 0, 0, 1, 0,
            //  10,11,12,13,14
                0, 0, 0, 1, 1,
            //  15,16,17,18,19
                0, 0, 0, 0, 1,
    };

    private static FrameBuffer initFBO() {
        try {
            return (FrameBuffer) Locator.RENDERER_OBJECT.get("ray renderer fbo");
        } catch (NoSuchElementException e) {
            FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, FBO_SIZE_X, FBO_SIZE_Y, false, false);

            fbo.getColorBufferTexture().setFilter(
                    Texture.TextureFilter.MipMapLinearLinear,
                    Texture.TextureFilter.MipMapLinearLinear);

            Locator.RENDERER_OBJECT.set("ray renderer fbo", fbo);

            return  fbo;
        }
    }

    public FboRayRenderer() {
        frameBuffer = initFBO();
        flareTexture = Resources.getSprite("game/fx/flare-00");
    }

    private void animate() {
        float tm = .01f * (float) (TimeUtils.millis() & 0xFFFF);

        float kt = (float) sin(.5f * tm);

        for (int x = 0; x < FBO_SIZE_X; x++) {
            float n = (float) sin(((float)x) * .2 - tm) * .15f;
            float na = 0; // TODO: Get from sound buffer
            float k_ = 1.f - (((float)min(x, FBO_SIZE_X-x)) / (float)FBO_SIZE_X);
            float k = 1.f - k_ * k_;
            animation[x] = k * (kt * n + na) * 1.4f * .5f * (float) FBO_SIZE_Y;
        }
    }

    private void drawLine(final float width, final float brightness, final float ky) {
        shapeRenderer.setColor(brightness, brightness, brightness, brightness);
        Gdx.gl.glLineWidth(width);
        float hySz = .5f * (float) FBO_SIZE_Y;

        shapeRenderer.line(-1, hySz, 0, animation[0] * ky + hySz);

        for (int x = 1; x < FBO_SIZE_X; x++) {
            shapeRenderer.line(x-1, animation[x-1] * ky + hySz, x, animation[x] * ky + hySz);
        }

        shapeRenderer.line(FBO_SIZE_X, hySz, FBO_SIZE_X - 1, animation[FBO_SIZE_X-1] * ky + hySz);

        shapeRenderer.flush();
    }

    @Override
    public void prepareFrame() {
        animate();
        frameBuffer.begin();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.getProjectionMatrix().idt().setToOrtho2D(0, 0, FBO_SIZE_X, FBO_SIZE_Y);
        shapeRenderer.getTransformMatrix().idt();
        shapeRenderer.updateMatrices();
        shapeRenderer.setColor(1f, 1f, 1f, 1f);
        drawLine(5, .4f, .2f);
        drawLine(3, .6f, .3f);
        drawLine(2, .95f, -.8f);
        shapeRenderer.end();
        frameBuffer.end();

        Texture cbt = frameBuffer.getColorBufferTexture();

        cbt.bind();
        Gdx.gl.glGenerateMipmap(cbt.glTarget);
    }

    @Override
    public void beginRays(SpriteBatch batch) {
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
    }

    @Override
    public void endRays(SpriteBatch batch) {
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void drawRay(
            final SpriteBatch batch,
            final Vector2 fromPos,
            final Direction dir,
            final Ray ray,
            final float cellSize) {
        Direction pd = dir.perpendicular();
        final float hw = cellSize * .5f;

        float srcX = fromPos.x;
        float srcY = fromPos.y;
        float dstX = srcX + (cellSize + .5f) * (float) dir.h;
        float dstY = srcY + (cellSize + .5f) * (float) dir.v;
        float dX = hw * (float) pd.h;
        float dY = hw * (float) pd.v;
        float color = Color.toFloatBits(
                (float) ray.getR() / (float) Ray.MAX_BRIGHTNESS,
                (float) ray.getG() / (float) Ray.MAX_BRIGHTNESS,
                (float) ray.getB() / (float) Ray.MAX_BRIGHTNESS,
                1.f);
        float colorF = Color.toFloatBits(
                (float) Ray.clamp(ray.getR() - 1) / (float) Ray.MAX_BRIGHTNESS,
                (float) Ray.clamp(ray.getG() - 1) / (float) Ray.MAX_BRIGHTNESS,
                (float) Ray.clamp(ray.getB() - 1) / (float) Ray.MAX_BRIGHTNESS,
                1.f);

        sv[2] = sv[17] = color;
        sv[12] = sv[7] = colorF;
        sv[0] = srcX - dX; sv[1] = srcY - dY;
        sv[5] = dstX - dX; sv[6] = dstY - dY;
        sv[10] = dstX + dX; sv[11] = dstY + dY;
        sv[15] = srcX + dX; sv[16] = srcY + dY;

        batch.draw(frameBuffer.getColorBufferTexture(), sv, 0, sv.length);
    }

    @Override
    public void drawFlare(SpriteBatch batch, Vector2 pos, float cellSize, Ray raySum) {
        float hsz = .5f * cellSize;
        batch.draw(flareTexture, pos.x - hsz, pos.y - hsz, cellSize, cellSize);
    }
}
