package com.mygdx.game.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.icicles.Constants.Difficulty;

/**
 * Created by linzhang on 9/11/2017.
 */

public class IciclesScreen extends InputAdapter implements  Screen{

    public static final String TAG = IciclesScreen.class.getName();

    private ExtendViewport viewPort;
    private ScreenViewport hudViewport;

    private SpriteBatch batch;
    private BitmapFont font;

    private ShapeRenderer shapeRenderer;
    Icicle icicle;

    Difficulty difficulty;

    IciclesGame game;

    // TODO: Add a Player (complete Player.java first)
    Player player;

    // TODO: Add an instance of Icicles
    Icicles icicles;

    int topScore;

    public IciclesScreen(IciclesGame game, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.game = game;
    }

    @Override
    public void show() {
        //init the extend view port using the world size constant
        viewPort = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        Gdx.input.setInputProcessor(this);

        //init the shape render
        shapeRenderer = new ShapeRenderer();
        //set auto shape type to true so that other objects who receive this shape render can
        //set a shape type
        shapeRenderer.setAutoShapeType(true);

        hudViewport = new ScreenViewport();
        batch = new SpriteBatch();
        font = new BitmapFont();

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // TODO: Initialize the player
        player = new Player(viewPort);
        icicle = new Icicle(new Vector2(Constants.WORLD_SIZE / 2, Constants.WORLD_SIZE / 2));

        // TODO: Initialize icicles
        icicles = new Icicles(viewPort, difficulty);

        topScore = 0;
    }

    @Override
    public void resize(int width, int height) {
        //update the extend view port
        viewPort.update(width, height, true);
        hudViewport.update(width, height, true);

        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);

        // TODO: Reset the player (using init())
        player.init();

        // TODO: Reset icicles
        icicles.init();
    }

    @Override
    public void dispose() {

    }


    @Override
    public void render(float delta) {
        // TODO: Call update() on player
        player.update(delta);

        // TODO: Update Icicles
        icicles.update(delta);

        // TODO: Check if the player was hit by an icicle. If so, reset the icicles.
        if (player.hitByIcicle(icicles)) {
            icicles.init();
        }

        //apply the viewport
        viewPort.apply(true);

        //clear the screen to the background color of blue
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //set shapeRender's projection matrix
        shapeRenderer.setProjectionMatrix(viewPort.getCamera().combined);

        //draw the icicle
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        icicle.render(shapeRenderer);

        // TODO: Call render() on the player
        player.render(shapeRenderer);
        // TODO: Render Icicles
        icicles.render(shapeRenderer);
        shapeRenderer.end();

        topScore = Math.max(topScore, icicles.iciclesDodged);

        hudViewport.apply(true);
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        batch.begin();
        font.draw(batch, "Deaths: " + player.deaths + "\nDifficulty: " + difficulty.label, Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN);
        font.draw(batch, "Score: " + icicles.iciclesDodged + "\nTop Score: " + topScore,
                    hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN, 0, Align.right, false);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        //dispose of the shape render
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }

    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        game.showDifficultyScreen();
        return true;
    }
}
