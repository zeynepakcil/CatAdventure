package com.mygdx.demo.Entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.softbody.btSparseSdf3.Cell;
import com.mygdx.demo.Levels.LevelState;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.Screens.GameplayQuestionScreen;
import com.mygdx.demo.TuruncsAdventure;

public class Book extends InteractiveTile{

    private static TiledMapTileSet tileSet;
    private GameScreen screen;

    public Book(GameScreen screen, MapObject object) {
        super(screen, object);
        this.screen = screen;
        tileSet = map.getTileSets().getTileSet("bookIconSmallEmpty");
        fixture.setUserData(this);
        setCategoryFilter(TuruncsAdventure.QUESTION_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Book", "Collusion");
        //LevelState.loseLive(1);
        LevelState.incrementBookCount();
        getCell().setTile(tileSet.getTile(1));
        getCell().setTile(null);
    }




}