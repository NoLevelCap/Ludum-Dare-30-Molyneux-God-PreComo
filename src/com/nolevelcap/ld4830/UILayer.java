package com.nolevelcap.ld4830;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.drawable.Text;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.WorldsLayer.ResNames;
import com.nolevelcap.ld4830.drawables.Button;
import com.nolevelcap.ld4830.drawables.Display;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.screen.Layer;
import com.nolevelcap.screen.Screen;

public class UILayer extends Layer {
	
	private static int TOOLS_SHIFT = 160;
	public UILayer(Screen Parent, TextureHandler data, Viewport viewport) {
		super(Parent, data, viewport);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTextures(TextureHandler handler) {
		handler.addTexture("OPTIONS", Gdx.files.internal("optios.png"));
		handler.addTexture("OPTIONS_L", "OPTIONS", new Rectangle(0, 0, 16, 16));
		handler.addTexture("OPTIONS_C", "OPTIONS", new Rectangle(16, 0, 16, 16));
		handler.addTexture("OPTIONS_R", "OPTIONS", new Rectangle(32, 0, 16, 16));
		
		
		handler.addTexture("Grey", Gdx.files.internal("Grey.png"));
		
		handler.addTexture("ICONS", Gdx.files.internal("ICONS.png"));
		int amount=0;
		
		for(int y=0; y<handler.getTexture("ICONS").getRegionHeight(); y+=12) {
			for(int x=0; x<handler.getTexture("ICONS").getRegionWidth(); x+=12) {
				handler.addTexture("ICON_"+amount, "ICONS", new Rectangle(x, y, 12, 12));
				Gdx.app.log("INFO", "ICON_"+amount);
				amount++;
			}
		}
		
		handler.addFont("BASIC_ARIAL");
	}

	@Override
	public void addObjects(TextureHandler handler) {
		
		Text toolTip = new Text(DATA, "", new Vector2(0, 0), false);
		toolTip.Render = false;
		toolTip.setColor(Color.CYAN);
		addObject("TOOLTIP", toolTip);
		
		int amount = 0;
		for(int dx=8; dx+96<512; dx+=100) {
			Display display = new Display(handler, "DISPLAY", "ICON_"+amount, ResNames.values()[amount], new Rectangle(dx+50, 500, 96, 48), (World) ScreenElements.get("EARTH_WORLD_DISPLAY"));
			addObject("DISPLAY_"+dx, display);
			amount++;
		}
		
		amount = 0;
		for(int dx=8; dx+96<512; dx+=100) {
			Display display = new Display(handler, "DISPLAY", "ICON_"+amount, ResNames.values()[amount], new Rectangle(dx+718, 500, 96, 48), (World) ScreenElements.get("PANDORA_WORLD_DISPLAY"));
			addObject("DISPLAY_2_"+dx, display);
			amount++;
		}
		
		
		amount = 5;
		Button Options_L = new Button(DATA, "OPTIONS_L", "ICON_"+amount, EFFECTS.values()[amount-5],new Rectangle(TOOLS_SHIFT+0, 0, 48, 48), (World) ScreenElements.get("EARTH_WORLD_DISPLAY"));
		addObject("OPTIONS_0", Options_L);
		amount++;
		
		for(int sx=48; sx<48*19; sx+=48) {
		Gdx.app.log("AMOUNT", "OPTIONS"+(sx/48)+"/?");
		Button Options = new Button(DATA, "OPTIONS_C", "ICON_"+amount, EFFECTS.values()[amount-5], new Rectangle(TOOLS_SHIFT+sx, 0, 48, 48), (World) ScreenElements.get("EARTH_WORLD_DISPLAY"));
		addObject("OPTIONS_"+(sx/48), Options);
		amount++;
		}
		
		Button Options_R = new Button(DATA, "OPTIONS_R", "ICON_"+amount, EFFECTS.values()[amount-5], new Rectangle(TOOLS_SHIFT+48*19, 0, 48, 48), (World) ScreenElements.get("EARTH_WORLD_DISPLAY"));
		addObject("OPTIONS_19", Options_R);
		amount++;
		
		
	}

	@Override
	public void customLogic() {
		String s = ((Text) ScreenElements.get("TOOLTIP")).getInput();
		((Drawable)ScreenElements.get("TOOLTIP")).IMAGEBOUNDS.setPosition(OnScreenMouseCoords.x-DATA.getFont("BASIC_ARIAL").getBounds(s).width, OnScreenMouseCoords.y+DATA.getFont("BASIC_ARIAL").getBounds(s).height);
	}

}
