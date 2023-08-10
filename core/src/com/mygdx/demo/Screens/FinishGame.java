package com.mygdx.demo.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.mygdx.demo.TuruncsAdventure;

public class FinishGame extends ApplicationAdapter implements InputProcessor {

    private Stage stage;
    private FinishScreen gamePlayQ;
    private TuruncsAdventure t;

    public void create(Stage stage, TuruncsAdventure t) {
        this.stage = stage;
        this.t =t;
        gamePlayQ = new FinishScreen(t);
        gamePlayQ.setSize(TuruncsAdventure.WIDTH - 10, TuruncsAdventure.HEIGHT - 10);
        gamePlayQ.setModal(true);
        gamePlayQ.setVisible(true);
        gamePlayQ.setMovable(true);
        gamePlayQ.setPosition(TuruncsAdventure.WIDTH / TuruncsAdventure.PPM, TuruncsAdventure.HEIGHT / TuruncsAdventure.PPM);
        stage.addActor(gamePlayQ);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.NUM_1) {
            gamePlayQ.setVisible(true);
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}