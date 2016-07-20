package io.coda.hotpotato;

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
 * Created by Dane on 7/17/2016.
 */
public class GameScreen extends InputAdapter implements Screen {
    HotPotatoGame game;
    SpriteBatch batch;
    Potato potato;
    ScreenViewport HUD;
    ExtendViewport viewport;
    Texture background;
    BitmapFont HUD_Font;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private long taps = 0;
    FreeTypeFontGenerator generator;


    public GameScreen(HotPotatoGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        HUD = new ScreenViewport();
//        generator = new FreeTypeFontGenerator(Gdx.files.internal("MANIFESTO.ttf"));
        generator = new FreeTypeFontGenerator(Gdx.files.internal("DIGITALDREAM.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        int font_size = HUD.getScreenHeight()/8;
        parameter.size = 144;
        parameter.color = new Color(1,232/255f,81f/255,1);
        parameter.borderColor = new Color(1,179/255f,81f/255,1);
        parameter.borderWidth = 2;
//        HUD.setWorldHeight(100f);
        HUD_Font = generator.generateFont(parameter); // font size 12 pixels
        HUD_Font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//        HUD_Font.getData().setScale(5f);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        float aspect_ratio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        viewport =new ExtendViewport(aspect_ratio * Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT );
//        viewport =new ExtendViewport(aspect_ratio * Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT );
        potato = new Potato(viewport);
        background = new Texture(Constants.BACKGROUND_OVEN);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        potato.update(delta);
        batch.begin();
        batch.draw(background, 0, 0, viewport.getWorldWidth(),viewport.getWorldHeight());
        potato.render(batch);
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
        generator = new FreeTypeFontGenerator(Gdx.files.internal("DIGITALDREAM.ttf"));
//        generator = new FreeTypeFontGenerator(Gdx.files.internal("MANIFESTO.ttf"));
        parameter.size = (int) HUD.getScreenHeight()/8;
        HUD_Font = generator.generateFont(parameter); // font size 12 pixels
//        HUD_Font.getData().setScale(5f);
        generator.dispose();
        potato.init();
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
        Gdx.app.log("POTATO W", worldClick.toString());
        Gdx.app.log("POTATO P", potato.position.toString());

        float xmaxdif = potato.position.x + Constants.POTATO_WIDTH / 2;
        float xmindif = potato.position.x - Constants.POTATO_WIDTH / 2;
        float ymaxdif = potato.position.y + Constants.POTATO_HEIGHT / 2;
        float ymindif = potato.position.y - Constants.POTATO_HEIGHT / 2;

        if ( worldClick.x > xmindif && worldClick.x < xmaxdif
                && worldClick.y > ymindif && worldClick.y < ymaxdif )  {
            Gdx.app.log("POTATO", "Inside");
            taps += 1;
            if(worldClick.x < potato.position.x){
                potato.velocity.x += Constants.PUSH_FORCE;
            } else{
                potato.velocity.x -= Constants.PUSH_FORCE;
            }
            potato.velocity.y += potato.getForce(ymaxdif - worldClick.y);

        }

        potato.collideWithWalls(viewport.getWorldWidth(), viewport.getWorldHeight());
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
