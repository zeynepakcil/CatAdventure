package com.mygdx.demo.Levels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.TuruncsAdventure;
import com.mygdx.demo.Entity.Cat;
import com.mygdx.demo.Screens.FinishGame;
import com.mygdx.demo.Screens.GameplayQ;
import com.mygdx.demo.Screens.QuestPlay;

public class LevelState implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private static int lives;

    private Label countDownLabel;
    private static Label live;
    private Label time;
    private static Label level;
    private Label world;
    private Label turunc;
    private static Label books;
    private static int levels;
    private static LivesWidget livesWidget;
    private Texture liveImageTexture;
    private Cat cat;
    private static int bookCount;
    private Image bookIcon;
    private TuruncsAdventure t;

    public LevelState(SpriteBatch sb, Cat cat,TuruncsAdventure t) {
        this.t =t;
        worldTimer = 300;
        timeCount = 0;
        lives = 3;
        levels = 0;

        viewport = new FitViewport(TuruncsAdventure.WIDTH, TuruncsAdventure.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        countDownLabel = new Label(String.format("%03d", worldTimer),
                new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        liveImageTexture = new Texture(Gdx.files.internal("maps/tiles/heart.png"));
        livesWidget = new LivesWidget(liveImageTexture, lives);

        live = new Label(String.format("%06d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        time = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        level = new Label( "1-3", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        world = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        turunc = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        books = new Label(bookCount + "", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        bookIcon = new Image(new Texture("maps/tiles/bookIconSmall.png"));

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(turunc).expandX().padTop(10);
        table.add(bookIcon);

        table.add(world).expandX().padTop(10);
        table.add(time).expandX().padTop(10);
        table.row();
        table.add(livesWidget).expandX(); // Add the custom LivesWidget

        table.add(books).expandX().padTop(10);
        table.add(level).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setDead() {
        cat.setDead();
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer--;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void incrementBookCount() {
        bookCount++;
        books.setText(bookCount + "");
    }

    public static void levelCount() {
        levels++;
        level.setText(levels + "-3");
    }

    public static void loseLive(int value) {
        /*
        if(lives > 1){
            lives -= value;
            live.setText(String.format("%06d", lives));
            livesWidget.setLives(lives); // Update the lives count in the widget
        }
        else{
            Cat.current = Cat.State.DEAD;
            //setDead();
        }


         */

        lives -= value;
        live.setText(String.format("%06d", lives));
        livesWidget.setLives(lives); // Update the lives count in the widget
    }

    public void callQuestScreen() {
        GameplayQ gameplayQ = new GameplayQ();
        gameplayQ.create(stage);
    }

    public void callQuestsScreen() {
        QuestPlay gameplayQ = new QuestPlay();
        gameplayQ.create(stage);
    }
    public void callFinishScreen() {
        FinishGame gameplayQ = new FinishGame();
        gameplayQ.create(stage,t);
    }

    public void gainLive(int value) {
        lives += value;
        live.setText(String.format("%06d", lives));
        livesWidget.setLives(lives); // Update the lives count in the widget
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public static int getLives() {
        return lives;
    }

    public static int getLevels() {
        return levels;
    }

    private class LivesWidget extends Actor {
        private Texture heartTexture;
        private int livesCount;

        public LivesWidget(Texture heartTexture, int livesCount) {
            this.heartTexture = heartTexture;
            this.livesCount = livesCount;
            setSize(heartTexture.getWidth() * livesCount, heartTexture.getHeight());
        }

        public void setLives(int newLives) {
            this.livesCount = newLives;
            setSize(heartTexture.getWidth() * livesCount, heartTexture.getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            for (int i = 0; i < livesCount; i++) {
                batch.draw(heartTexture, getX() + i * heartTexture.getWidth(), getY());
            }
        }
    }
}