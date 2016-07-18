package io.coda.hotpotato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by Dane on 7/18/2016.
 */
public class HomeScreen extends InputAdapter implements Screen {

    ExtendViewport viewport;
    HotPotatoGame game;
    SpriteBatch batch;
    Texture LOGO;
    Texture PLAY;
    Texture ACH;


    public HomeScreen(HotPotatoGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspect_ratio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        viewport =new ExtendViewport(aspect_ratio * Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT );
        LOGO = new Texture(Constants.LOGO_TEXTURE);
        PLAY = new Texture(Constants.PLAY_TEXTURE);
        ACH = new Texture(Constants.ACHIEVEMENT_TEXTURE);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0.67f, 0.32f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        float aspect_ratio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        float logo_width = aspect_ratio * Constants.LOGO_HEIGHT;
        float logox = (viewport.getWorldWidth() - logo_width)/2;
        batch.draw(LOGO, logox,
                Constants.WORLD_HEIGHT - Constants.LOGO_HEIGHT - Constants.HOME_PADDING,
                logo_width, Constants.LOGO_HEIGHT);


        float menu_item_width = Constants.MENU_ITEM_WIDTH * aspect_ratio;
        float playy = Constants.WORLD_HEIGHT - Constants.LOGO_HEIGHT - 3*Constants.HOME_PADDING
                - Constants.MENU_ITEM_HEIGHT;
        float menu_itemX = (viewport.getWorldWidth() - menu_item_width)/2;
        batch.draw(PLAY, menu_itemX, playy, menu_item_width, Constants.MENU_ITEM_HEIGHT);
        float achy = Constants.WORLD_HEIGHT - Constants.LOGO_HEIGHT - 4*Constants.HOME_PADDING
                - 2*Constants.MENU_ITEM_HEIGHT;
        batch.draw(ACH, menu_itemX, achy, menu_item_width, Constants.MENU_ITEM_HEIGHT);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
        LOGO.dispose();
        PLAY.dispose();
        ACH.dispose();
        batch.dispose();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
