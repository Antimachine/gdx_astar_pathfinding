package com.mygdx.game.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Locale;


/**
 * Created by Evgheniy on 5/15/2017.
 */

public class MyNode {
    public final int x;
    public final int y;
    public final int index;

    public Array<Connection<MyNode>> connections = new Array<Connection<MyNode>>();

    private boolean selected;
    private boolean isWall = false;


    public MyNode(int greaterMapMeasurement, int x, int y) {
        this.x = x;
        this.y = y;
        index = x * greaterMapMeasurement + y;
    }

    public Vector2 getPosition(){return new Vector2(x,y);}

    public void select() {
        selected = true;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setIsWall() {
        isWall = true;
        connections.clear();
    }

    public void addNeighbour(MyNode aNode) {
        if (null != aNode) {
            connections.add(new DefaultConnection<>(this, aNode));
            connections.shuffle();
        }


    }


    @Override
    public String toString() {
        return String.format(Locale.US, "Node #%d : x = %d ,y = %d , is Wall = %b", index, x, y, isWall());
    }
}
