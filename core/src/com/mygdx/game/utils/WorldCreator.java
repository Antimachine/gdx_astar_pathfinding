package com.mygdx.game.utils;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameManager;
import com.mygdx.game.ai.AStarMap;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public class WorldCreator {

    private final TiledMap tiledMap;
    private final World world;
    private final NodeDefiner nodeDefiner;

    public WorldCreator(TiledMap tiledMap, World world) {
        this.tiledMap = tiledMap;
        this.world = world;
        nodeDefiner = new NodeDefiner();
    }

    public AStarMap generateMap() {
        MapLayers mapLayers = tiledMap.getLayers();


        int mapWidth = ((TiledMapTileLayer) mapLayers.get(0)).getWidth();
        int mapHeight = ((TiledMapTileLayer) mapLayers.get(0)).getHeight();


        MapLayer wallLayer = mapLayers.get("hedge");

        for (MapObject mapObject : wallLayer.getObjects()) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            new B2Body.Builder(world)
                    .rectangle(rectangle)
                    .bodyType(BodyDef.BodyType.StaticBody)
                    .categoryBits(GameManager.WALL)
                    .build()
                    .generateBody();
        }

        AStarMap aStarMap = new AStarMap(mapWidth, mapHeight);
        nodeDefiner.defineWall(world, aStarMap);
        return aStarMap;
    }
}
