package com.mygdx.game.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.Locale;


/**
 * Created by Evgheniy on 5/15/2017.
 */

public class MyNode {
    public final int x;
    public final int y;
//    public final int width;
//    public final int height;


    public final static int TILE_SIZE = 50;
    private final static int SPACE_BETWEEN_TILES = 2;
    private static int counter = 0;


    public final int index = counter++;


    public Array<Connection<MyNode>> connections = new Array<Connection<MyNode>>();
    private boolean selected;

    public MyNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void select() {
        selected = true;
    }


    public void render(ShapeRenderer shapeRenderer) {

        if (selected)
            shapeRenderer.setColor(Color.GREEN);
        else
            shapeRenderer.setColor(Color.RED);

        shapeRenderer.line(x, y, x, y + TILE_SIZE);
        shapeRenderer.line(x + TILE_SIZE, y, x, y + TILE_SIZE);
        shapeRenderer.line(x, y + TILE_SIZE, x + TILE_SIZE, y + TILE_SIZE);
        shapeRenderer.line(x, y, x + TILE_SIZE, y + TILE_SIZE);
    }

    public void addNeighbour(MyNode aNode) {
        if (null != aNode) {
            connections.add(new DefaultConnection<MyNode>(this, aNode));
        }
    }


    @Override
    public String toString() {
        return String.format(Locale.US, "Node #%d : x = %d ,y = %d", index, x, y);
    }
}
