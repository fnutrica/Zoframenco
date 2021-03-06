package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by zoray on 3/23/16.
 */

public class HomeScreen implements Screen{

    private static OrthographicCamera camera= new OrthographicCamera();;
    private Stage stage;
    private Texture background = new Texture(Gdx.files.internal("images/main_menu_small.png"));;
    private Table buttonTable = new Table();
    private TextButton startGameButton;
    private TextButton instructionButton;
    private FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal("fonts/SIXTY.TTF"));;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter= new FreeTypeFontGenerator.FreeTypeFontParameter();;
    private BitmapFont font= new BitmapFont();;
    private Skin skin;



    public HomeScreen(final MyGdxGame game){

        camera.setToOrtho(false, MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);
        Gdx.graphics.setWindowedMode(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT);

        setUpFont();
        createButtonSkin();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        startGameButton = new TextButton("Start a game", skin);
        stage.addActor(startGameButton);
        startGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new  com.mygdx.game.PlayScreen(game));
                dispose();
            }
        });

        instructionButton = new TextButton("Instructions", skin);
        stage.addActor(instructionButton);
        instructionButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new com.mygdx.game.InstructionScreen(game));
                dispose();
                System.gc();
            }
        });
    }

    @Override
    public void show(){

    }

    @Override
    public void render(float delta) {

        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(batch, "THE DAILY DRIVER!", Gdx.graphics.getWidth()/50, Gdx.graphics.getHeight()*7/8);
        font.draw(batch, "Team Zoframenco", Gdx.graphics.getWidth()/50, Gdx.graphics.getHeight()*6/8);
        batch.end();

        startGameButton.setPosition(camera.viewportWidth/2 - camera.viewportWidth/10 , camera.viewportHeight/12);
        instructionButton.setPosition(camera.viewportWidth/2 + camera.viewportWidth/5 , camera.viewportHeight/12);

        stage.act();
        stage.draw();

    }

    private void setUpFont() {
        parameter.size = 44;
        parameter.color = Color.YELLOW;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        font = generator.generateFont(parameter);
    }

    private void createButtonSkin(){
        skin = new Skin();
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = font;
        skin.add("default", textButtonStyle);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
