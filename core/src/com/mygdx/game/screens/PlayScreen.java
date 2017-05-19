package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.game.utils.ActorController;
import com.mygdx.game.utils.B2Body;
import com.mygdx.game.utils.CameraOperator;
import com.mygdx.game.utils.MapManager;
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
    private CameraOperator operator;
    private World world;
    private Box2DDebugRenderer boxDebugger;
    private final ActorController actorController;
    private final MyPathFinder myPathFinder;
    private final Body actor;
    private final Body target;
    private float timer;
    private final B2Body.Builder box2dBuilder;

    public PlayScreen(MyAiImpl game) {
        spriteBatch = game.spriteBatch;

        MapManager.INSTANCE.loadMap(MAP_PATH);

        camera = new OrthographicCamera();
        operator = new CameraOperator(camera);


        viewport = new FitViewport(GameManager.WORLD_WIDTH, GameManager.WORLD_HEIGHT, camera);
        viewport.apply();
        isometricTiledMapRenderer = new OrthogonalTiledMapRenderer(MapManager.INSTANCE.map, 1 / GameManager.PPM, spriteBatch);
        world = new World(Vector2.Zero, true);


        WorldCreator worldCreator = new WorldCreator(MapManager.INSTANCE.map, world);

        AStarMap map = worldCreator.generateMap();
        System.out.println(map);


        myPathFinder = new MyPathFinder(map);


        boxDebugger = new Box2DDebugRenderer();

        box2dBuilder = new B2Body.Builder(world);


        target = box2dBuilder.bodyType(BodyDef.BodyType.DynamicBody)
                .categoryBits(GameManager.TARGET)
                .height(1)
                .maskBits(GameManager.WALL)
                .width(1)
                .x(15)
                .y(15)
                .build().generateBody();
        target.setAwake(true);
        actor = box2dBuilder.bodyType(BodyDef.BodyType.DynamicBody)
                .categoryBits(GameManager.ACTOR)
                .maskBits(GameManager.WALL)
                .width(1)
                .height(1)
                .x(20)
                .y(20)
                .build().generateBody();

        actorController = new ActorController(actor);

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
        operator.handleUserActions();


        update();
    }


    private void update() {
        pursue();
        boxDebugger.render(world, viewport.getCamera().combined);
        actorController.handleActions();
        handleTarget();

    }

    private void handleTarget() {
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            target.applyLinearImpulse(new Vector2(1, 0), actor.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            target.applyLinearImpulse(new Vector2(-1, 0), actor.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.W))
            target.applyLinearImpulse(new Vector2(0, 1), actor.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            target.applyLinearImpulse(new Vector2(0, -1), actor.getWorldCenter(), true);
        else
            target.setLinearVelocity(0, 0);


        if (target.getLinearVelocity().len2() > 1) {
            actor.setLinearVelocity(actor.getLinearVelocity().scl(1));
        }
    }

    private void pursue() {

        if (timer > .5f) {
            // System.out.println("targetPosition " + target.getPosition() + " actorposition " + actor.getPosition());

            MyNode pursueNode = myPathFinder.findNextNode(target.getPosition(), actor.getPosition());
            //NodeDebugger.highLightNodeConnection(myPathFinder.getSourceNode(), box2dBuilder);
            //System.out.println(pursueNode);
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
