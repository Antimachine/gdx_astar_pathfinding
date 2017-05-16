package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public class CameraOperator {
    private final Camera camera;
    private float CAMERA_SHIFT = 1;

    public CameraOperator(Camera camera) {
        this.camera = camera;
    }

    public void handleMovings() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            camera.position.x -= CAMERA_SHIFT;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            camera.position.x += CAMERA_SHIFT;
        else if (Gdx.input.isKeyPressed(Input.Keys.UP))
            camera.position.y += CAMERA_SHIFT;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            camera.position.y -= CAMERA_SHIFT;
        camera.update();
    }

}
