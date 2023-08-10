package com.mygdx.demo.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.demo.TuruncsAdventure;

public class HowToScreen extends Window{
    private static final WindowStyle windowStyle;
    private static final ImageButtonStyle backButtonStyle;
    private TuruncsAdventure game;
    static {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("howToTileSet.pack"));
        windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK,
                new TextureRegionDrawable(textureAtlas.findRegion("howToPlay")));
        backButtonStyle = new ImageButtonStyle();
        backButtonStyle.imageUp = new TextureRegionDrawable(textureAtlas.findRegion("backIcon"));
    }
    public HowToScreen(TuruncsAdventure game){
        super("", windowStyle);
        this.game = game;
        final Button backButton = new ImageButton(backButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setVisible(false);
            }
        });
        getTitleTable().add(backButton).size(64, 48).padRight(10).padTop(10);
        setClip(false);
        setTransform(true);

    }
}
