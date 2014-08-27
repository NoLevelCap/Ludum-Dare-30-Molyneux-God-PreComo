package com.nolevelcap.ld4830.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.ImageTest;
import com.nolevelcap.drawable.Text;
import com.nolevelcap.ld4830.MainScreen;
import com.nolevelcap.ld4830.drawables.TextButton;
import com.nolevelcap.screen.Layer;

public class BackgroundLayer extends Layer {
	
	private ImageTest world;
	private long lastTime,  Interval = 100;
	private ImageTest text;
	private MainMenu MAINMENUPARENT;

	public BackgroundLayer(MainMenu Parent, TextureHandler data, Viewport viewport) {
		super(Parent, data, viewport);
		this.MAINMENUPARENT = Parent;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTextures(TextureHandler handler) {
		handler.addTexture("MAIN_MENU_BACKGROUND", Gdx.files.internal("MainMenu/background.png"));
		handler.addTexture("WORLD_LOGO", Gdx.files.internal("MainMenu/world.png"));
		handler.addTexture("WORLD_TEXT", Gdx.files.internal("MainMenu/text.png"));
		handler.addTexture("Grey", Gdx.files.internal("Grey.png"));
		handler.addTexture("Black", Gdx.files.internal("Black.png"));
		handler.addTexture("WORLD_BUTTON", Gdx.files.internal("MainMenu/ButtonBoundry.png"));
		handler.addFont("Helvetica", Gdx.files.internal("Fonts/Helvetica_32.fnt"));
	}

	@Override
	public void addObjects(TextureHandler handler) {
		TextButton button = new TextButton(handler, "WORLD_BUTTON", "PLAY", new Rectangle(900, 465, 180, 36)) {
			
			@Override
			public void buttonEffect() {
				MAINMENUPARENT.changeScreen(new MainScreen(DATA));
			}
		};
		
		addObject("WORLD_BUTTON_PLAY", button);
		
		TextButton button_instructionsButton = new TextButton(handler, "WORLD_BUTTON", "HELP", new Rectangle(900, 450-36, 180, 36)) {
			
			@Override
			public void buttonEffect() {
				// TODO Auto-generated method stub
				
			}
		};
		
		//addObject("WORLD_BUTTON_INSTRUCTIONS", button_instructions);
		
		TextButton button_trello = new TextButton(handler, "WORLD_BUTTON", "TRELLO", new Rectangle(900, 435-36*2, 180, 36)) {
			
			@Override
			public void buttonEffect() {
				Gdx.net.openURI("https://trello.com/b/xaE7xXEL/molyneux-god");
			}
		};
		
		addObject("WORLD_BUTTON_TRELLO", button_trello);
		
		
		TextButton button_ludumdare = new TextButton(handler, "WORLD_BUTTON", "LD 30", new Rectangle(900, 420-36*3, 180, 36)) {
			
			@Override
			public void buttonEffect() {
				Gdx.net.openURI("http://www.ludumdare.com/compo/ludum-dare-30/?action=preview&uid=28209");
			}
		};
		
		addObject("WORLD_BUTTON_LD30", button_ludumdare);
		
		TextButton button_exit = new TextButton(handler, "WORLD_BUTTON", "EXIT", new Rectangle(900, 405-36*4, 180, 36)) {
			
			@Override
			public void buttonEffect() {
				Gdx.app.exit();
			}
		};
		
		addObject("WORLD_BUTTON_EXIT", button_exit);
		
		text = new ImageTest(handler, handler.getTexture("WORLD_TEXT"), 0.0f, new Rectangle(0, 0, 742, 720), false);
		addObject("WORLD_TEXT", text);
		
		world = new ImageTest(handler, handler.getTexture("WORLD_LOGO"), 0.0f, new Rectangle(0, 0, 742, 720), false);
		addObject("WORLD_LOGO", world);
		
		Text text = new Text(handler, "Made for LUDUM DARE 30; Version #PC001", new Vector2(0, 720), false);
		addObject("Version_Text", text);
		
		ImageTest background = new ImageTest(handler, handler.getTexture("MAIN_MENU_BACKGROUND"), 1.0f, new Rectangle(0, 0, 1280, 720), false);
		addObject("BACKGROUND", background);
		
	}

	@Override
	public void customLogic() {
		if(System.currentTimeMillis()-lastTime>=Interval && world.getOpacity() < 1.0f) {
			lastTime = System.currentTimeMillis();
			world.increaseOpacity(0.05f);
		} else if(world.getOpacity() >= 1.0f && System.currentTimeMillis()-lastTime>=Interval) {
			lastTime = System.currentTimeMillis();
			world.setOpacity(1.0f);
			if(text.getOpacity()<1.0f) {
				text.increaseOpacity(0.05f);
			} else {
				text.setOpacity(1.0f);
			}
		}
	}

}
