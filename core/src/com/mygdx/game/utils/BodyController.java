package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Evgheniy on 5/18/2017.
 */

public class ActorController {

    public static final float movingSpeed = 2f;
    private final Body actor;

    public ActorController(Body actor) {
        this.actor = actor;

    }

    public void handleActions() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            actor.applyLinearImpulse(new Vector2(-movingSpeed, 0), actor.getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            actor.applyLinearImpulse(new Vector2(movingSpeed, 0), actor.getWorldCenter(), true);

        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            actor.applyLinearImpulse(new Vector2(0, movingSpeed), actor.getWorldCenter(), true);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            actor.applyLinearImpulse(new Vector2(0, -movingSpeed), actor.getWorldCenter(), true);
        } else {
            actor.setLinearVelocity(0, 0);
        }
        if (actor.getLinearVelocity().len2() > movingSpeed * movingSpeed) {
            actor.setLinearVelocity(actor.getLinearVelocity().scl(movingSpeed / actor.getLinearVelocity().len()));
        }


    }
}
