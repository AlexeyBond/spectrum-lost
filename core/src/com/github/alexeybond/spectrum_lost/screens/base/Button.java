package com.github.alexeybond.spectrum_lost.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.alexeybond.spectrum_lost.resources.Resources;

/**
 *
 */
public class Button {
    private TextureRegion texture;
    private boolean visible = true;
    private ButtonListener listener;
    private Rectangle rect;
    private int x, y;

    Button(ButtonListener listener, int x, int y) {
        this.listener = listener;
        this.rect = new Rectangle();
        this.x = x;
        this.y = y;
        recalc();
    }

    public void recalc() {
        texture = Resources.getSprite(listener.getSprite(this));

        int margin = 20;

        int x = (this.x < 0)
                ?(Gdx.graphics.getWidth() - (margin*2 + this.width()) * (-this.x) + margin)
                :(margin + (this.width() + 2*margin) * this.x);
        int y = (this.y < 0)
                ?(Gdx.graphics.getHeight() - (margin*2 + this.width()) * (-this.y) + margin)
                :(margin + (this.height() + 2*margin) * this.y);

        this.rect.set(x,y,this.width(),this.height());
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            batch.draw(texture, rect.x, rect.y, rect.width, rect.height);
        }
    }

    public boolean onClick(int x, int y) {
        if (visible && rect.contains(x,y)) {
            listener.press(this);
            recalc();
            return true;
        }

        return false;
    }

    public int width() {
        return texture.getRegionWidth();
    }

    public int height() {
        return texture.getRegionHeight();
    }

    public void show(boolean show) {
        this.visible = show;
    }
}
