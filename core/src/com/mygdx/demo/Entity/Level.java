package com.mygdx.demo.Entity;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.demo.Levels.LevelState;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;

public class Level extends InteractiveTile{
    GameScreen gameScreen;
    static int count=0;
    public Level(GameScreen screen, MapObject object) {
        super(screen,object);
        gameScreen = screen;
        fixture.setUserData(this);
        setCategoryFilter(TuruncsAdventure.LEVEL_BIT);
    }

    @Override
    public void onHeadHit() {
    setCategoryFilter(TuruncsAdventure.NOTHING_BIT);
        count++;
    if(count<4){
        LevelState.levelCount();
        gameScreen.callQuestsScreen();
    }
    else
        gameScreen.callFinishScreen();
    }
}
