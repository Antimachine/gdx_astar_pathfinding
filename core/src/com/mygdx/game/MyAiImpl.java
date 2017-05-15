package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.ai.DefaultIndexGraphPath;
import com.mygdx.game.ai.ManhattanDistanceHeuristic;
import com.mygdx.game.ai.MyNode;

public class MyAiImpl extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private IndexedAStarPathFinder<MyNode> indexedAStarPathFinder;


    private MyNode[][] myNodes = new MyNode[5][5];

    int[][] map = {
            {1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 1, 1, 1}
    };
    private DefaultIndexGraphPath graphPath;


    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        graphPath = new DefaultIndexGraphPath();
        myNodes = generateNodes();
        addNodeConnection(myNodes);

        indexedAStarPathFinder = new IndexedAStarPathFinder<MyNode>(graphPath);


        calculatePath();

    }

    private void addNodeConnection(MyNode[][] myNodes) {
        for (int x = 0; x < myNodes.length; x++) {
            for (int y = 0; y < myNodes[0].length; y++) {
                if (null != myNodes[x][y]) {
                    addNodeNeighbour(myNodes[x][y], x - 1, y); // Node to left
                    addNodeNeighbour(myNodes[x][y], x + 1, y); // Node to right
                    addNodeNeighbour(myNodes[x][y], x, y - 1); // Node below
                    addNodeNeighbour(myNodes[x][y], x, y + 1); // Node above
                }
            }
        }
    }

    private void addNodeNeighbour(MyNode node, int x, int y) {
        if (x >= 0 && x < myNodes.length && y >= 0 && y < myNodes.length)
            node.addNeighbour(myNodes[x][y]);
    }

    private MyNode[][] generateNodes() {
        MyNode[][] nodes = new MyNode[5][5];
        System.out.println("map length " + map.length);
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (map[x][y] == 1) {
                    nodes[x][y] = new MyNode(x, y);
                    graphPath.add(nodes[x][y]);
                }

            }
        }
        return nodes;
    }

    private void calculatePath() {
        MyNode startNode = myNodes[4][0];
        MyNode endNode = myNodes[4][4];

        graphPath.clear();

        System.out.println("StarNode " + startNode);
        System.out.println("EndNode " + endNode);
        indexedAStarPathFinder.searchNodePath(startNode, endNode, new ManhattanDistanceHeuristic(), graphPath);

        if (graphPath.nodes.size == 0) {
            System.out.println("Path not Found");
        } else {
            System.out.println("Path founded");

            for (MyNode node : graphPath.nodes) {
                //node.select();
                System.out.println(node);
            }
        }


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        shapeRenderer.begin(S);
    }

    @Override
    public void dispose() {

    }


}
