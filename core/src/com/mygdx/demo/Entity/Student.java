package com.mygdx.demo.Entity;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.demo.Levels.LevelState;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;

public class Student extends Enemy {
    private float stateTime;
    private Animation<TextureRegion> walking;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;

    public Student(GameScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(screen.getAtlas().findRegion("newCharacters/side eye2"), 1, 1, 48, 48));
        walking = new Animation<TextureRegion>(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 48 / TuruncsAdventure.PPM, 48 / TuruncsAdventure.PPM);
        setToDestroy = false;
        destroyed = false;
    }

    public void update(float dt) {
        stateTime += dt;
        if (setToDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("newCharacters/surprised2"),1,1,48,48));
            stateTime=0;
        }
        else if (!destroyed) {
            body.setLinearVelocity(velocity);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
            setRegion(walking.getKeyFrame(stateTime, true));
        }

    }


    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/ TuruncsAdventure.PPM);

        fixtureDef.filter.categoryBits = TuruncsAdventure.ENEMY_BIT;
        fixtureDef.filter.maskBits = TuruncsAdventure.GROUND_BIT | TuruncsAdventure.QUESTION_BIT | TuruncsAdventure.ENEMY_BIT | TuruncsAdventure.OBJECT_BIT | TuruncsAdventure.CAT_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(-5, 20).scl(1 / TuruncsAdventure.PPM);
        vertices[1] = new Vector2(5, 20).scl(1 / TuruncsAdventure.PPM);
        vertices[2] = new Vector2(-3, 19).scl(1 / TuruncsAdventure.PPM);
        vertices[3] = new Vector2(3, 19).scl(1 / TuruncsAdventure.PPM);

        head.set(vertices);

        fixtureDef.shape = head;
        fixtureDef.restitution = 1f;
        fixtureDef.filter.categoryBits = TuruncsAdventure.ENEMY_HEAD_BIT;
        body.createFixture(fixtureDef).setUserData(this);

    }

    public void draw(Batch batch) {
        if(!destroyed || stateTime <1)
            super.draw(batch);
    }
    @Override
    public void hitOnHead() {
        setToDestroy =true;
    }
}
