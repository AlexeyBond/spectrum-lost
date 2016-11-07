package com.github.alexeybond.spectrum_lost.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.alexeybond.spectrum_lost.achievements.AchievementStatus;
import com.github.alexeybond.spectrum_lost.achievements.Achievements;
import com.github.alexeybond.spectrum_lost.levels.ILevelsSource;
import com.github.alexeybond.spectrum_lost.levels.json.JsonSource;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChapterLevelsDesc;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChaptersList;
import com.github.alexeybond.spectrum_lost.levels.json.compact.CompactChapterDesc;
import com.github.alexeybond.spectrum_lost.resources.Resources;
import com.github.alexeybond.spectrum_lost.screens.background.AnimatedBackground;
import com.github.alexeybond.spectrum_lost.screens.base.$Screen;

import java.util.*;

class ChapterView {
    List<ChapterView> after;

    static float a1, a2;

    final CompactChapterDesc desc;
    private TextureRegion closedIcon;
    private TextureRegion doneIcon;
    private final TextureRegion icon;
    private final AchievementStatus achievementStatus;

    final Rectangle rect = new Rectangle();

    private static Vector2 tv1 = new Vector2(), tv2 = new Vector2();

    private static int CONNECTION_POINT_OFFSET = 5;

    ChapterView(CompactChapterDesc desc, TextureRegion closedIcon) {
        this.desc = desc;
        this.closedIcon = closedIcon;

        if (desc.attrs.containsKey("icon")) {
            icon = Resources.getSprite(desc.attrs.get("icon").toString());
        } else {
            icon = Resources.getSprite("chapter-icons/".concat(desc.id));
        }

        doneIcon = Resources.getSprite("chapter-icons/open");

        achievementStatus = Achievements.get("chapter:".concat(desc.id));
    }

    boolean isOpen() {
        for (ChapterView afterView : after) {
            if (!afterView.isDone()) {
                return false;
            }
        }

        return true;
    }

    boolean isDone() {
        return achievementStatus.isStarted();
    }

    void draw(SpriteBatch batch, ChapterView justClicked) {
        float c = justClicked == this ? Math.min(1f, 1f - a1 * .6f) : .7f;

        batch.setColor(c,c,c, 1);
        batch.draw(isOpen() ? icon : closedIcon,
                rect.x, rect.y, rect.width, rect.height);

        batch.setColor(Color.WHITE);

        if (isDone()) {
            batch.draw(doneIcon, rect.x, rect.y, rect.width, rect.height);
        }
    }

    void drawConnections(ShapeRenderer renderer, ChapterView justClicked) {
        getLeftConnectionPoint(tv1);

        for (ChapterView view : after) {
            view.getRightConnectionPoint(tv2);

            if (justClicked == this ||
                    (view == justClicked && !view.isDone())) {
                float c = Math.min(1f, 1f - a1 * .4f);
                renderer.setColor(c, c, c, c);
            } else {
                renderer.setColor(.73f, .73f, .73f, 1.f);
            }
            renderer.rectLine(tv1, tv2, 5.f);
        }
    }

    Vector2 getRightConnectionPoint(Vector2 vector) {
        rect.getCenter(vector);
        vector.x = rect.x + rect.width - CONNECTION_POINT_OFFSET;
        return vector;
    }

    Vector2 getLeftConnectionPoint(Vector2 vector) {
        rect.getCenter(vector);
        vector.x = rect.x + CONNECTION_POINT_OFFSET;
        return vector;
    }
}

/**
 *
 */
public class ChapterSelectScreen extends $Screen {
    private Map<String, ChapterView> chapterViewList = new HashMap<String, ChapterView>();
    private List<List<ChapterView>> layers = new ArrayList<List<ChapterView>>();

    private int iconMarginX = 40;
    private int iconMarginY = 20;

    private final Rectangle focusBounds = new Rectangle();
    private final Vector2 focus = new Vector2();
    private float scale = 1.0f;

    private static final Vector2 tv = new Vector2();

    private ChapterView justClicked = null;

    private TextureRegion closedIcon;

    private boolean preExit = false;
    private boolean initialized = false;

    private final AnimatedBackground[] backs;

    public ChapterSelectScreen() {
        backs = new AnimatedBackground[] {
                new AnimatedBackground("background/abg-00", 4, 512),
                new AnimatedBackground("background/abg-00", 4, 256),
                new AnimatedBackground("background/abg-00", 4, 128)
        };

        backs[0].opacity = .075f;
        backs[0].kVelocity = .5f;

        backs[1].opacity = .15f;
        backs[1].kVelocity = 1f;

        backs[2].opacity = .3f;
        backs[2].kVelocity = 2f;
    }

    private void init() {
        closedIcon = Resources.getSprite("chapter-icons/closed");

        ChaptersList list = ChaptersList.readFrom(Gdx.files.internal("levels"));

        for (CompactChapterDesc ccd : list.chapters) {
            chapterViewList.put(ccd.id, new ChapterView(ccd, closedIcon));
        }

        for (ChapterView view : chapterViewList.values()) {
            view.after = new ArrayList<ChapterView>(view.desc.after.size());

            for (String id : view.desc.after) {
                if (!chapterViewList.containsKey(id)) {
                    throw new NoSuchElementException("No such chapter: "
                            .concat(id).concat(" ; referred by ").concat(view.desc.id));
                }

                view.after.add(chapterViewList.get(id));
            }
        }

        Set<ChapterView> sortedViews = new HashSet<ChapterView>();

        do {
            List<ChapterView> layer = new ArrayList<ChapterView>();

            for (ChapterView view : chapterViewList.values()) {
                if (!sortedViews.contains(view) && sortedViews.containsAll(view.after)) {
                    layer.add(view);
                }
            }

            if (layer.size() == 0) {
                break;
            }

            sortedViews.addAll(layer);
            layers.add(layer);
        } while (true);

        recalculateRectangles();

        initialized = true;
    }

