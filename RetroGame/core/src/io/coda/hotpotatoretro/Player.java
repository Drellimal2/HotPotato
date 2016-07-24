package io.coda.hotpotatoretro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Dane on 7/20/2016.
 */
public class Player {
    Vector2 position;
    Texture texture;
    Viewport viewport;
    protected boolean activeTouch = false;



    public Player(Viewport viewport) {
        this.viewport = viewport;
        texture = new Texture(Constants.PLAYER_TEXTURE);
        init();

    }

    public void init(){

        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 5);
    }


    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_SPEED;
        } else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_SPEED;
        }

        if (Gdx.input.isTouched(0)) {
            float x = Gdx.input.getX(0);
            float y = Gdx.input.getY(0);
            Vector2 worldClick = viewport.unproject(new Vector2(x, y));

            if (activeTouch) {
                if (worldClick.x > viewport.getWorldWidth()/2){
                    position.x += delta * Constants.PLAYER_SPEED;
                } else {
                    position.x -= delta * Constants.PLAYER_SPEED;

                }
            } else {
                // starting a new touch ..
//                Gdx.app.log("PL", "AHHH TOUCHED");

                activeTouch = true;
                if (worldClick.x > viewport.getWorldWidth()/2){
                    position.x += delta * Constants.PLAYER_SPEED;
                } else {
                    position.x -= delta * Constants.PLAYER_SPEED;

                }
            }
        } else {
            activeTouch = false;
        }
        ensureInBounds();
    }

    private void ensureInBounds() {
        if (position.x - (Constants.PLAYER_WIDTH/2) < 0) {
            position.x = Constants.PLAYER_WIDTH/2;
        }
        if (position.x + (Constants.PLAYER_WIDTH/2) > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - (Constants.PLAYER_WIDTH/2);
        }
    }




    public void render(SpriteBatch batch) {
        float x = position.x - Constants.PLAYER_WIDTH / 2;
        batch.draw(texture,x, viewport.getWorldHeight()/5 , Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
    }


    public void move(float delta, float multiplier ){
        position.x += multiplier * delta * Constants.PLAYER_SPEED;

    }

    public boolean hitPotatoes(Potatoes potatoes) {
        boolean isHit = false;
        for (RetroPotato potato : potatoes.potatoList) {
            float poty = potato.position.y - Constants.RETRO_POTATO_HEIGHT;
            float maxy = position.y + (Constants.PLAYER_HEIGHT / 2);
            float miny = position.y - (Constants.PLAYER_HEIGHT / 2);
            float potmaxx = potato.position.x + (Constants.RETRO_POTATO_WIDTH/2);
            float potminx = potato.position.x - (Constants.RETRO_POTATO_WIDTH/2);
            float minx = position.x - (Constants.PLAYER_WIDTH/2);
            float maxx = position.x + (Constants.PLAYER_WIDTH/2);
            if ( poty > miny && poty <= maxy){
                Gdx.app.log("pfd", "" + poty + " " + miny + " " + maxy);
                float dif = Math.abs(potato.position.x - position.x);
                if(dif <= Constants.PLAYER_WIDTH/2 + Constants.RETRO_POTATO_WIDTH/2) {
                    potato.velocity.x += (dif * 2 / Constants.PLAYER_WIDTH) * 10f;
                    potato.velocity.y = -potato.velocity.y;
                    potato.randomPush();
                }

            }

        }
        return true;
    }

}
