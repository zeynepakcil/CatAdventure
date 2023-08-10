package com.mygdx.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.demo.TuruncsAdventure;

import java.awt.*;


public class MenuScreen implements Screen {
    private Stage stage;
    private Texture background;
    private Texture play, quit, howTo;
    private TextureRegionDrawable buttonDrawable, buttonDrawable2, buttonDrawable3;
    private SpriteBatch batch;
    private TuruncsAdventure game;
    private BitmapFont font;
    public Music music;
    private HowToScreen howToScreen;

    public MenuScreen(final TuruncsAdventure game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        background = new Texture("Menu/background.png");
        play = new Texture("Menu/play.png");
        quit = new Texture("Menu/quit.png");
        howTo = new Texture("Menu/howToIcon.png");
        buttonDrawable = new TextureRegionDrawable(new TextureRegion(play));
        buttonDrawable2 = new TextureRegionDrawable(new TextureRegion(quit));
        buttonDrawable3 = new TextureRegionDrawable(new TextureRegion(howTo));



        TextButton startButton = createButton("");
        startButton.addListener(new

                                        ClickListener() {
                                            @Override
                                            public void clicked(InputEvent event, float x, float y) {
                                                game.setScreen(new GameScreen(game));
                                            }
                                        });

        TextButton quitButton = createButton2("");
        quitButton.addListener(new

                                       ClickListener() {
                                           @Override
                                           public void clicked(InputEvent event, float x, float y) {
                                               Gdx.app.exit();
                                           }
                                       });

        TextButton howToButton = createButton3("");
        howToButton.addListener(new

                                        ClickListener() {
                                            @Override
                                            public void clicked(InputEvent event, float x, float y) {
                                                create(stage);;
                                            }
                                        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(startButton).padBottom(5).row();
        table.add(quitButton).padBottom(5).row();
        table.add(howToButton);

        stage.addActor(table);

        music = TuruncsAdventure.manager.get("Audio/music/pixel-song-12-72532.mp3", Music.class);
        music.setLooping(true);
        music.play();
    }

    private TextButton createButton(String text) {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(buttonDrawable, null, null, font);
        TextButton button = new TextButton(text, buttonStyle);
        return button;
    }
    private TextButton createButton2(String text) {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(buttonDrawable2, null, null, font);
        TextButton button = new TextButton(text, buttonStyle);
        return button;
    }
    private TextButton createButton3(String text) {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(buttonDrawable3, null, null, font);
        TextButton button = new TextButton(text, buttonStyle);
        return button;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        background.dispose();
        play.dispose();
        quit.dispose();
        stage.dispose();
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
    public void create(Stage stage) {
        this.stage = stage;

        howToScreen = new HowToScreen(game);
        howToScreen.setSize(TuruncsAdventure.WIDTH*2, TuruncsAdventure.HEIGHT*2);
        howToScreen.setModal(true);
        howToScreen.setVisible(true);
        howToScreen.setMovable(true);
        howToScreen.setPosition(TuruncsAdventure.WIDTH / TuruncsAdventure.PPM / 2, TuruncsAdventure.HEIGHT / TuruncsAdventure.PPM  / 2);
        stage.addActor(howToScreen);

        InputMultiplexer multiplexer = new InputMultiplexer();
        //multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

}