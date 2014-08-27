package com.nolevelcap.ld4830;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.ld4830.drawables.GodDisplay;
import com.nolevelcap.ld4830.drawables.ObjectiveDisplay;
import com.nolevelcap.ld4830.drawables.PixmapDisplay;
import com.nolevelcap.ld4830.drawables.TimeButton;
import com.nolevelcap.ld4830.drawables.TimeDisplay;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.ld4830.effects.Effect;
import com.nolevelcap.ld4830.effects.EncouragePopGrowth;
import com.nolevelcap.ld4830.effects.ErodeEffect;
import com.nolevelcap.ld4830.effects.FreezeEffect;
import com.nolevelcap.ld4830.effects.MeteorStrike;
import com.nolevelcap.ld4830.effects.NoEffect;
import com.nolevelcap.ld4830.effects.SpawnVolcano;
import com.nolevelcap.ld4830.effects.VirusEffect;
import com.nolevelcap.screen.Layer;
import com.nolevelcap.screen.Screen;

public class WorldsLayer extends Layer {
	
	public enum EFFECTS {
		Meteor("Spawn Meteor", true, 100), Volcano("Spawn Volcano", true, 50), Earthquake("Spawn Earthquake", false, 1000), Rain("Spawn Rain", false, 1000), Sunshine("Spawn Sunshine", false, 1000),
		Radiation("Spawn Radiation", false, 1000), Aliens("Spawn Aliens", false, 1000), Encourage_War("Encourage War", false, 1000), Encourage_Peace("Encourage Peace", false, 1000), Minerals("Spawn Minerals", false, 1000),
		Grow("Make Grow", false, 1000), Woohoo("Encourage Woohoo", true, 10), Migration("Encourage Migration", false, 1000), Discovery("Encourage Discovery", false, 1000), Virus("Spawn Pandemic", true, 10),
		XCom("Spawn XCom Units", false, 1000), Sabotage("Encourage Sabotage", false, 1000), Locus_Plague("Spawn Locus Plague", false, 1000), Freeze("Make Freeze", true, 15), Erosion("Erosion", true, 5);
		
		public String Tooltip;
		public boolean Available;
		public int cost;
		
		private EFFECTS(String name, boolean Available, int cost) {
			this.Available =Available; 
			this.Tooltip = name;
			this.cost = cost;
		}
		
		public Effect getAttributedEffect() {
			switch(this) {
			case Aliens:
				return new NoEffect(this);
			case Discovery:
				return new NoEffect(this);
			case Earthquake:
				return new NoEffect(this);
			case Encourage_Peace:
				return new NoEffect(this);
			case Encourage_War:
				return new NoEffect(this);
			case Erosion:
				return new ErodeEffect(this);
			case Freeze:
				return new FreezeEffect(this);
			case Grow:
				return new NoEffect(this);
			case Locus_Plague:
				return new NoEffect(this);
			case Meteor:
				return new MeteorStrike(this);
			case Migration:
				return new NoEffect(this);
			case Minerals:
				return new NoEffect(this);
			case Radiation:
				return new NoEffect(this);
			case Rain:
				return new NoEffect(this);
			case Sabotage:
				return new NoEffect(this);
			case Sunshine:
				return new NoEffect(this);
			case Virus:
				return new VirusEffect(this);
			case Volcano:
				return new SpawnVolcano(this);
			case Woohoo:
				return new EncouragePopGrowth(this);
			case XCom:
				return new NoEffect(this);
			default:
				return new NoEffect(this);
			}
		}
	};
	
	public enum ResNames {
		Population("Population"),
		LandMass("Land Mass"),
		Resources("Resources"),
		Goldilocks("World Health"),
		Temperature("Temperature");
		
		public String Tooltip;
		
		private ResNames(String name) {
			this.Tooltip = name;
		}
	}
	
	public long lastTime;
	private World pandora, earth;
	private ObjectiveDisplay objective;
	private Effect selectedEffect;
	public int INTERVAL;
	
