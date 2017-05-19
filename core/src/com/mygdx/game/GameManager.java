package com.mygdx.game;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public final class GameManager {


    public static short EMPTY_BIT = 0;
    public static final short WALL = 1 << 1;
    public static final short ACTOR = 2 << 1;
    public static final short TARGET = 3 << 1;


    public static float PPM = 16;

    public static int WORLD_WIDTH = 50;
    public static int WORLD_HEIGHT = 50;


}
