package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameManager;
import com.mygdx.game.MyAiImpl;
import com.mygdx.game.ai.AStarMap;
import com.mygdx.game.ai.MyNode;
import com.mygdx.game.ai.MyPathFinder;
import com.mygdx.game.utils.B2Body;
import com.mygdx.game.utils.BodyController;
import com.mygdx.game.utils.MapManager;
import com.mygdx.game.utils.PathPrinter;
import com.mygdx.game.utils.WallDrawer;
import com.mygdx.game.utils.WorldCreator;

/**
 * Created by Evgheniy on 5/17/2017.
 */

public class PlayScreen implements Screen {
    public static final String MAP_PATH = "maps/empty_lvl.tmx";
    public SpriteBatch spriteBatch;


    private OrthogonalTiledMapRenderer isometricTiledMapRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private World world;
    private Box2DDebugRenderer boxDebugger;
    private final BodyController bodyController;
    private final MyPathFinder myPathFinder;
    private final PathPrinter pathPrinter;
    private final Body actor;
    private final Body target;
    private float timer;
    private final B2Body.Builder box2dBuilder;
    private final ShapeRenderer shapeRenderer;
    private final AStarMap map;
    private final WallDrawer wallDrawer;

    public PlayScreen(MyAiImpl game) {
        spriteBatch = game.spriteBatch;

        MapManager.INSTANCE.loadMap(MAP_PATH);

        camera = new OrthographicCamera();


        viewport = new FitViewport(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, camera);
        viewport.apply();
        isometricTiledMapRenderer = new OrthogonalTiledMapRenderer(MapManager.INSTANCE.map, 1 / GameManager.PPM, spriteBatch);
        world = new World(Vector2.Zero, true);


        WorldCreator worldCreator = new WorldCreator(world);

        map = worldCreator.generateMap(MapManager.INSTANCE.map);


        myPathFinder = new MyPathFinder(map);
        pathPrinter = new PathPrinter(myPathFinder);
        shapeRenderer = new ShapeRenderer();

        boxDebugger = new Box2DDebugRenderer();

        box2dBuilder = new B2Body.Builder(world);

        wallDrawer = new WallDrawer(world, map, camera);

        Gdx.input.setInputProcessor(wallDrawer);

        target = box2dBuilder.bodyType(BodyDef.BodyType.DynamicBody)
                .categoryBits(GameManager.TARGET)
                .height(1)
                .maskBits(GameManager.WALL)
                .width(1)
                .x(15)
                .y(15)
                .build().generateBody();
        actor = box2dBuilder.bodyType(BodyDef.BodyType.DynamicBody)
                .categoryBits(GameManager.ACTOR)
                .maskBits(GameManager.WALL)
                .width(1)
                .height(1)
                .x(20)
                .y(20)
                .build().generateBody();

        bodyController = new BodyController(actor, target);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        isometricTiledMapRenderer.setView(camera);
        isometricTiledMapRenderer.render();
        world.step(1 / 60f, 8, 3);


        //Highlight the fastest path
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        pathPrinter.highlightPath(shapeRenderer, target.getPosition(), actor.getPosition());
        shapeRenderer.end();


        spriteBatch.begin();
        wallDrawer.render(spriteBatch);
        spriteBatch.end();

        update();


    }


    private void update() {
        //pursue();
        boxDebugger.render(world, viewport.getCamera().combined);
        bodyController.handleActions();
        bodyController.handleTarget();

    }


    private void pursue() {

        if (timer > .5f) {
            // System.out.println("targetPosition " + target.getPosition() + " actorposition " + actor.getPosition());

            MyNode pursueNode = myPathFinder.findNextNode(target.getPosition(), actor.getPosition());
            System.out.println(pursueNode);
            timer = 0;
        }
        timer += Gdx.graphics.getDeltaTime();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
