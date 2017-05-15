package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.ai.PathFinder;

public class MyAiImpl extends ApplicationAdapter {


    int[][] map = {
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1}
    };


    @Override
    public void create() {
        PathFinder pathFinder = new PathFinder(map);
        pathFinder.calculatePath();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        shapeRenderer.begin(S);
    }

    @Override
    public void dispose() {

    }


}
