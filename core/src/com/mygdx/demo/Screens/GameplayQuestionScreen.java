package com.mygdx.demo.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.TuruncsAdventure;

public class GameplayQuestionScreen implements com.badlogic.gdx.Screen{
    private Viewport viewport;
    private Stage stage;

    private Game game;

    public GameplayQuestionScreen(TuruncsAdventure game){
        this.game = game;
        viewport = new FitViewport(TuruncsAdventure.WIDTH, TuruncsAdventure.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((TuruncsAdventure) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.ORANGE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label questionLabel = new Label("Do you want to answer the question?", font);
        Label yesLabel = new Label("Yes", font);
        Label noLabel = new Label("No", font);

        table.add(questionLabel).expandX();
        table.row();
        table.add(yesLabel).expandX().padTop(10f);
        table.row();
        table.add(noLabel).expandX().padTop(10f);
        stage.addActor(table);



    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
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
    }

}