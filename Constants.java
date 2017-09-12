package com.mygdx.game.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by linzhang on 9/11/2017.
 */

public class Constants {
    //world size
    public static final float WORLD_SIZE = 10.0f;

    //background color of the world
    public static final Color BACKGROUND_COLOR = Color.BLUE;

    //height of the icicle
    public static final float ICICLES_HEIGHT = 1.0f;

    //width of the icicle
    public static final float ICICLES_WIDTH = 0.5f;

    //color of the icicles
    public static final Color ICICLE_COLOR = Color.WHITE;

    // TODO: Add constant for player head radius
    public static final float PLAYER_HEAD_RADIUS = 0.5f;

    // TODO: Add constant for player head height
    public static final float PLAYER_HEAD_HEIGHT = 4.0f * PLAYER_HEAD_RADIUS;

    // TODO: Add constant for player limb width
    public static final float PLAYER_LIMB_WIDTH = 0.1f;

    // TODO: Add constant for circle segments for the player's head
    public static final int PLAYER_HEAD_SEGMENTS = 20;

    // TODO: Add constant for the player's color
    public static final Color PLAYER_COLOR = Color.BLACK;

    // TODO: Add Constant for player movement speed
    public static final float PLAYER_MOVEMENT_SPEED = 10.0f;

    // TODO: Add constant for Accelerometer sensitivity
    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;

    // TODO: Add constant for acceleration due to gravity (9.8)
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    // TODO: Add constant for icicle acceleration
    public static final Vector2 ICICLES_ACCELERATION = new Vector2(0, -5.0f);

    // TODO: Add constant for icicle spawns per second
    public static final float ICICLE_SPAWNS_PER_SECOND = 10.0f;

    // TODO: Add screen reference size for scaling the HUD (480 works well)
    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 480.0f;

    // TODO: Add constant for the margin between the HUD and screen edge
    public static final float HUD_MARGIN = 20.0f;

    public static final String EASY_LABEL = "Easy";
    public static final String MEDIUM_LABEL = "Medium";
    public static final String HARD_LABEL = "Hard";

    public static final float EASY_SPAWNS_PER_SECOND = 5;
    public static final float MEDIUM_SPAWNS_PER_SECOND = 15;
    public static final float HARD_SPAWNS_PER_SECOND = 25;

    // TODO: Add constants for the color of each difficulty select circle
    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);

    // TODO: Add constant for the size of the difficulty world
    public static final float DIFFICULTY_WORLD_SIZE = 480.0f;

    // TODO: Add constant for the radius of the difficulty select "buttons"
    public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;

    // TODO: Add constant for the scale of the difficulty labels (1.5 works well)
    public static final float DIFFICULTY_LABEL_SCALE = 1.5f;

    // TODO: Add Vector2 constants for the centers of the difficulty select buttons
    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);

    public enum Difficulty {
        EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
        HARD(HARD_SPAWNS_PER_SECOND, HARD_LABEL);

        float spawnRate;
        String label;

        Difficulty(float spawnRate, String label) {
            this.spawnRate = spawnRate;
            this.label = label;
        }
    }

}
