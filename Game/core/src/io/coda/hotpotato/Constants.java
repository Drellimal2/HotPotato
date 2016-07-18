package io.coda.hotpotato;

/**
 * Created by Dane on 7/17/2016.
 */
public class Constants {

    public static final float GRAVITY = -9.81f;
    public static final float DRAG = -10f;


    //Textures
    public static final String POTATO_TEXTURE = "potato.png";
    public static final String LOGO_TEXTURE = "hotpotato_logo.png";
    public static final String PLAY_TEXTURE = "menu_item_play.png";
    public static final String ACHIEVEMENT_TEXTURE = "menu_item_achievement.png";


    //Dimensions
    public static final float WORLD_HEIGHT = 60f;
    public static final float POTATO_HEIGHT = WORLD_HEIGHT/3;
    public static final float POTATO_WIDTH = POTATO_HEIGHT * 2 / 3;
    public static final float LOGO_HEIGHT = WORLD_HEIGHT * 2/5;
    public static final float MENU_ITEM_HEIGHT = WORLD_HEIGHT/10;
    public static final float MENU_ITEM_WIDTH = MENU_ITEM_HEIGHT * 8;
    public static final float HOME_PADDING = WORLD_HEIGHT/20;

    //Values
    public static final float PUSH_FORCE = 20f;

    public static final float MULTIPLIER_LEVEL_1 = 0.5f;
    public static final float MULTIPLIER_LEVEL_2 = 0.75f;
    public static final float MULTIPLIER_LEVEL_3 = 1.0f;
    public static final float MULTIPLIER_LEVEL_4 = 1.5f;
    public static final float MULTIPLIER_LEVEL_5 = 2.0f;






}
