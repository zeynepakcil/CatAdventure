package com.mygdx.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class QCreator extends Window {
    TextureAtlas textureAtlas;
    public static int questionNo;
    private boolean[] isQuestionsShown;
    private static final WindowStyle windowStyle;
    private static final ImageButtonStyle yesButtonStyle, noButtonStyle;
    private static Image[] questions;
    private static Image close, questionAsk;

    static {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("questionScreenTiles.pack"));
        windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(textureAtlas.findRegion("questionBackground")));
        yesButtonStyle = new ImageButtonStyle();
        noButtonStyle = new ImageButtonStyle();
        yesButtonStyle.imageUp = new TextureRegionDrawable(textureAtlas.findRegion("yesButton"));
        noButtonStyle.imageUp = new TextureRegionDrawable(textureAtlas.findRegion("noButton"));
    }

    public QCreator() {
        super("", windowStyle);
        TextureAtlas texAtlas = new TextureAtlas(Gdx.files.internal("question1.pack"));
        TextureAtlas closeAtlas = new TextureAtlas(Gdx.files.internal("closeIconTileset.pack"));
        TextureAtlas questionAskAtlas = new TextureAtlas(Gdx.files.internal("questionAsk.pack"));
        questions = new Image[3];
        questions[0] = new Image(texAtlas.findRegion("question1"));
        questions[1] = new Image(texAtlas.findRegion("question2"));
        questions[2] = new Image(texAtlas.findRegion("question3"));
        close = new Image(closeAtlas.findRegion("closeIcon"));
        questionAsk = new Image(questionAskAtlas.findRegion("questionAsk"));
        isQuestionsShown = new boolean[3];
        isQuestionsShown[0] = false;
        isQuestionsShown[1] = false;
        isQuestionsShown[2] = false;
        final Button yesButton = new ImageButton(yesButtonStyle);
        final Button noButton = new ImageButton(noButtonStyle);
        yesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getTitleTable().removeActor(questionAsk);
                getTitleTable().removeActor(yesButton);
                getTitleTable().removeActor(noButton);
                if (!isQuestionsShown[0]) {
                    getTitleTable().add(questions[0]).size((int) (1100 / 2.5), (int) (516 / 2.5)).padTop(200)
                            .padRight(550);
                    questions[0].addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            setVisible(false);
                        }
                    });
                    isQuestionsShown[0] = true;
                } else if (!isQuestionsShown[1]) {
                    getTitleTable().add(questions[1]).size((int) (1100 / 2.5), (int) (516 / 2.5)).padTop(200)
                            .padRight(550);
                    questions[0].addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            setVisible(false);
                        }
                    });

                    isQuestionsShown[1] = true;
                } else if (!isQuestionsShown[2]) {
                    getTitleTable().add(questions[2]).size((int) (1100 / 2.5), (int) (516 / 2.5)).padTop(200)
                            .padRight(550);
                    questions[0].addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            setVisible(false);
                        }
                    });

                    isQuestionsShown[2] = true;
                }

            }
        });
        noButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
        getTitleTable().add(questionAsk).size(700 / 5, 100 / 5).padLeft(400).padTop(100);
        getTitleTable().add(yesButton).size(96 / 2, 64 / 2).padRight(450).padTop(300);
        getTitleTable().add(noButton).size(96 / 2, 64 / 2).center().padTop(300);
        // getTitleTable().add(noButton).size(96 / 2, 64 / 2).padLeft(10);

        setClip(false);
        setTransform(true);
    }
}