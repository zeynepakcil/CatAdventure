package com.mygdx.demo.Entity;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.Screens.GameScreen;
import com.mygdx.demo.TuruncsAdventure;

public abstract class InteractiveTile {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle rect;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTile(GameScreen screen, MapObject object) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.rect = ((RectangleMapObject) object).getRectangle();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / TuruncsAdventure.PPM, (rect.getY() + rect.getHeight() / 2) / TuruncsAdventure.PPM);

        body = world.createBody(bodyDef);
        shape.setAsBox((rect.getWidth() / 2) / TuruncsAdventure.PPM, (rect.getHeight() / 2) / TuruncsAdventure.PPM);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);

    }
    public  abstract  void  onHeadHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(4);
        return layer.getCell((int)(body.getPosition().x * TuruncsAdventure.PPM /16),(int)(body.getPosition().y * TuruncsAdventure.PPM /16));
    }
}