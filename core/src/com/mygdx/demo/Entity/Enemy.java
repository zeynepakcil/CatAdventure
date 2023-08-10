package com.mygdx.demo.Entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.demo.Screens.GameScreen;

public abstract class Enemy extends Sprite {
    protected World world;
    protected GameScreen screen;
    public Body body;
    public Vector2 velocity;

    public Enemy(GameScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(1, 0);
    }

    protected abstract void defineEnemy();

    public abstract void hitOnHead();

    public void reverseVelo(boolean x, boolean y) {
        if(x)
            velocity.x= -velocity.x;
        if(y)
            velocity.y= -velocity.y;

    }

    public abstract void update(float d);
}
