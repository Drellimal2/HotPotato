package io.coda.hotpotatoretro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by Dane on 7/20/2016.
 */
public class RetroGameScreen extends InputAdapter implements Screen {


    HotPotatoRetroGame game;
    SpriteBatch batch;
    RetroPotato potato;
    Potatoes potatoes;
    Player player;
    ScreenViewport HUD;
    ExtendViewport viewport;
    Texture background;
    BitmapFont HUD_Font;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private long taps = 0;
    FreeTypeFontGenerator generator;


    public RetroGameScreen(HotPotatoRetroGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        HUD = new ScreenViewport();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("MANIFESTO.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 144;
        parameter.color = new Color(1,232/255f,81f/255,1);
        parameter.borderColor = new Color(1,179/255f,81f/255,1);
        parameter.borderWidth = 2;

        HUD_Font = generator.generateFont(parameter);
        HUD_Font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        generator.dispose();


        float aspect_ratio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        viewport =new ExtendViewport(aspect_ratio * Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT );

        potatoes = new Potatoes(viewport);
//        potato = new RetroPotato(viewport);
        player = new Player(viewport);
//        background = new Texture(Constants.BACKGROUND_KITCHEN);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);

        potatoes.update(delta);
        player.update(delta);
        player.hitPotatoes(potatoes);
        batch.begin();

        potatoes.render(batch);
        player.render(batch);

        batch.end();

        HUD.apply();

        batch.setProjectionMatrix(HUD.getCamera().combined);
        batch.begin();

        float HUD_X = 100f*HUD.getWorldWidth()/512;
        float HUD_Y = 990*HUD.getWorldHeight()/1024f;

        HUD_Font.draw(batch, ""+taps, HUD_X, HUD_Y);

        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        HUD.update(width, height, true);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("MANIFESTO.ttf"));
        parameter.size = (int) HUD.getScreenHeight()/8;
        HUD_Font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
        potatoes.init();
        player.init();
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
        batch.dispose();
        potato.texture.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        if (worldClick.x > viewport.getWorldWidth()/2){
            Gdx.app.log("P", "Right");
        } else {
            Gdx.app.log("P", "Left");

        }


        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        if (worldClick.x > viewport.getWorldWidth()/2){
            Gdx.app.log("D", "Right");
        } else {
            Gdx.app.log("D", "Left");

        }
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
