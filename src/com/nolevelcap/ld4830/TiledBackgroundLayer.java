package com.nolevelcap.ld4830;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.ImageTest;
import com.nolevelcap.screen.Layer;
import com.nolevelcap.screen.Screen;

public class TiledBackgroundLayer extends Layer {
	
	public TiledBackgroundLayer(Screen Parent, TextureHandler data, Viewport viewport) {
		super(Parent, data, viewport);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTextures(TextureHandler handler) {
		handler.addTexture("TILED", Gdx.files.internal("LINES_TILEABLE.png"));
	}

	@Override
	public void addObjects(TextureHandler handler) {
		for(int x=0; x<VIEWPORT.getScreenWidth(); x+=256) {
			for(int y=0; y<VIEWPORT.getScreenHeight(); y+=256) {
				ImageTest tiled = new ImageTest(handler, handler.getTexture("TILED"), 1.0f, new Rectangle(x, y, 256, 256), false);
				addObject("TILED_OBJECT_"+x+"/"+y, tiled);
			}
		}
	}

	@Override
	public void customLogic() {
		// TODO Auto-generated method stub

	}

}
