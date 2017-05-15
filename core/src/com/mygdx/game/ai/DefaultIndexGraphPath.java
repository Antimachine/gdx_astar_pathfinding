package com.mygdx.game.ai;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Evgheniy on 5/15/2017.
 */

public class DefaultIndexGraphPath extends DefaultGraphPath<MyNode> implements IndexedGraph<MyNode> {


    @Override
    public int getIndex(MyNode node) {
        return node.index;
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    @Override
    public Array<Connection<MyNode>> getConnections(MyNode fromNode) {
        return fromNode.connections;
    }

}
