package com.mygdx.game.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Evgheniy on 5/18/2017.
 */

public class Graph implements IndexedGraph<MyNode> {
    private final int nodeCount;

    public Graph(AStarMap map) {
        nodeCount = map.getHeight() * map.getHeight();
    }

    @Override
    public int getIndex(MyNode node) {
        return node.index;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public Array<Connection<MyNode>> getConnections(MyNode fromNode) {
        return fromNode.connections;

    }
}