	public WorldsLayer(Screen Parent, TextureHandler data, Viewport viewport) {
		super(Parent, data, viewport);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		selectedEffect = new NoEffect(EFFECTS.Meteor);
		addCustomObject("SEffect", selectedEffect);
	}

	@Override
	public void addTextures(TextureHandler handler) {
		handler.addTexture("EARTH", Gdx.files.internal("erth.png"));
		handler.addTexture("PANDORA", Gdx.files.internal("pandroa.png"));
		handler.addTexture("MOUSE", Gdx.files.internal("mouse.png"));
		handler.addTexture("TimeButton", Gdx.files.internal("time_buttons.png"));
		handler.addTexture("TIMEICONS", Gdx.files.internal("time_icons.png"));
		handler.addTexture("TIMEICON_PLAY", "TIMEICONS", new Rectangle(0, 0, 12, 12));
		handler.addTexture("TIMEICON_PAUSE", "TIMEICONS", new Rectangle(12, 0, 12, 12));
		handler.addTexture("TIMEICON_FASTFORWARD", "TIMEICONS", new Rectangle(24, 0, 12, 12));
		handler.addTexture("ObjectiveDisplay", Gdx.files.internal("ObjectiveDisplay.png"));
		handler.addTexture("DisplayColorCase", Gdx.files.internal("PixelDisplay.png"));
		handler.addTexture("DISPLAY", Gdx.files.internal("Display.png"));
		handler.addTexture("ICON_GOD", Gdx.files.internal("god_icon.png"));
	}

	@Override
	public void addObjects(TextureHandler handler) {
		earth = new World(handler, "erth.png", new Rectangle(50, 232, 512, 256), true, selectedEffect);
		addObject("EARTH_WORLD_DISPLAY", earth);
		
		pandora = new World(handler, "pandroa.png", new Rectangle(718, 232, 512, 256), true, selectedEffect);
		addObject("PANDORA_WORLD_DISPLAY", pandora);
		
		World.link(earth, pandora);
		
		TimeButton Pause = new TimeButton(DATA, "TimeButton", "TIMEICON_PAUSE", 0, new Rectangle(50, 560, 48, 48), this);
		addObject("TIME_0", Pause);
		
		TimeButton Play = new TimeButton(DATA, "TimeButton", "TIMEICON_PLAY", 1000, new Rectangle(50, 560+42, 48, 48), this);
		addObject("TIME_1", Play);
		
		TimeButton FastForward = new TimeButton(DATA, "TimeButton", "TIMEICON_FASTFORWARD", 250, new Rectangle(50, 560+42*2, 48, 48), this);
		addObject("TIME_2", FastForward);
		
		Pause.onClick(0, 0);
		
		objective = new ObjectiveDisplay(DATA, new Rectangle(104, 560, 1126, 132), earth, pandora);
		addObject("Objective", objective);
		
		PixmapDisplay pixDisplay = new PixmapDisplay(handler, new Rectangle(589, 331, 96, 96), earth);
		addObject("pixDisplay_earth", pixDisplay);
		
		PixmapDisplay pixDisplay2 = new PixmapDisplay(handler, new Rectangle(589, 229, 96, 96), pandora);
		addObject("pixDisplay_pandora", pixDisplay2);
		
		GodDisplay GodPoints = new GodDisplay(DATA, "DISPLAY", "ICON_GOD", new Rectangle(589, 433, 96, 48), 500);
		addObject("godPoints", GodPoints);//589 433
		
		TimeDisplay td = new TimeDisplay(DATA, new Rectangle(565, 495, 144, 48), earth);
		addObject("TimeDisplay", td);
	}

	@Override
	public void customLogic() {
		//Gdx.app.log("Selected Effect", selectedEffect.toString());
		if(INTERVAL>0 || lastTime == 0) {
			if(System.currentTimeMillis() - lastTime > INTERVAL) {
				lastTime = System.currentTimeMillis();
				pandora.onMonth();
				earth.onMonth();
				objective.checkCompleted();
				((GodDisplay) ScreenElements.get("godPoints")).amount+=20;
			}
		} 
	}

}
