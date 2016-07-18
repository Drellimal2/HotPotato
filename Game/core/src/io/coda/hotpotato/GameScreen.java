package io.coda.hotpotato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * Created by Dane on 7/17/2016.
 */
public class GameScreen implements Screen {
    HotPotatoGame game;
    SpriteBatch batch;
    Potato potato;
    ExtendViewport viewport;
    Texture background;


    public GameScreen(HotPotatoGame game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        float aspect_ratio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
        viewport =new ExtendViewport(aspect_ratio * Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT );
//        viewport =new ExtendViewport(aspect_ratio * Constants.WORLD_HEIGHT, Constants.WORLD_HEIGHT );
        potato = new Potato(viewport);
        background = new Texture(Constants.BACKGROUND_GAME_TEXTURE);
        Gdx.input.setInputProcessor(potato);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        potato.update(delta);
        batch.begin();
        batch.draw(background, 0, viewport.getWorldHeight()/3, viewport.getWorldWidth(),viewport.getWorldHeight() *2/3 );
        potato.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
}
