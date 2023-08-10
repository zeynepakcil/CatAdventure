package com.mygdx.demo.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.Entity.Cat;
import com.mygdx.demo.Entity.Enemy;
import com.mygdx.demo.Entity.InteractiveTile;
import com.mygdx.demo.Levels.LevelState;
import com.mygdx.demo.TuruncsAdventure;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        if (fixA.getUserData() == "head" || fixB.getUserData() == "head") {
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;
            if (object.getUserData() != null && InteractiveTile.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTile) object.getUserData()).onHeadHit();
            }
        }
        switch (cDef) {
            case TuruncsAdventure.ENEMY_HEAD_BIT | TuruncsAdventure.CAT_BIT:
                if (fixA.getFilterData().categoryBits == TuruncsAdventure.ENEMY_HEAD_BIT)
                    ((Enemy) fixA.getUserData()).hitOnHead();
                else
                    ((Enemy) fixB.getUserData()).hitOnHead();
                break;
            case TuruncsAdventure.ENEMY_BIT | TuruncsAdventure.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == TuruncsAdventure.ENEMY_BIT)
                    ((Enemy) fixA.getUserData()).reverseVelo(true, false);
                else
                    ((Enemy) fixB.getUserData()).reverseVelo(true, false);
                break;
            case TuruncsAdventure.CAT_BIT | TuruncsAdventure.ENEMY_BIT:
                LevelState.loseLive(1);
                TuruncsAdventure.manager.get("Audio/sound effects/cat-meow-99835.mp3", Sound.class).play();
                if (LevelState.getLives() == 0) {
                    TuruncsAdventure.manager.get("Audio/music/pixel-song-18-72641.mp3", Music.class).stop();
                    TuruncsAdventure.manager.get("Audio/music/kl-peach-game-over-iii-142453.mp3", Music.class).play();
                    Cat.isDead = true;
                }
                break;
            case TuruncsAdventure.CAT_BIT | TuruncsAdventure.FLOOR_BIT:
                System.out.println("dieee");
                LevelState.loseLive(3);
                if (LevelState.getLives() == 0) {
                    TuruncsAdventure.manager.get("Audio/music/kl-peach-game-over-iii-142453.mp3", Music.class).play();
                    Cat.isDead = true;
                }
                break;
            case TuruncsAdventure.CAT_BIT | TuruncsAdventure.LEVEL_BIT:

                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}