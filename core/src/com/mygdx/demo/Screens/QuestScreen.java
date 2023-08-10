package com.mygdx.demo.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class QuestScreen extends Window {

    private static WindowStyle windowStyle;
    private static Image[] quests;
    private static Image close;
    private static int count = 0;


    static {
        windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(new Texture("Empty.png")));
    }

    public QuestScreen() {
        super("", windowStyle);
        TextureAtlas texAtlas = new TextureAtlas(Gdx.files.internal("question1.pack"));
        TextureAtlas closeAtlas = new TextureAtlas(Gdx.files.internal("closeIconTileset.pack"));
        quests = new Image[3];
        quests[0] = new Image(new Texture("Quest.png"));
        quests[1] = new Image(new Texture("Quest2.png"));
        quests[2] = new Image(new Texture("Quest3.png"));
        close = new Image(closeAtlas.findRegion("closeIcon"));

        if (count == 0) {
            getTitleTable().add(quests[0]).size((int) (1100 / 3), (int) (516 / 3)).padTop(200).padRight(10);
            quests[0].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setVisible(false);
                }
            });

            count++;

        } else if (count == 1) {
            getTitleTable().add(quests[1]).size((int) (1100 / 3), (int) (516 / 3)).padTop(200).padRight(10);
            quests[1].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setVisible(false);
                }
            });
            count++;
        } else if (count == 2) {
            getTitleTable().add(quests[2]).size((int) (1100 / 3), (int) (516 / 3)).padTop(200).padRight(10);
            quests[2].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    setVisible(false);
                }
            });
        }
        setClip(false);
        setTransform(true);
    }

}
