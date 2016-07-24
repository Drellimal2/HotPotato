package io.coda.hotpotatoretro;

import com.badlogic.gdx.Game;

public class HotPotatoRetroGame extends Game {

	@Override
	public void create() {
//		setScreen(new GameScreen());
		setScreen(new HomeScreen(this));
	}

	public void showHome(){
		setScreen(new HomeScreen(this));
	}

	public void showGame(){
		setScreen(new RetroGameScreen(this));
	}



}
