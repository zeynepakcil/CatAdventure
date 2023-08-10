package com.mygdx.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.Entity.Cat;
import com.mygdx.demo.Entity.Enemy;
import com.mygdx.demo.Levels.LevelState;
import com.mygdx.demo.Objects.WorldContactListener;
import com.mygdx.demo.Objects.WorldCreator;
import com.mygdx.demo.TuruncsAdventure;

public class GameScreen implements Screen {
    private TuruncsAdventure turuncsAdventure;
    private TextureAtlas atlas;
    private OrthographicCamera camera;
    private Viewport gameport;
    private LevelState levelBar;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private WorldCreator creator;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Cat cat;
    public Music music;
    private Stage stage;
    private Texture pauseButtonTexture;
    private Texture pauseMenuBackgroundTexture; // Texture for the pause menu background
    private Texture resumeButtonTexture;
    private Texture quitButtonTexture;
    private TextureRegionDrawable buttonDrawable;
    private BitmapFont font;
    private SpriteBatch batch;
    private boolean isPaused = false;
    private MenuScreen menuScreen;

    public GameScreen(TuruncsAdventure turuncsAdventure) {
        this.menuScreen = new MenuScreen(turuncsAdventure);
        menuScreen.music.pause();
        atlas = new TextureAtlas("turunc_new_chars.pack");
        this.turuncsAdventure = turuncsAdventure;
        camera = new OrthographicCamera();
        gameport = new FitViewport(turuncsAdventure.WIDTH / TuruncsAdventure.PPM, turuncsAdventure.HEIGHT / TuruncsAdventure.PPM, camera);
        gameport.apply();
        levelBar = new LevelState(turuncsAdventure.batch, cat,turuncsAdventure);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/Map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / TuruncsAdventure.PPM);
        camera.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0, -10), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        cat = new Cat(this);
        creator = new WorldCreator(this);
        world.setContactListener(new WorldContactListener());
        music = TuruncsAdventure.manager.get("Audio/music/pixel-song-18-72641.mp3", Music.class);
        music.setLooping(true);
        music.play();
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        pauseButtonTexture = new Texture("pMini.png"); // Replace with your pause button texture
        pauseMenuBackgroundTexture = new Texture("Menu/pause.png"); // Replace with your pause menu background texture
        resumeButtonTexture = new Texture("Menu/continue.png"); // Replace with your resume button texture
        //restartButtonTexture = new Texture("restart_button.png"); // Replace with your restart button texture
        quitButtonTexture = new Texture("Menu/quit2.png"); // Replace with your quit button texture

        buttonDrawable = new TextureRegionDrawable(new TextureRegion(resumeButtonTexture));

        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(pauseButtonTexture)));
        pauseButton.setPosition(Gdx.graphics.getWidth() - pauseButton.getWidth() - 10, Gdx.graphics.getHeight() - pauseButton.getHeight() - 10);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showPauseMenu();
            }
        });

        // Add actors to the stage
        stage.addActor(pauseButton);

    }

    private ImageButton createPauseMenuButton(Texture texture, final ClickListener listener) {
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton button = new ImageButton(buttonStyle);
        button.addListener(listener);
        return button;
    }

    private void showPauseMenu() {
        // Create UI elements for pause menu
        ImageButton continueButton = createPauseMenuButton(resumeButtonTexture, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isPaused = false;
            }
        });


        ImageButton quitButton = createPauseMenuButton(quitButtonTexture, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Return to the main menu
                turuncsAdventure.setScreen(new MenuScreen(turuncsAdventure));
                music.stop();
                menuScreen.music.play();
            }
        });

        Table pauseMenuTable = new Table();
        pauseMenuTable.setFillParent(true);
        pauseMenuTable.background(new TextureRegionDrawable(new TextureRegion(pauseMenuBackgroundTexture)));
        pauseMenuTable.add(continueButton).padBottom(20).row();
        pauseMenuTable.add(quitButton);

        stage.addActor(pauseMenuTable);

        isPaused = true;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float d) {
        if (Cat.current != Cat.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                cat.box2d.applyLinearImpulse(new Vector2(0, 4f), cat.box2d.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && cat.box2d.getLinearVelocity().x <= 2)
                cat.box2d.applyLinearImpulse(new Vector2(0.1f, 0), cat.box2d.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && cat.box2d.getLinearVelocity().x >= -2)
                cat.box2d.applyLinearImpulse(new Vector2(-0.1f, 0), cat.box2d.getWorldCenter(), true);
        }
    }


    public void update(float d) {
        handleInput(d);
        world.step(1 / 60f, 6, 2);
        cat.update(d);
        for (int i = 0; i < creator.getAnimals().size; i++) {
            creator.getAnimals().get(i).update(d);
        }
        levelBar.update(d);
        if (Cat.current != Cat.State.DEAD) {
            camera.position.x = cat.box2d.getPosition().x;
            camera.update();
            renderer.setView(camera);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        renderer.setView(camera);
        //box2DDebugRenderer.render(world, camera.combined);
        turuncsAdventure.batch.setProjectionMatrix(camera.combined);
        turuncsAdventure.batch.begin();
        cat.draw(turuncsAdventure.batch);
        for (Enemy enemy : creator.getAnimals()) {
            enemy.draw(turuncsAdventure.batch);
        }
        turuncsAdventure.batch.end();
        turuncsAdventure.batch.setProjectionMatrix(levelBar.stage.getCamera().combined);
        levelBar.stage.draw();

        if (Gdx.input.isTouched()) {
            Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            if (Gdx.input.getX() == 0) {
                new GameOverScreen(turuncsAdventure);
                dispose();
            }
        }

        if (gameOver()) {
            turuncsAdventure.setScreen(new GameOverScreen(turuncsAdventure));
            dispose();
        }

        if (isPaused) {
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        }

    }

    public boolean gameOver() {
        if (Cat.current == Cat.State.DEAD && cat.getStateTimer() > 3)
            return true;
        return false;
    }

    public void callQuestScreen() {
        levelBar.callQuestScreen();
    }

    public void callQuestsScreen() {
        levelBar.callQuestsScreen();
    }
    public void callFinishScreen() {
      levelBar.callFinishScreen();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    public TiledMap getMap() {
        return this.map;
    }

    public World getWorld() {
        return this.world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        levelBar.dispose();
        batch.dispose();
        font.dispose();
        pauseButtonTexture.dispose();
        pauseMenuBackgroundTexture.dispose();
        resumeButtonTexture.dispose();
        quitButtonTexture.dispose();
        stage.dispose();
    }
}