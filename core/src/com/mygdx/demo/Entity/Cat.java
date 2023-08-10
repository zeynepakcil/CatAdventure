package com.mygdx.demo.Entity;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;


public class Cat extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING, DEAD};

    public static State current;
    public static State prev;
    public World world;
    public Body box2d;
    private TextureRegion catNorm;
    private Animation<TextureRegion> catRun;
    private Animation<TextureRegion> catJump;
    private TextureRegion catDead;
    private boolean timeToRedefineCat;
    private float stateTimer;
    private boolean right;
    public static boolean isDead;


    public Cat(GameScreen screen) {
        super(screen.getAtlas().findRegion("newCharacters/orange"));
        this.world = screen.getWorld();
        current = State.STANDING;
        prev = State.STANDING;
        stateTimer = 0;
        right = true;
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 12; i < 14; i++)
            frames.add(new TextureRegion(getTexture(), i * 32 + 13, 51, 27, 25));
        catRun = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 18; i < 20; i++)
            frames.add(new TextureRegion(getTexture(), i * 32 + 19, 51, 29, 25));
        catJump = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        catNorm = new TextureRegion(getTexture(), 1, 51, 29, 25);

        catDead = new TextureRegion(screen.getAtlas().findRegion("newCharacters/side eye2"), 351, 1, 48, 48);
        defineCat();
        setBounds(0, 0, 32 / TuruncsAdventure.PPM, 32 / TuruncsAdventure.PPM);
        setRegion(catNorm);
    }

    public void update(float dt) {
        setPosition(box2d.getPosition().x - getWidth() / 2, box2d.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

    }
    public void setDead(){
        isDead = true;
    }
    public boolean isDead() {
        return isDead;
    }

    public float getStateTimer() {
        return stateTimer;
    }

    private TextureRegion getFrame(float dt) {
        current = getState();
        TextureRegion region;
        switch (current) {
            case DEAD:
                region = catDead;
                break;
            case JUMPING:
                region = catJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = catRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = catNorm;
                break;
        }
        if ((box2d.getLinearVelocity().x < 0 || !right) && region.isFlipX()) {
            region.flip(true, false);
            right = false;
        } else if ((box2d.getLinearVelocity().x > 0 || right) && region.isFlipX()) {
            region.flip(true, false);
            right = true;
        }
        stateTimer = current == prev ? stateTimer + dt : 0;
        prev = current;
        return region;
    }

    private State getState() {
        if(isDead)
            return State.DEAD;
        else if(box2d.getLinearVelocity().y > 0 || (box2d.getLinearVelocity().y < 0 && prev == State.JUMPING))
            return State.JUMPING;
        else if (box2d.getLinearVelocity().y < 0)
            return State.FALLING;
        else if (box2d.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } else
            return State.STANDING;
    }

    private void defineCat() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / TuruncsAdventure.PPM, 32 / TuruncsAdventure.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        box2d = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / TuruncsAdventure.PPM);

        fixtureDef.filter.categoryBits = TuruncsAdventure.CAT_BIT;
        fixtureDef.filter.maskBits = TuruncsAdventure.GROUND_BIT | TuruncsAdventure.QUESTION_BIT | TuruncsAdventure.ENEMY_BIT | TuruncsAdventure.OBJECT_BIT | TuruncsAdventure.ENEMY_HEAD_BIT | TuruncsAdventure.FLOOR_BIT |TuruncsAdventure.LEVEL_BIT;

        fixtureDef.shape = shape;
        box2d.createFixture(fixtureDef).setUserData(this);

        EdgeShape head = new EdgeShape();
        EdgeShape bod = new EdgeShape();
        head.set(new Vector2(-2 / TuruncsAdventure.PPM, 10 / TuruncsAdventure.PPM), new Vector2(2 / TuruncsAdventure.PPM, 10 / TuruncsAdventure.PPM));
        bod.set(new Vector2(2 / TuruncsAdventure.PPM, 10 / TuruncsAdventure.PPM), new Vector2(10 / TuruncsAdventure.PPM, -10 / TuruncsAdventure.PPM));
        fixtureDef.shape = head;
        fixtureDef.shape =bod;
        fixtureDef.isSensor = true;
        box2d.createFixture(fixtureDef).setUserData("head");

    }

    public void hit(){

        //Game music
        //Game over sound
        isDead = true;
        Filter filter = new Filter();
        filter.maskBits = TuruncsAdventure.NOTHING_BIT;
        for(Fixture fixture: box2d.getFixtureList())
            fixture.setFilterData(filter);
        box2d.applyLinearImpulse(new Vector2(0, 4f), box2d.getWorldCenter(), true);

    }

}