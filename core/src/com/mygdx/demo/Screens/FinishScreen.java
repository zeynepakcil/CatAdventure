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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.TuruncsAdventure;

public class FinishScreen extends Window {

    private Viewport viewport;
    private Stage stage;
    private static WindowStyle windowStyle;
    private TuruncsAdventure game;
    private Texture ad, oo, ud;
    private static Image finish;
    private TextureRegionDrawable buttonDrawable, buttonDrawable2, buttonDrawable3;

    static {
        windowStyle = new Window.WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(new Texture("Empty.png")));
    }

    public FinishScreen(TuruncsAdventure t) {
        super("", windowStyle);
        game = t;
        finish = new Image(new Texture("end.png"));
        getTitleTable().add(finish).size((int) (1100 / 3), (int) (516 / 3)).padTop(200).padRight(10);
        ;
        finish.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
                game.setScreen(new MenuScreen(game));

            }
        });
        setClip(false);
        setTransform(true);
    }
}

