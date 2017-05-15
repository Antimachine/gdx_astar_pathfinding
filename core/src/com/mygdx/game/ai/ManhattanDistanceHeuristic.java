package com.mygdx.game.ai;

import com.badlogic.gdx.ai.pfa.Heuristic;

/**
 * Created by Evgheniy on 5/15/2017.
 */

public class ManhattanDistanceHeuristic implements Heuristic<MyNode> {

    @Override
    public float estimate(MyNode node, MyNode endNode) {
        return Math.abs(endNode.x - node.x) + Math.abs(endNode.y - node.y);
    }
}
