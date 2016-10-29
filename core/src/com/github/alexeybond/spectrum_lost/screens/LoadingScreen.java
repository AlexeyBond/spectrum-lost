package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.screens.base.$Screen;

/**
 *
 */
public class LoadingScreen extends $Screen {
    @Override
    public void draw() {
        if (Resources.manager().loadNext()) {
            goBack();
        }

        shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        shapeRenderer.getTransformMatrix().idt();
        shapeRenderer.updateMatrices();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        float offst = .2f;
        float lw = Math.max(10f, Math.min(w,h) * .02f);

        float hlw = .5f * lw;

        float x1 = offst * w, x2 = w - offst * w;
        float y1 = offst * h, y2 = h - offst * h;

        float p = Resources.manager().getLoadProgress();
        float np = 1f - p;

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(1,0,0,1);
        shapeRenderer.rectLine(x2,h,x2,p*(y1-hlw)+np*h,lw);
        shapeRenderer.setColor(0,1,0,1);
        shapeRenderer.rectLine(w,y1,p*(x1-hlw)+np*w,y1,lw);
        shapeRenderer.setColor(0,0,1,1);
        shapeRenderer.rectLine(x1,0,x1,(y2-hlw)*p,lw);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rectLine(0f,y2,(x2-hlw)*p,y2,lw);

        shapeRenderer.end();
    }
}
