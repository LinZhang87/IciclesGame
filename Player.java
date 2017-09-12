package com.mygdx.game.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by linzhang on 9/11/2017.
 */

public class Player {
    public static final String TAG = Player.class.getName();
    // TODO: Add a position (add constants to Constants.java first)
    Vector2 position;

    // TODO: Add a viewport
    Viewport viewport;

    int deaths;

    // TODO: Add constructor that accepts and sets the viewport, then calls init()
    public Player(Viewport viewport) {
        this.viewport = viewport;
        deaths = 0;
        init();
    }
    // TODO: Add init() function that moves the character to the bottom center of the screen
    public void init() {
        position = new Vector2(viewport.getWorldWidth() / 2, Constants.PLAYER_HEAD_HEIGHT);
    }

    public void update(float delta) {
        // TODO: Use Gdx.input.isKeyPressed() to move the player in the appropriate direction when an arrow key is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= delta * Constants.PLAYER_MOVEMENT_SPEED;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += delta * Constants.PLAYER_MOVEMENT_SPEED;
        }
        // TODO: Compute accelerometer input = raw input / (gravity * sensitivity)
        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.GRAVITATIONAL_ACCELERATION * Constants.ACCELEROMETER_SENSITIVITY);
        // TODO: Use the accelerometer input to move the player
        position.x += -delta * accelerometerInput * Constants.PLAYER_MOVEMENT_SPEED;
        ensureInBounds();
    }

    public void ensureInBounds() {
        // TODO: Complete this function to ensure the player is within the viewport
        if(position.x < Constants.PLAYER_HEAD_RADIUS) {
            position.x = Constants.PLAYER_HEAD_RADIUS;
        }
        if(position.x + Constants.PLAYER_HEAD_RADIUS > viewport.getWorldWidth()) {
            position.x = viewport.getWorldWidth() - Constants.PLAYER_HEAD_RADIUS;
        }
    }

    public boolean hitByIcicle(Icicles icicles) {
        for(int i = 0; i < icicles.icicleList.size; i++) {
            if(position.dst2(icicles.icicleList.get(i).position) < Constants.PLAYER_HEAD_RADIUS) {
                deaths++;
                return true;
            }
        }
        return false;
    }

    // TODO: Create a render function that accepts a ShapeRenderer and does the actual drawing
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.set(ShapeType.Filled);
        //draw head
        shapeRenderer.circle(position.x, position.y, Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_HEAD_SEGMENTS);

        Vector2 torsoTop = new Vector2(position.x, position.y - Constants.PLAYER_HEAD_RADIUS);
        Vector2 torsoBottom = new Vector2(torsoTop.x, torsoTop.y - 2 * Constants.PLAYER_HEAD_RADIUS);
        //draw left arm
        shapeRenderer.rectLine(torsoTop.x, torsoTop.y, torsoTop.x - Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
        //draw right arm
        shapeRenderer.rectLine(torsoTop.x, torsoTop.y, torsoTop.x + Constants.PLAYER_HEAD_RADIUS, torsoTop.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
        //draw left leg
        shapeRenderer.rectLine(torsoBottom.x, torsoBottom.y, torsoBottom.x - Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
        //draw right leg
        shapeRenderer.rectLine(torsoBottom.x, torsoBottom.y, torsoBottom.x + Constants.PLAYER_HEAD_RADIUS, torsoBottom.y - Constants.PLAYER_HEAD_RADIUS, Constants.PLAYER_LIMB_WIDTH);
        //draw torso
        shapeRenderer.rectLine(torsoTop.x, torsoTop.y, torsoBottom.x, torsoBottom.y, Constants.PLAYER_LIMB_WIDTH);

    }
}
