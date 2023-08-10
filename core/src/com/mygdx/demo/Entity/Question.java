package com.mygdx.demo.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;

public class Question extends InteractiveTile {
    private static TiledMapTileSet tileSet;
    private GameScreen screen;

    public Question(GameScreen screen, MapObject object) {
        super(screen, object);
        this.screen = screen;
        tileSet = map.getTileSets().getTileSet("emptyQuestion");
        fixture.setUserData(this);
        setCategoryFilter(TuruncsAdventure.QUESTION_BIT);
    }

    @Override
    public void onHeadHit() {
        if (getCell().getTile() != tileSet.getTile(1)) {
            getCell().setTile(tileSet.getTile(1));
            Gdx.app.log("Question", "Collision");
            TuruncsAdventure.manager.get("Audio/sound effects/pixel-sound-effect-5-103221.mp3", Sound.class).play();
            screen.callQuestScreen();
        }

    }

}