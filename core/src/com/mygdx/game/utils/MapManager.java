package com.mygdx.game.utils;


import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.GameManager;

/**
 * Created by Evgheniy on 5/15/2017.
 */

public enum MapManager {
    INSTANCE;

    public TiledMap map;
    public static int mapWidth;
    public static int mapHeight;

    public static int tilePixelHeight;
    public static int tilePixelWidth;
    public static float mapPixelWidth;
    public static float mapPixelHeight;

    public void loadMap(String mapPath) {
        map = new TmxMapLoader().load(mapPath);
        MapProperties mapProperties = map.getProperties();

        mapWidth = mapProperties.get("width", Integer.class);
        mapHeight = mapProperties.get("height", Integer.class);


        tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        tilePixelWidth = mapProperties.get("tilewidth", Integer.class);

        mapPixelWidth = (mapWidth * tilePixelWidth) / GameManager.PPM;
        mapPixelHeight = (mapHeight * tilePixelHeight) / GameManager.PPM;

    }


}
