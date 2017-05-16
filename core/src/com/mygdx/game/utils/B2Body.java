package com.mygdx.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public class B2Body {
    private float width, height;
    private float x, y;
    private short maskBits, categoryBits;
    private World world;

    private Rectangle rectangle;
    private BodyDef.BodyType bodyType;


    public static class Builder {
        private float width, height;
        private float x, y;
        private short maskBits, categoryBits;
        private World world;

        private Rectangle rectangle;
        private BodyDef.BodyType bodyType;


        public Builder(World world) {
            this.world = world;
        }

        public Builder rectangle(Rectangle rectangle) {
            this.rectangle = rectangle;
            height(rectangle.getWidth() / 2);
            width(rectangle.getHeight() / 2);
            x(rectangle.x + rectangle.width / 2);
            y(rectangle.y + rectangle.height / 2);
            return this;
        }

        public Builder width(float width) {
            this.width = width;
            return this;
        }

        public Builder height(float height) {
            this.height = height;
            return this;
        }

        public Builder x(float x) {
            this.x = x;
            return this;
        }

        public Builder y(float y) {
            this.y = y;
            return this;
        }

        public Builder categoryBits(short categoryBits) {
            this.categoryBits = categoryBits;
            return this;
        }

        public Builder maskBits(short maskBits) {
            this.maskBits = maskBits;
            return this;
        }

        public Builder bodyType(BodyDef.BodyType bodyType) {
            this.bodyType = bodyType;
            return this;
        }

        public B2Body build() {
            return new B2Body(this);
        }

    }

    private B2Body(Builder builder) {
        x = builder.x;
        y = builder.y;
        width = builder.width;
        height = builder.height;
        world = builder.world;
        maskBits = builder.maskBits;
        categoryBits = builder.categoryBits;
        rectangle = builder.rectangle;
        bodyType = builder.bodyType;
    }


    public Body generateBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width, height);


        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.filter.categoryBits = categoryBits;
        fixtureDef.filter.maskBits = maskBits;

        body.createFixture(fixtureDef);
        polygonShape.dispose();
        return body;
    }

}
