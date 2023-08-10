package com.mygdx.demo.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;

public class FloorTouch extends InteractiveTile{
    public FloorTouch(GameScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(TuruncsAdventure.FLOOR_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Level", "Collision");
    }
}
