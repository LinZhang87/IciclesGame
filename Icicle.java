package com.mygdx.game.icicles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.reflect.Constructor;

/**
 * Created by linzhang on 9/11/2017.
 */

public class Icicle {
    public static final String TAG = Icicle.class.getName();

    //Vector2 position that represents the x,y coordinates of bottom point of icicle
    Vector2 position;

    //Vector2 for velocity
    Vector2 velocity;

    public Icicle(Vector2 position) {
        this.position = position;
        // TODO: Initialize velocity
        this.velocity = new Vector2();
    }

    public void update(float delta) {
        // TODO: Update velocity using icicle accelration constant
        velocity.mulAdd(Constants.ICICLES_ACCELERATION, delta);

        // TODO: Update position using velocity
        position.mulAdd(velocity, delta);
    }
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Constants.ICICLE_COLOR);

        renderer.set(ShapeType.Filled);

        //draw the icicle using the size constants
        renderer.triangle(position.x, position.y,
                            position.x - Constants.ICICLES_WIDTH / 2, position.y + Constants.ICICLES_HEIGHT,
                            position.x + Constants.ICICLES_WIDTH / 2, position.y + Constants.ICICLES_HEIGHT);
    }
}
