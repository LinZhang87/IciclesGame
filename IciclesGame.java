package com.mygdx.game.icicles;

import com.badlogic.gdx.Game;
import com.mygdx.game.icicles.Constants.Difficulty;

public class IciclesGame extends Game {

	@Override
	public void create () {
		//set IciclesGame's screen to an instance of IciclesScreen
		showDifficultyScreen();
    }
    public void showDifficultyScreen() {
		setScreen(new DifficultyScreen(this));
	}
    public void showIciclesScreen(Difficulty difficulty) {
		setScreen(new IciclesScreen(this, difficulty));
	}
}