    @Override
    public void show($Screen prev) {
        super.show(prev);
        if (awaitResources()) return;
        if (!initialized) init();
    }

    @Override
    public void unpause() {
        super.unpause();
        awaitResources();
    }

    private void recalculateRectangles() {
        boolean focusSet = false;
        focusBounds.set(0,0,0,0);

        Vector2 tv = new Vector2();

        for (int i = 0; i < layers.size(); i++) {
            List<ChapterView> layer = layers.get(i);

            int iconHeight = closedIcon.getRegionHeight() + 2 * iconMarginY;
            int iconWidth = closedIcon.getRegionWidth() + 2 * iconMarginX;

            int iconStartY = (iconHeight * layer.size()) / 2;
            int iconStartX = iconWidth * i;

            for (int j = 0; j < layer.size(); j++) {
                ChapterView view = layer.get(j);

                view.rect.set(iconStartX + iconMarginX, iconStartY - iconHeight * (j + 1) + iconMarginY,
                        closedIcon.getRegionWidth(), closedIcon.getRegionHeight());

                view.rect.getCenter(tv);

                if (i==0 && j==0) {
                    focusBounds.set(tv.x, tv.y, 0, 0);
                } else {
                    focusBounds.merge(tv);
                }

                if (!focusSet && view.isOpen()) {
                    view.rect.getCenter(focus);
                    focusSet = !view.isDone();
                    justClicked = view;
                }
            }
        }

        focus.y = 0;
    }

    private void toIconCoordinates(Vector2 pt) {
        pt.sub(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        pt.scl(1.f / scale);
        pt.add(focus);
    }

    private List<ChapterView> getLayerAtX(float x) {
        int l = (int) Math.floor(((float) layers.size()) * x /
                (focusBounds.width + 2*iconMarginX + closedIcon.getRegionWidth()));

        if (l < 0 || l >= layers.size())
            return null;

        return layers.get(l);
    }

    @Override
    protected void onDrag(float x, float y, float dx, float dy) {
        preExit = false;
        focus.x -= dx / scale;
        focus.x = Math.min(focusBounds.x + focusBounds.width,
                Math.max(focusBounds.x, focus.x));
        tv.set(x, y);
        toIconCoordinates(tv);

        List<ChapterView> layer = getLayerAtX(tv.x);

        if (layer != null) {
            float centerTopY = layer.get(0).rect.y + layer.get(0).rect.height * .5f;
            float centerBotY = layer.get(layer.size() - 1).rect.y +
                    layer.get(layer.size() - 1).rect.height * .5f;
            float shiftY = Math.min(-centerBotY, Math.max(-centerTopY, dy / scale));

            for (ChapterView view : layer) {
                view.rect.setY(view.rect.getY() + shiftY);
            }
        }

        for (AnimatedBackground ab : backs) {
            ab.applyImpulse(-dx, -dy);
        }
    }

    @Override
    protected void onScroll(int amount) {
        preExit = false;
        if (amount == 0) return;
        scale(scale * ((amount < 0)?1.1f:.9f));
    }

    @Override
    protected void onZoom(int x, int y, float zoom) {
        preExit = false;
        scale(scale * zoom);
    }

    @Override
    protected void onClick(float x, float y) {
        preExit = false;
        tv.set(x, y);
        toIconCoordinates(tv);

        List<ChapterView> layer = getLayerAtX(tv.x);

        if (layer == null) {
            super.onClick(x,y);
            return;
        }

        for (ChapterView chapterView : layer) {
            if (chapterView.rect.contains(tv)) {
                justClicked = chapterView;

                if (chapterView.isOpen()) {
                    ChapterLevelsDesc levelsDesc = chapterView.desc.readLevels(Gdx.files.internal("levels"));
                    ILevelsSource source = new JsonSource(levelsDesc, chapterView.desc.attrs);
                    next(new GameScreen(source, source.rootLevelName(), "chapter:".concat(chapterView.desc.id)));
                }
            }
        }
    }

    private void scale(float scale) {
        scale = Math.max(scale, 0.5f);
        scale = Math.min(scale, 1.0f);

        this.scale = scale;

        for (AnimatedBackground ab : backs) {
            ab.scale(scale);
        }
    }

    @Override
    public void draw() {
        ChapterView.a1 = Math.abs((float) Math.sin(20000f * Math.PI *
                ((float) (TimeUtils.millis() & 0xFFFFFF)) / (float) 0x1000000));
        ChapterView.a2 = (float) Math.cos(20000f * Math.PI *
                ((float) (TimeUtils.millis() & 0xFFFFFF)) / (float) 0x1000000);

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.getTransformMatrix().idt();
        // begin() updates matrices
        spriteBatch.begin();

        for (AnimatedBackground ab : backs) {
            ab.draw();
        }

        spriteBatch.end();

        spriteBatch.setTransformMatrix(spriteBatch.getTransformMatrix()
                .idt()
                .translate(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0)
                .scale(scale,scale,1.f)
                .translate(-focus.x, -focus.y, 0));

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(spriteBatch.getTransformMatrix());

        for (ChapterView view : chapterViewList.values()) {
            view.drawConnections(shapeRenderer, justClicked);
        }

        shapeRenderer.end();

        spriteBatch.begin();

        for (ChapterView view : chapterViewList.values()) {
            view.draw(spriteBatch, justClicked);
        }

        spriteBatch.end();
    }

    @Override
    protected void onKeyPress(int code) {
        if (code == Input.Keys.BACK) {
            if (preExit) {
                Gdx.app.exit();
            } else {
                preExit = true;
            }
        }
    }
}
