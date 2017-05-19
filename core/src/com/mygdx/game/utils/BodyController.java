package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Evgheniy on 5/18/2017.
 */

public class BodyController {

    public static final float movingSpeed = 2f;
    private final Body actor;
    private final Body target;

    public BodyController(Body actor, Body target) {
        this.actor = actor;
        this.target = target;

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


    public void handleTarget() {
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            target.applyLinearImpulse(new Vector2(1, 0), target.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.A))
            target.applyLinearImpulse(new Vector2(-1, 0), target.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.W))
            target.applyLinearImpulse(new Vector2(0, 1), target.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.S))
            target.applyLinearImpulse(new Vector2(0, -1), target.getWorldCenter(), true);
        else
            target.setLinearVelocity(0, 0);


        if (target.getLinearVelocity().len2() > 1) {
            target.setLinearVelocity(target.getLinearVelocity().scl(1));
        }
    }


}
