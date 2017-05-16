package com.mygdx.game.ai;

import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public class AStarMap {
    private MyNode[][] nodes;

    private final int width, height;

    public AStarMap(int width, int height) {
        this.width = width;
        this.height = height;
        generateEmptyNodes(nodes, width, height);

    }

    private void generateEmptyNodes(MyNode[][] nodes, int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                nodes[y][x] = new MyNode(x, y);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MyNode getNodeAt(int x, int y) {
        return nodes[x][y];
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                stringBuilder.append(nodes[y][x].isWall() ? "#" : " ");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }



}
