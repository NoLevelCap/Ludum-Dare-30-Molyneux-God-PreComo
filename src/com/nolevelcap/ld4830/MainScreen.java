package com.nolevelcap.ld4830;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.screen.Screen;

public class MainScreen extends Screen {
	
	private WorldsLayer worldLayer;
	private UILayer uiLayer;
	private TiledBackgroundLayer tlLayer;

	public MainScreen(TextureHandler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void addTextures(TextureHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addObjects(TextureHandler handler) {
		
		
		worldLayer = new WorldsLayer(this, handler, new FitViewport(1280, 720));
		addObject(worldLayer);
		
		uiLayer = new UILayer(this, handler, new FitViewport(1280, 720));
		addObject(uiLayer);
		
		tlLayer = new TiledBackgroundLayer(this, handler, new FitViewport(1280, 720));
		addObject(tlLayer);
	}

	@Override
	public void customLogic() {
	}

}
