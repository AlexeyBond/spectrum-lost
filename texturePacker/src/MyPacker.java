import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.github.alexeybond.spectrum_lost.levels.json.ChapterDesc;
import com.github.alexeybond.spectrum_lost.levels.json.GridDesc;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChapterLevelsDesc;
import com.github.alexeybond.spectrum_lost.levels.json.compact.ChaptersList;
import com.github.alexeybond.spectrum_lost.levels.json.compact.CompactChapterDesc;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MyPacker {
    private static Path rawAssetsPath = Paths.get("_raw-assets");
    private static Path assetsPath = Paths.get("android/assets");

    private static Path rawSpritesPath = rawAssetsPath.resolve("sprites");
    private static Path spritesPath = assetsPath.resolve("sprites");

    private static Path rawLevelsPath = rawAssetsPath.resolve("levels");
    private static Path levelsPath = assetsPath.resolve("levels");

    private static Files files = Gdx.files = new LwjglFiles();

    public static void main (String[] args) throws Exception {
        packTextures();
        packLevels();
    }

    private static void packTextures() {
        TexturePacker.process(
                rawSpritesPath.toString(),
                spritesPath.toString(),
                "sprites-common");
    }

    private static void packLevels() {
        Json json = new Json(JsonWriter.OutputType.minimal);

        ChaptersList list = new ChaptersList();

        for (FileHandle f : files.local(rawLevelsPath.toString()).list()) {
            if (!f.name().endsWith(".json")) continue;
            if (f.isDirectory()) continue;
            if (f.name().contains("$")) continue;

            ChapterDesc cd = json.fromJson(ChapterDesc.class, f);

            CompactChapterDesc ccd = new CompactChapterDesc();
            ChapterLevelsDesc levels = new ChapterLevelsDesc();

            ccd.attrs = cd.attrs;
            ccd.after = cd.afterChapters;
            ccd.id = f.nameWithoutExtension();

            for (String ln : cd.levelNames) {
                FileHandle fh = f.parent().child(ln.concat(".json"));
                levels.levels.put(ln, json.fromJson(GridDesc.class, fh));
            }

            levels.rootLevelName = cd.rootLevel;

            files
                    .local(levelsPath.resolve("levels-".concat(ccd.id).concat(".json")).toString())
                    .writeString(json.toJson(levels), false);
            list.chapters.add(ccd);
        }

        files
                .local(levelsPath.resolve("chapters.json").toString())
                .writeString(json.toJson(list),false);
    }
}
