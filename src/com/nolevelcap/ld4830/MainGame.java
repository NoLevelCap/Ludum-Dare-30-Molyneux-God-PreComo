package com.nolevelcap.ld4830;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.ld4830.mainmenu.MainMenu;
import com.nolevelcap.screen.Screen;
/*
 * Things that need to be done:
 * 
 */

public class MainGame extends ApplicationAdapter {
	
	SpriteBatch batch;
	private MainMenu MainMenu;
	private TextureHandler handler;
	public Screen mainScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		handler = new TextureHandler();
		MainMenu = new MainMenu(this, handler);
		mainScreen = MainMenu;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainScreen.render();
		
		mainScreen.logic();
		
		if(Gdx.input.isKeyJustPressed(Keys.R) && Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			mainScreen.REFRESH_OBJECTS();
		}
	}

	@Override
	public void resize(int width, int height) {
		mainScreen.resize(width, height);
		super.resize(width, height);
	}
	
	public void switchScreen(Screen switchTo) {
		mainScreen = switchTo;
	}
}
