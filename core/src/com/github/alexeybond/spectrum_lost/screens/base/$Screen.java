package com.github.alexeybond.spectrum_lost.screens.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.github.alexeybond.spectrum_lost.locator.Locator;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.screens.LoadingScreen;

import java.util.ArrayList;
import java.util.List;

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
        private boolean pinch = false;
        private float prevZoom;

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
            float curZoom = (float) Math.sqrt((double)(pointer1.dst2(pointer2) / initialPointer1.dst2(initialPointer2)));

            int x = (int)(.5f * (pointer1.x + pointer2.x));
            int y = (int)(.5f * (pointer1.y + pointer2.y));

            if (pinch) {
                $Screen.this.onZoom(x, y, curZoom / prevZoom);
            } else {
                $Screen.this.onZoom(x, y, curZoom);
                pinch = true;
            }

            prevZoom = curZoom;

            return true;
        }

        @Override
        public void pinchStop() {
            pinch = false;
        }
    }

    protected SpriteBatch spriteBatch;
    protected ShapeRenderer shapeRenderer;
    private $Screen prev;
    private boolean freezePrev = false;
    private $Screen next;
    private List<Button> buttons = new ArrayList<Button>();

    protected final InputProcessor inputProcessor;

    protected $Screen() {
        spriteBatch = (SpriteBatch) Locator.RENDERER_OBJECT.get("sprite batch");
        shapeRenderer = (ShapeRenderer) Locator.RENDERER_OBJECT.get("shape renderer");

        inputProcessor = new InputProcessorDecorator(new GestureDetector(new GestureListener()));
    }

    public void resize(final int width, final int height) {
        Gdx.gl.glViewport(0, 0, width, height);
        spriteBatch.getProjectionMatrix()
                .setToOrtho2D(0, 0, width, height);
        spriteBatch.setProjectionMatrix(spriteBatch.getProjectionMatrix());

        for (Button button : buttons) {
            button.recalc();
        }
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

    protected final void rememberWayBack() {
        freezePrev = true;
    }

    protected Button addButton(int x, int y, ButtonListener listener) {
        Button button = new Button(listener, x, y);
        buttons.add(button);
        return button;
    }

    protected void drawButtons() {
        for (Button button : buttons) {
            button.draw(spriteBatch);
        }
    }

    public void pause() {

    }

    public void unpause() {

    }

    public void show(final $Screen prev) {
        if (this.prev == null || !freezePrev) {
            this.prev = prev;
        }

        Gdx.input.setInputProcessor(inputProcessor);
    }

    protected $Screen prev() {
        return prev;
    }

    /**
     * Await for resources to load. If not all resources are ready switches to loading screen and returns {@code true}.
     */
    protected boolean awaitResources() {
        if (!Resources.manager().loadNext()) {
            next(new LoadingScreen());
            return true;
        }

        return false;
    }

    public void leave(final $Screen next) {
        Gdx.input.setInputProcessor(null);
    }

    protected void onClick(final float x, final float y) {
        for (Button button : buttons) {
            if (button.onClick((int)x, (int)y)) {
                break;
            }
        }
    }

    protected void onDrag(final float x, final float y, final float dx, final float dy) {}
    protected void onKeyPress(final int code) {
        if (code == Input.Keys.BACK) {
            goBack();
        }
    }
    protected void onCharTyped(final char chr) {}
    protected void onScroll(final int amount) {}
    protected void onZoom(final int x, final int y, final float zoom) {}
}
