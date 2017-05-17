package com.mygdx.game.ai;

/**
 * Created by Evgheniy on 5/16/2017.
 */

public class AStarMap {
    private MyNode[][] nodes;

    private final int width, height;

    public AStarMap(int width, int height) {
        this.width = width;
        this.height = height;
        nodes = new MyNode[height][width];
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

    /**
     * Representation will be rotated to 90 degree clockwise.
     * */
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
