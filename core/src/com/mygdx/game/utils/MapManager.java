package com.mygdx.game.utils;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by Evgheniy on 5/15/2017.
 */

public enum MapManager {
    INSTANCE;

    public TiledMap map;
    public static int width;
    public static int height;

    public void loadMap(String mapPath) {
        map = new TmxMapLoader().load(mapPath);
        width = map.getProperties().get("width", Integer.class);
        height = map.getProperties().get("height", Integer.class);


    }


}
