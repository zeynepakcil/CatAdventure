package com.mygdx.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.Screens.MenuScreen;


public class TuruncsAdventure extends Game {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    public static final float PPM = 100;
    public static final short NOTHING_BIT = 0;
    public static final short GROUND_BIT = 1;
    public static final short CAT_BIT = 2;
    public static final short QUESTION_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short FLOOR_BIT = 256;
    public static final short ENEMY_BIT = 64;
    public static final short ENEMY_HEAD_BIT = 128;
    public static final short LEVEL_BIT = 512;
    public static boolean questScreenShown = false;
    public SpriteBatch batch;
    public static AssetManager manager;

    @Override
    public void create() {

        batch = new SpriteBatch();
        manager = new AssetManager();
        manager.load("Audio/music/pixel-song-18-72641.mp3", Music.class);
        manager.load("Audio/music/kl-peach-game-over-iii-142453.mp3", Music.class);
        manager.load("Audio/music/pixel-song-12-72532.mp3", Music.class);
        manager.load("Audio/sound effects/cat-meow-99835.mp3", Sound.class);
        manager.load("Audio/sound effects/cat-purring-and-meow-5928.mp3", Sound.class);
        manager.load("Audio/sound effects/pixel-sound-effect-5-103221.mp3", Sound.class);
        manager.load("Audio/music/pixel-song-12-72532.mp3", Music.class);
        manager.finishLoading();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        setScreen(new MenuScreen(this));
    }

    public void render() {
        super.render();
    }
}