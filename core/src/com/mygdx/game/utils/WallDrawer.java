package com.mygdx.game.utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GameManager;
import com.mygdx.game.ai.AStarMap;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.GameManager.WALL;

/**
 * Created by Evgheniy on 5/19/2017.
 */

public class WallDrawer implements InputProcessor {
    private static final Texture WALL_TILE = new Texture("images/tile.png");

    private final B2Body.Builder bodyBuilder;
    private final Camera camera;
    private final AStarMap map;
    private final World world;
    private List<Sprite> sprites = new ArrayList<>();
    private final NodeDefiner nodeDefiner;

    public WallDrawer(World world, AStarMap map, Camera camera) {
        this.camera = camera;
        this.map = map;
        this.world = world;
        bodyBuilder = new B2Body.Builder(world);
        nodeDefiner = new NodeDefiner();

    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 position = camera.unproject(new Vector3(screenX, screenY, 0));

        int floorX = MathUtils.floor(position.x);
        int floorY = MathUtils.floor(position.y);

        Sprite sprite = new Sprite(WALL_TILE, WALL_TILE.getWidth(), WALL_TILE.getHeight());
        sprite.setPosition(floorX, floorY);



        bodyBuilder.x(floorX + (sprite.getWidth() / GameManager.PPM / 2))
                .y(floorY + (sprite.getWidth() / GameManager.PPM / 2))
                .categoryBits(WALL)
                .bodyType(BodyDef.BodyType.StaticBody)
                .width(sprite.getWidth() / GameManager.PPM / 2)
                .height(sprite.getHeight() / GameManager.PPM / 2)
                .build().generateBody();






        sprites.add(sprite);

        nodeDefiner.defineWall(world, map);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void render(SpriteBatch spriteBatch) {
        for (Sprite sprite : sprites)
            spriteBatch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getWidth() / GameManager.PPM, sprite.getHeight() / GameManager.PPM);

    }
}
