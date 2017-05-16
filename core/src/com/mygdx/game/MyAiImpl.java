package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.utils.CameraOperator;
import com.mygdx.game.utils.MapManager;

public class MyAiImpl extends ApplicationAdapter {


    public static final String MAP_PATH = "maps/empty_lvl.tmx";


    int[][] map = {
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1}
    };
    private OrthogonalTiledMapRenderer isometricTiledMapRenderer;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private CameraOperator operator;


    @Override
    public void create() {

        MapManager.INSTANCE.loadMap(MAP_PATH);

        camera = new OrthographicCamera(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT);
        operator = new CameraOperator(camera);

        viewport = new ExtendViewport(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, camera);

        isometricTiledMapRenderer = new OrthogonalTiledMapRenderer(MapManager.INSTANCE.map);
        camera.position.x += GameManager.WORLD_WIDTH / 2;
        camera.position.y += GameManager.WORLD_HEIGHT / 2;

        System.out.println(camera.position);
        camera.update();


//        PathFinder pathFinder = new PathFinder(map);
//        pathFinder.calculatePath();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        isometricTiledMapRenderer.setView(camera);
        isometricTiledMapRenderer.render();

        operator.handleMovings();
    }


    @Override
    public void dispose() {

    }


}
