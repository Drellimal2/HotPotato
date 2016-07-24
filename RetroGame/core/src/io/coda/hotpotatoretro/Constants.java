package io.coda.hotpotatoretro;

/**
 * Created by Dane on 7/22/2016.
 */
public class Constants {

    //Textures
    public static final String PLAYER_TEXTURE = "hand_paddle.png";
    public static final String RETRO_POTATO_TEXTURE = "potato_pixels_2.png";
    public static final String LOGO_TEXTURE = "hotpotato_logo.png";
    public static final String PLAY_TEXTURE = "menu_item_play.png";
    public static final String ACHIEVEMENT_TEXTURE = "menu_item_achievement.png";


    //DIMENSIONS
    public static final float WORLD_HEIGHT = 60f;
    public static final float RETRO_POTATO_HEIGHT = WORLD_HEIGHT / 10;
    public static final float RETRO_POTATO_WIDTH = RETRO_POTATO_HEIGHT / 2;
    public static final float PLAYER_HEIGHT = WORLD_HEIGHT / 20;
    public static final float PLAYER_WIDTH = PLAYER_HEIGHT * 4;
    public static final float LOGO_HEIGHT = WORLD_HEIGHT * 2/5;
    public static final float MENU_ITEM_HEIGHT = WORLD_HEIGHT/10;
    public static final float MENU_ITEM_WIDTH = MENU_ITEM_HEIGHT * 8;
    public static final float HOME_PADDING = WORLD_HEIGHT / 20;

    //Values
    public static final float PUSH_FORCE = 40f;
    public static final float GRAVITY = -100f;
    public static final float DRAG = -10f;

    public static final float PLAYER_SPEED = 25f;



}
