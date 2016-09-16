package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public abstract class $Screen {
    private class InputProcessorDecorator implements InputProcessor {
        private final InputProcessor decorated;

        InputProcessorDecorator(final InputProcessor decorated) {
            this.decorated = decorated;
        }

        @Override
        public boolean keyDown(int keycode) {
            $Screen.this.onKeyPress(keycode);
            decorated.keyDown(keycode);
            return true;
        }

        @Override
        public boolean keyUp(int keycode) {
            return decorated.keyUp(keycode);
        }

        @Override
        public boolean keyTyped(char character) {
            $Screen.this.onCharTyped(character);
            decorated.keyTyped(character);
            return true;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return decorated.touchDown(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return decorated.touchUp(screenX, screenY, pointer, button);
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return decorated.touchDragged(screenX, screenY, pointer);
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return decorated.mouseMoved(screenX, screenY);
        }

        @Override
        public boolean scrolled(int amount) {
            $Screen.this.onScroll(amount);
            decorated.scrolled(amount);
            return true;
        }
    }

    private class GestureListener implements GestureDetector.GestureListener {
        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            if (count != 0) {
                $Screen.this.onClick(x, Gdx.graphics.getHeight() - y);
                return true;
            }
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            $Screen.this.onDrag(x, Gdx.graphics.getHeight() - y, deltaX, -deltaY);
            return true;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            return false;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {
        }
    }

    protected static SpriteBatch spriteBatch;
    private $Screen prev;
    private $Screen next;

    protected final InputProcessor inputProcessor;

    protected $Screen() {
        if (spriteBatch == null) spriteBatch = new SpriteBatch();

        inputProcessor = new InputProcessorDecorator(new GestureDetector(new GestureListener()));
    }

    public void resize(final int width, final int height) {
        Gdx.gl.glViewport(0, 0, width, height);
        spriteBatch.getProjectionMatrix()
                .setToOrtho2D(0, 0, width, height);
        spriteBatch.setProjectionMatrix(spriteBatch.getProjectionMatrix());
    }

    public abstract void draw();

    public final $Screen next() {
        return next;
    }

    public final void next(final $Screen screen) {
        next = screen;
    }

    public final boolean goBack() {
        if (prev != null) {
            next = prev;
            return true;
        }
        return false;
    }

    public void pause() {

    }

    public void unpause() {

    }

    public void show(final $Screen prev) {
        this.prev = prev;
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public void leave(final $Screen next) {
        Gdx.input.setInputProcessor(null);
    }

    protected void onClick(final float x, final float y) {}
    protected void onDrag(final float x, final float y, final float dx, final float dy) {}
    protected void onKeyPress(final int code) {}
    protected void onCharTyped(final char chr) {}
    protected void onScroll(final int amount) {}
}
