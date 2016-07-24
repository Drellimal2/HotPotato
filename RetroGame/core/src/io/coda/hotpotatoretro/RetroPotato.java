package io.coda.hotpotatoretro;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Dane on 7/20/2016.
 */
public class RetroPotato {



    Vector2 position;
    Vector2 startingPos;
    Vector2 velocity;
    Texture texture;
    Viewport viewport;

    public RetroPotato(Vector2 position, Viewport viewport) {
        this.position = position;
        this.velocity = new Vector2();
        this.viewport = viewport;
        texture = new Texture(Constants.RETRO_POTATO_TEXTURE);

    }


    public RetroPotato(Viewport viewport) {
        this.viewport = viewport;
        texture = new Texture(Constants.RETRO_POTATO_TEXTURE);
        init();

    }

    public void init(){
        this.position = new Vector2(viewport.getWorldWidth()/2, viewport.getWorldHeight()-Constants.HOME_PADDING);
        velocity = new Vector2(0f, 1f);
    }


    public void update(float delta) {

        velocity.mulAdd(new Vector2(0,Constants.GRAVITY), delta);
        position.mulAdd(velocity, delta);

        collideWithWalls(viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    public void collideWithWalls(float viewportWidth, float viewportHeight) {
        if (position.x - Constants.RETRO_POTATO_WIDTH/2 < 0) {
            position.x = (Constants.RETRO_POTATO_WIDTH/2);
            velocity.x = -velocity.x;
        }
        if (position.x + Constants.RETRO_POTATO_WIDTH/2 > viewportWidth) {
            position.x = viewportWidth - (Constants.RETRO_POTATO_WIDTH/2);
            velocity.x = -velocity.x;
        }
        if (position.y - Constants.RETRO_POTATO_HEIGHT/2 < 0) {
            position.y = Constants.RETRO_POTATO_HEIGHT/2;
            velocity.y = -velocity.y;
        }
        if (position.y - Constants.RETRO_POTATO_HEIGHT/2 > viewportHeight) {

//            position.y = viewportHeight - Constants.RETRO_POTATO_HEIGHT/2;
//            velocity.y = -velocity.y;
        }
    }



    private float changeS(float velocity, float time){
        float s;
        s = time * velocity + Constants.GRAVITY * time * time;
        return s;

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, getDrawX(), getDrawY(), Constants.RETRO_POTATO_HEIGHT, Constants.RETRO_POTATO_HEIGHT);
    }


    private float getDrawX(){
        return position.x - Constants.RETRO_POTATO_WIDTH/2;
    }

    private float getDrawY(){
        return position.y - Constants.RETRO_POTATO_HEIGHT/2;
    }

    public void randomPush(){
        float vx =(float) Math.random() * 10f;
        float vy = (float) Math.random() * 8f;
        Vector2 vel = new Vector2(vx, vy);
        velocity.add(vel);
    }

}


