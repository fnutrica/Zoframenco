package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by zoray on 4/25/16.
 */
public class EndScreen implements Screen{

    public static final int V_WIDTH = 1000;
    public static final int V_HEIGHT = 1150;

    private final MyGdxGame game;
    private OrthographicCamera camera;
    private Skin skin;
    private Stage stage;
    private TextButton restartButton;
    private SpriteBatch batch;
    private ScorePanel scores;
    private Table scoreTable;
    private Viewport scorePort;


    public EndScreen(final MyGdxGame game) {

        this.game = game;
        camera = new OrthographicCamera(HomeScreen.V_WIDTH, HomeScreen.V_HEIGHT);
        camera.setToOrtho(false,HomeScreen.V_WIDTH, HomeScreen.V_HEIGHT);
        scorePort = new FitViewport(HomeScreen.V_WIDTH, HomeScreen.V_HEIGHT, camera);
        scores = new ScorePanel(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        createButtonSkin();
        restartButton = new TextButton("Restart", skin);
        stage.addActor(restartButton);

        restartButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new com.mygdx.game.PlayScreen(game));
                dispose();
            }
        });

        scoreTable = scores.getTable();
        stage.addActor(scores.getTable());
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(250/255f, 236/255f, 129/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        restartButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/10 , Gdx.graphics.getHeight()/10);

        stage.act();
        stage.draw();
    }

    private void createButtonSkin(){
        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
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
