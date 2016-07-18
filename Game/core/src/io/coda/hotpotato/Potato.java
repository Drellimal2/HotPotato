package io.coda.hotpotato;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Dane on 7/17/2016.
 */
public class Potato extends InputAdapter {

    Vector2 position;
    Vector2 velocity;
    Texture texture;
    Viewport viewport;

    public Potato(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2();
        texture = new Texture(Constants.POTATO_TEXTURE);

    }


    public Potato(Viewport viewport) {
        this.viewport = viewport;
        texture = new Texture(Constants.POTATO_TEXTURE);
        init();

    }

    public void init(){
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        velocity = new Vector2();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        Gdx.app.log("POTATO W", worldClick.toString());
        Gdx.app.log("POTATO P", position.toString());

        float xmaxdif = position.x + Constants.POTATO_WIDTH / 2;
        float xmindif = position.x - Constants.POTATO_WIDTH / 2;
        float ymaxdif = position.y + Constants.POTATO_HEIGHT / 2;
        float ymindif = position.y - Constants.POTATO_HEIGHT / 2;

        if ( worldClick.x > xmindif && worldClick.x < xmaxdif
                && worldClick.y > ymindif && worldClick.y < ymaxdif )  {
            Gdx.app.log("POTATO", "Inside");
            if(worldClick.x < position.x){
                velocity.x = Constants.PUSH_FORCE;
            } else{
                velocity.x = -Constants.PUSH_FORCE;
            }
            velocity.y = getForce(ymaxdif - worldClick.y);

        }
        collideWithWalls(viewport.getWorldWidth(), viewport.getWorldHeight());
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void update(float delta) {
        position.y += changeS(velocity.y, delta);
        position.x += changeS(velocity.x, delta);
        if (velocity.x != 0) {
            if (velocity.x < 0) {
                velocity.x -= delta * Constants.DRAG;
            } else {
                velocity.x += delta * Constants.DRAG;
            }
        }
        velocity.y += delta * Constants.GRAVITY;
        velocity.clamp(0, 101);
        collideWithWalls(viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    private void collideWithWalls(float viewportWidth, float viewportHeight) {
        if (position.x - Constants.POTATO_WIDTH/2 < 0) {
            position.x = Constants.POTATO_WIDTH/2;
            velocity.x = -velocity.x;
        }
        if (position.x + Constants.POTATO_WIDTH/2 > viewportWidth) {
            position.x = viewportWidth - Constants.POTATO_WIDTH/2;
            velocity.x = -velocity.x;
        }
        if (position.y - Constants.POTATO_HEIGHT/2 < 0) {
            position.y = Constants.POTATO_HEIGHT/2;
            velocity.y = -velocity.y/2;
        }
        if (position.y + Constants.POTATO_HEIGHT/2 > viewportHeight) {
            position.y = viewportHeight - Constants.POTATO_HEIGHT/2;
            velocity.y = -velocity.y;
        }
    }



    private float changeS(float velocity, float time){
        float s;
        s = time * velocity + Constants.GRAVITY * time * time;
        return s;

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, getDrawX(), getDrawY(), Constants.POTATO_WIDTH, Constants.POTATO_HEIGHT);
    }


    private float getDrawX(){
        return position.x - Constants.POTATO_WIDTH/2;
    }

    private float getDrawY(){
        return position.y - Constants.POTATO_HEIGHT/2;
    }


    private float getForce(float diff){
        float scale = Constants.POTATO_HEIGHT;
        float result = Constants.PUSH_FORCE;

        float test = diff/scale;
        if(test < 0.25){
            return Constants.MULTIPLIER_LEVEL_1 * result;
        }
        if(test < 0.5){
            return Constants.MULTIPLIER_LEVEL_2 * result;
        }
        if(test == 0.5){
            return Constants.MULTIPLIER_LEVEL_3 * result;
        }
        if(test < 0.75){
            return Constants.MULTIPLIER_LEVEL_4 * result;
        }
        if(test < 1){
            return Constants.MULTIPLIER_LEVEL_5 * result;
        }

        return result;
    }
}
