package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ai.MyNode;
import com.mygdx.game.ai.MyPathFinder;

/**
 * Created by Evgheniy on 5/19/2017.
 */

public class PathPrinter {

    private static final float RADIUS = .2f;
    private MyPathFinder pathFinder;

    public PathPrinter(MyPathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    public void highlightPath(ShapeRenderer shapeRenderer, Vector2 from, Vector2 to) {
        MyNode pursueNode = pathFinder.findNextNode(from, to);
        if (pursueNode == null)
            return;

        Vector2 pursueVector = new Vector2(pursueNode.x, pursueNode.y);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.line(from, pursueVector);
        shapeRenderer.circle(pursueVector.x, pursueVector.y, RADIUS);


        highlightPath(shapeRenderer, pursueVector, to);
    }


}
