package io.coda.hotpotatoretro;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Dane on 7/20/2016.
 */
public class Potatoes  {
    //        int iciclesDodged;
    DelayedRemovalArray<RetroPotato> potatoList;
    Viewport viewport;

    public Potatoes(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        potatoList = new DelayedRemovalArray<RetroPotato>(false, 1);
    }

    public void update(float delta) {

        if(potatoList.size == 0){
            Vector2 newPotatoPosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight());
            RetroPotato potato = new RetroPotato(newPotatoPosition, viewport);
            potatoList.add(potato);
        }
        for (RetroPotato potato : potatoList) {
            potato.update(delta);
        }

//            potatoList.begin();
//            for (int i = 0; i < potatoList.size; i++) {
//                if (potatoList.get(i).position.y < -Constants.RETRO_POTATO_HEIGHT) {
////                    iciclesDodged += 1;
//                    potatoList.removeIndex(i);
//                }
//            }
//            potatoList.end();
    }

    public void render(SpriteBatch batch) {
        for (RetroPotato potato : potatoList) {
            potato.render(batch);
        }
    }
}