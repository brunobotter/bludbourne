package br.com.blud;

import br.com.blud.screen.MainGameScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class BludBourne extends Game {

	public static final MainGameScreen _mainGameScreen = new MainGameScreen();

	@Override
	public void create() {
		setScreen(_mainGameScreen);
	}

	@Override
	public void dispose() {
		_mainGameScreen.dispose();
	}
}
