package com.mygdx.game.ai.steering;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Evgheniy on 5/22/2017.
 */

public class Box2dLocation implements Location<Vector2> {
    private Vector2 position;
    private float orientation = 0;

    public Box2dLocation(Vector2 vector2) {
        position = vector2;
    }

    public Box2dLocation() {
        this(new Vector2());
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public float getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return SteeringUtils.vectorToAngle(vector);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        return SteeringUtils.angleToVector(outVector, angle);
    }

    @Override
    public Location<Vector2> newLocation() {
        return new Box2dLocation();
    }
}
