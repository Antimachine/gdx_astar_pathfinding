package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameManager;
import com.mygdx.game.ai.AStarMap;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public class NodeDefiner {
    private boolean wall;


    public void defineWall(World world, AStarMap map) {

        float lowerShift = 0.2f;
        float higherShift = 0.8f;

        QueryCallback callback = new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                wall = fixture.getFilterData().categoryBits == GameManager.WALL;
                return false;
            }
        };

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                wall = false;
                world.QueryAABB(callback, x + lowerShift, y + lowerShift, x + higherShift, y + higherShift);
                if (wall)
                    map.getNodeAt(x, y).setIsWall();
            }
        }


    }

}
