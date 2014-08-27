package com.nolevelcap.ld4830.mainmenu;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.ld4830.MainGame;
import com.nolevelcap.screen.Screen;

public class MainMenu extends Screen {
	private MainGame parent;

	public MainMenu(MainGame parent, TextureHandler handler) {
		super(handler);
		this.parent = parent;
	}

	@Override
	public void addTextures(TextureHandler handler) {
	}

	@Override
	public void addObjects(TextureHandler handler) {
		BackgroundLayer back = new BackgroundLayer(this, handler, new FitViewport(1280, 720));
		addObject(back);
	}

	@Override
	public void customLogic() {
		// TODO Auto-generated method stub
		
	}
	
	public void changeScreen(Screen switchTo) {
		parent.switchScreen(switchTo);
	}

}
