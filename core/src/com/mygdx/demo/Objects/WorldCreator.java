package com.mygdx.demo.Objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.demo.Entity.*;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;

public class WorldCreator {
    private Array<Enemy> enemies;
    private Array<FloorTouch> floor;
    private Array<Level> level;
    public WorldCreator(GameScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / TuruncsAdventure.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / TuruncsAdventure.PPM);

            body = world.createBody(bodyDef);
            shape.setAsBox((rectangle.getWidth() / 2) / TuruncsAdventure.PPM, (rectangle.getHeight() / 2) / TuruncsAdventure.PPM);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = TuruncsAdventure.OBJECT_BIT;
            body.createFixture(fixtureDef);
            //fixtureDef.filter.categoryBits = TuruncsAdventure.OBJECT_BIT;
            //fixtureDef.filter.maskBits = TuruncsAdventure.ENEMY_BIT | TuruncsAdventure.CAT_BIT;
        }


        for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            new Question(screen, object);
        }
        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            Book book = new Book(screen, object);
            /* bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / TuruncsAdventure.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / TuruncsAdventure.PPM);

            body = world.createBody(bodyDef);
            shape.setAsBox((rectangle.getWidth() / 2) / TuruncsAdventure.PPM, (rectangle.getHeight() / 2) / TuruncsAdventure.PPM);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = TuruncsAdventure.OBJECT_BIT;
            body.createFixture(fixtureDef); */
        }

        enemies = new Array<Enemy>();
        int count = 0;
        for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            count++;
            if (count < 3) {
                enemies.add(new Student(screen, rectangle.getX() / TuruncsAdventure.PPM, rectangle.getY() / TuruncsAdventure.PPM));
            } else if (count < 6) {
                enemies.add(new Animal(screen, rectangle.getX() / TuruncsAdventure.PPM, rectangle.getY() / TuruncsAdventure.PPM));
            } else if( count < 9){
                enemies.add(new Bug(screen, rectangle.getX() / TuruncsAdventure.PPM, rectangle.getY() / TuruncsAdventure.PPM));
            }

        }
        floor = new Array<FloorTouch>();
        for (MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            floor.add(new FloorTouch(screen, object));
        }
        level = new Array<Level>();
        for (MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            level.add(new Level(screen, object));
        }

    }

    public Array<Enemy> getAnimals() {
        return enemies;
    }
}