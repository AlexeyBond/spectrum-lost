package com.github.alexeybond.spectrum_lost.resources.impl;

import com.badlogic.gdx.audio.Music;
import com.github.alexeybond.spectrum_lost.resources.IMusicPlayer;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class MusicPlayerImpl implements IMusicPlayer {
    private boolean play = true;
    private Music currentMusic = null;
    private List<Music> musicStack = new LinkedList<Music>();

    @Override
    public boolean isPlaying() {
        return play;
    }

    @Override
    public void setPlaying(boolean playing) {
        if (null != currentMusic && playing != currentMusic.isPlaying()) {
            if (playing) {
                currentMusic.play();
            } else {
                currentMusic.stop();
            }
        }

        play = playing;
    }

    void pushMusic(String name, Music music) {
        if (null != currentMusic) {
            if (currentMusic.isPlaying())
                currentMusic.pause();
            musicStack.add(0, currentMusic);
        }

        currentMusic = music;
        currentMusic.setLooping(true);

        setPlaying(play);
    }

    /**
     * This is not what you think about.
     */
    void popMusic(String name) {
        // Do not use <name>. Hope push/pop~Music are called in right order ...
        if (null != currentMusic) {
            currentMusic.pause();
            if (!musicStack.contains(currentMusic)) {
                currentMusic.dispose();
            }

            currentMusic = null;
        }

        if (musicStack.size() > 0) {
            currentMusic = musicStack.remove(0);
            setPlaying(play);
        }
    }
}
