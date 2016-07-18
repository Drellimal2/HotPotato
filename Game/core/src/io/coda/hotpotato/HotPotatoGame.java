package io.coda.hotpotato;

import com.badlogic.gdx.Game;

public class HotPotatoGame extends Game {


	@Override
	public void create() {
//		setScreen(new GameScreen());
		setScreen(new HomeScreen(this));
	}

	public void showHome(){
		setScreen(new HomeScreen(this));
	}

	public void showGame(){
		setScreen(new GameScreen(this));
	}

}
