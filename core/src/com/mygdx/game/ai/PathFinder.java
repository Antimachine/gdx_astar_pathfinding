package com.mygdx.game.ai;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;

/**
 * Created by Evgheniy on 5/15/2017.
 */

public class PathFinder {

    private final DefaultIndexGraphPath graphPath;
    private IndexedAStarPathFinder<MyNode> indexedAStarPathFinder;


    public static final int ALLOWED_PATH = 1;
    private final int[][] map;
    private MyNode[][] nodes;

    public PathFinder(int[][] map) {
        this.map = map;


        graphPath = new DefaultIndexGraphPath();
        nodes  = generateNodes();
        nodes =  addNodeConnection(nodes);


        indexedAStarPathFinder = new IndexedAStarPathFinder<MyNode>(graphPath);



    }


    private MyNode[][] generateNodes() {
        MyNode[][] nodes = new MyNode[map.length][map[0].length];

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map.length; y++) {
                if (map[x][y] == ALLOWED_PATH) {
                    nodes[x][y] = new MyNode(x, y);
                    graphPath.add(nodes[x][y]);
                }

            }
        }
        return nodes;
    }


    public void calculatePath() {
        MyNode startNode = nodes[4][0];
        MyNode endNode = nodes[4][4];

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


    private void addNodeNeighbour(MyNode node, int x, int y) {
        if (x >= 0 && x < nodes.length && y >= 0 && y < nodes.length)
            node.addNeighbour(nodes[x][y]);
    }

    private MyNode[][] addNodeConnection(MyNode[][] myNodes) {
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
        return myNodes;
    }
}
