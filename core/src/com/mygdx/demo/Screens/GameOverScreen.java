package com.mygdx.demo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.TuruncsAdventure;

public class GameOverScreen implements Screen{

    private Viewport viewport;
    private Stage stage;

    private TuruncsAdventure game;
    private Texture ad, oo,ud;
    private Image image;
    private TextureRegionDrawable buttonDrawable, buttonDrawable2, buttonDrawable3;

    public GameOverScreen(TuruncsAdventure game){
        this.game = game;
        viewport = new FitViewport(TuruncsAdventure.WIDTH, TuruncsAdventure.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((TuruncsAdventure) game).batch);
        image = new Image(new Texture("gameOver.png"));

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.ORANGE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        table.row();
        table.add(image).expandX().padTop(10f);


        stage.addActor(table);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            game.setScreen(new MenuScreen(game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
