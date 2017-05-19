package com.mygdx.game.utils;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.ai.AStarMap;
import com.mygdx.game.ai.MyNode;

/**
 * Created by Evgheniy on 5/18/2017.
 */

public class NodeDebugger {



    public static void highLightAllConnections(MyNode[][] nodes, B2Body.Builder builder) {
        for (int y = 0; y < nodes.length; y++) {
            MyNode[] n = nodes[y];
            for (int x = 0; x < n.length; x++) {
                builder
                        .height(1)
                        .width(1)
                        .x(n[x].x)
                        .y(n[x].y)
                        .bodyType(BodyDef.BodyType.StaticBody)
                        .build().generateBody();
            }

        }
    }

    public static void highLightNodeConnection(MyNode node, B2Body.Builder builder) {
        for (Connection<MyNode> connection : node.connections) {
            MyNode n = connection.getToNode();
            builder
                    .height(1)
                    .width(1)
                    .x(n.x)
                    .y(n.y)
                    .bodyType(BodyDef.BodyType.StaticBody)
                    .build().generateBody();

        }

    }

}
