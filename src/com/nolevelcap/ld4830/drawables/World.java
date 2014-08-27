package com.nolevelcap.ld4830.drawables;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.ld4830.effects.Effect;

public class World extends Drawable {

	public enum LandType{
		Desert(-157006593),
		FLand(709957119),
		Forest(439618815),
		Ice(-604713217),
		Water(423842559),
		Border(255),
		Stone(1869574143),
		Lava(-16776961),
		SpawnLava(-15776961),
		Land_Ice(-1312512001),
		City(1415529215);

		public int id;

		private LandType(int id) {
			this.id = id;
		}

		public static LandType getLandType(int id) {
			for(LandType l: values()) {
				if(l.id == id) {
					return l;
				}
			}

			Gdx.app.error("No Existing LandType", id+"");
			return Border;
		}
	}

	public static boolean SIMULATE = true;

	Pixmap WORLD;
	Texture TEXTURE;
	private World linkedWorld;
	public boolean FORCEDHOVER;
	public int refx, refy;
	public int month, years;
	public Random RANDOM;
	public static int TotalLand = 252*124;
	private int IceAmount, DesertAmount, RainforestAmount, FertileAmount;
	public float LANDMASS;
	public long population, resources;
	public int Temperature;
	public int goldilocks = 100;
	public static float PerMonthModifier = 1.0063f;
	public float PopulationModifier = 1;
	public Effect SelectedEffect;

	public World(TextureHandler DATA, String PATH, Rectangle IMAGEBOUNDS,
			boolean INTERACTABLE, Effect SelectedEffect) {
		super(DATA, IMAGEBOUNDS, INTERACTABLE);
		WORLD = new Pixmap(Gdx.files.internal(PATH));
		TEXTURE = new Texture(WORLD);
		month = 0;
		RANDOM = new Random();
		this.SelectedEffect = SelectedEffect;
	}

	@Override
	public void load(TextureHandler DATA) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(TEXTURE, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);

		if(HOVERED) {
			batch.draw(DATA.getTexture("MOUSE"), refx+IMAGEBOUNDS.x-8, refy+IMAGEBOUNDS.y-8, 16, 16);
		} else if(FORCEDHOVER) {
			batch.draw(DATA.getTexture("MOUSE"), linkedWorld.refx+IMAGEBOUNDS.x-8, linkedWorld.refy+IMAGEBOUNDS.y-8, 16, 16);
		}
	}



	@Override
	public void logic() {
		refx = (int) (Parent.OnScreenMouseCoords.x - IMAGEBOUNDS.x);
		refy = (int) (Parent.OnScreenMouseCoords.y - IMAGEBOUNDS.y);
		super.logic();
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(int mx, int my) {
		//changePixel((int) ((mx-IMAGEBOUNDS.x)/2),(int) (IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y))/2, Color.BLACK);
		//setTextureAsPixmap();

		//linkedWorld.changePixel((int) ((mx-IMAGEBOUNDS.x)/2),(int) (IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y))/2, Color.BLACK);
		//linkedWorld.setTextureAsPixmap();
		if(((GodDisplay) Parent.ScreenElements.get("godPoints")).amount-SelectedEffect.effect.cost>=0) {
		
		SelectedEffect.affect(WORLD, this, (int) ((mx-IMAGEBOUNDS.x)/2),(int) (IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y))/2);
		SelectedEffect.affect(linkedWorld.WORLD, linkedWorld,  (int) ((mx-IMAGEBOUNDS.x)/2),(int) (IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y))/2);
		
		((GodDisplay) Parent.ScreenElements.get("godPoints")).amount-=SelectedEffect.effect.cost;
		}
	}

	@Override
	public void onHover(int mx, int my) {
		linkedWorld.FORCEDHOVER = true;
	}

	@Override
	public void onRelease(int mx, int my) {

	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub

	}

	public void changePixel(int px, int py, Color color) {
		WORLD.setColor(LandType.City.id);
		WORLD.drawPixel(px, py);
	}

	public void setTextureAsPixmap() {
		TEXTURE = new Texture(WORLD);
	}

	@Override
	public void whileHovered(int mx, int my) {
		//Gdx.app.log("PIXEL INFORMATION", "("+(mx-IMAGEBOUNDS.x)/2+","+(IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y)/2)+")"+WORLD.getPixel((int) ((mx-IMAGEBOUNDS.x)/2),(int) (IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y))/2));
	}
	
	public int getWorldColor(int mx, int my) {
		if(HOVERED) {
			return WORLD.getPixel((int) ((mx-IMAGEBOUNDS.x)/2),(int) (IMAGEBOUNDS.height-(my-IMAGEBOUNDS.y))/2);
		} else if(FORCEDHOVER){
			Gdx.app.log("INFO", (int) linkedWorld.refx+"/"+(int) (linkedWorld.WORLD.getHeight()-linkedWorld.refy));
			return WORLD.getPixel((int) (linkedWorld.refx/2f),(int) ((linkedWorld.IMAGEBOUNDS.height-linkedWorld.refy)/2f));
		}
		return 0;
	}
	public static void link(World WORLD1, World WORLD2) {
		WORLD1.linkedWorld = WORLD2;
		WORLD2.linkedWorld = WORLD1;
	}

	@Override
	public void outHovered(int mx, int my) {
		linkedWorld.FORCEDHOVER = false;
	}

	public void onMonth() {
		if(SIMULATE) {
			if(month<12) {
				month++;
			} else {
				years++;
				month = 1;
			}

			int landMass = 0;
			population = 0;
			IceAmount = 0; RainforestAmount = 0; DesertAmount = 0; FertileAmount = 0;
			Temperature = 0;
			resources = 0;
			PopulationModifier = PerMonthModifier*PopulationModifier;

			Gdx.app.log("WORLD FORMAT", WORLD.getFormat().name());

			for(int x=0; x<WORLD.getWidth(); x++) {
				for(int y=0; y<WORLD.getHeight(); y++) {
					if(goldilocks<=0) {
						Temperature +=50;
						/*if(shouldChange(isTilesAround(LandType.Water, x, y), 128f, RANDOM)) {
							WORLD.setColor(LandType.Lava.id);
							WORLD.drawPixel(x, y);
						}*/
						
						if(shouldChange(isTilesAround(LandType.Land_Ice, x, y), 64f, RANDOM)) {
							WORLD.setColor(LandType.FLand.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.Desert, x, y), 1f, RANDOM) && LandType.getLandType(WORLD.getPixel(x, y)).equals(LandType.Land_Ice)) {
							WORLD.setColor(LandType.FLand.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.FLand, x, y), 1f, RANDOM)) {
							WORLD.setColor(LandType.Desert.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.Desert, x, y), 1f, RANDOM) && LandType.getLandType(WORLD.getPixel(x, y)).equals(LandType.FLand)) {
							WORLD.setColor(LandType.Desert.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.Desert, x, y), 750f, RANDOM)) {
							WORLD.setColor(LandType.Lava.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.Ice, x, y), 128f, RANDOM)) {
							WORLD.setColor(LandType.Water.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.City, x, y), 1f, RANDOM)) {
							WORLD.setColor(LandType.Desert.id);
							WORLD.drawPixel(x, y);
						}
						
						if(shouldChange(isTilesAround(LandType.Desert, x, y), 1f, RANDOM) && LandType.getLandType(WORLD.getPixel(x, y)).equals(LandType.City)) {
							WORLD.setColor(LandType.Desert.id);
							WORLD.drawPixel(x, y);
						}
					}
					switch(LandType.getLandType(WORLD.getPixel(x, y))) {
					case Border:
						break;
					case City:
						if(shouldChange(isTilesAround(LandType.FLand, x, y), 400f, RANDOM)) {
							WORLD.setColor(LandType.FLand.id);
							WORLD.drawPixel(x, y);
						}
						if(shouldChange(isTilesAround(LandType.Desert, x, y), 350f, RANDOM)) {
							WORLD.setColor(LandType.Desert.id);
							WORLD.drawPixel(x, y);
						}
						if(shouldChange(isTilesAround(LandType.Ice, x, y), 250f, RANDOM)) {
							WORLD.setColor(LandType.Ice.id);
							WORLD.drawPixel(x, y);
						}
						
						population += 100;
						landMass++;
						FertileAmount++;
						resources+=50*PopulationModifier;
						break;
					case Desert:
						if(shouldChange(isTilesAround(LandType.City, x, y), 300f, RANDOM)) {
							WORLD.setColor(LandType.City.id);
							WORLD.drawPixel(x, y);
						}
						population += 1;
						landMass++;
						DesertAmount++;
						resources+=100*PopulationModifier;
						break;
					case FLand:
						if(shouldChange(isTilesAround(LandType.City, x, y), 250f, RANDOM)) {
							WORLD.setColor(LandType.City.id);
							WORLD.drawPixel(x, y);
						}
						if(month > 3 && month < 10) {
							if(shouldChange(isTilesAround(LandType.Ice, x, y), 8f, RANDOM)) {
								WORLD.setColor(LandType.Ice.id);
								WORLD.drawPixel(x, y);
							}

							if(shouldChange(isTilesAround(LandType.Land_Ice, x, y), 8f, RANDOM)) {

								WORLD.setColor(LandType.Land_Ice.id);
								WORLD.drawPixel(x, y);
							}
						}
						population += 10*PopulationModifier;
						landMass++;
						FertileAmount++;
						resources+=5;
						break;
					case Forest:
						if(shouldChange(isTilesAround(LandType.City, x, y), 275f, RANDOM)) {
							WORLD.setColor(LandType.City.id);
							WORLD.drawPixel(x, y);
						}
						population += 4;
						landMass++;
						RainforestAmount++;
						resources-=5;
						break;
					case Ice:
						if(month < 3 || month > 10) {
							if(shouldChange(isTilesAround(LandType.FLand, x, y), 8f, RANDOM)) {
								WORLD.setColor(LandType.FLand.id);
								WORLD.drawPixel(x, y);
							}

							if(shouldChange(isTilesAround(LandType.Water, x, y),6f, RANDOM)) {
								WORLD.setColor(LandType.Water.id);
								WORLD.drawPixel(x, y);
							}

							if(shouldChange(isTilesAround(LandType.Desert, x, y),0.25f, RANDOM)) {
								WORLD.setColor(LandType.Water.id);
								WORLD.drawPixel(x, y);
							}
						}
						population += 1*PopulationModifier;
						landMass++;
						IceAmount++;
						resources-=25;
						break;
					case Lava:
						WORLD.setColor(LandType.Stone.id);
						WORLD.drawPixel(x, y);
						resources-=50;
						break;
					case Stone:
						if(shouldChange(isTilesAround(LandType.Water, x, y),2f, RANDOM)) {
							WORLD.setColor(LandType.Desert.id);
							WORLD.drawPixel(x, y);
						}

						if(shouldChange(isTilesAround(LandType.FLand, x, y),10f, RANDOM)) {
							WORLD.setColor(LandType.FLand.id);
							WORLD.drawPixel(x, y);
						}
						resources+=1;
						population += 1*PopulationModifier;
						landMass++;
						break;
					case Water:
						if(month > 3 && month < 10) {
							if(shouldChange(isTilesAround(LandType.Ice, x, y), 16f, RANDOM)) {
								WORLD.setColor(LandType.Ice.id);
								WORLD.drawPixel(x, y);
							}

							if(shouldChange(isTilesAround(LandType.Land_Ice, x, y), 16f, RANDOM)) {

								WORLD.setColor(LandType.Ice.id);
								WORLD.drawPixel(x, y);
							}
						}
						resources+=75;
						break;
					case Land_Ice:

						if(month < 3 || month > 10) {
							if(shouldChange(isTilesAround(LandType.FLand, x, y), 16f, RANDOM)) {
								WORLD.setColor(LandType.FLand.id);
								WORLD.drawPixel(x, y);
							}
						}
						population += 1*PopulationModifier;
						landMass++;
						IceAmount++;
						resources-=25;
						break;
					case SpawnLava:
						
						break;
					default:
						break;
					}
				}
			}

			LANDMASS = (float) landMass/(float) TotalLand;
			Temperature = (DesertAmount * 45) + (FertileAmount * 25) + (RainforestAmount * 35) + (IceAmount * 2);
			Temperature = (int) ((float)Temperature / (float) (DesertAmount+FertileAmount+RainforestAmount+IceAmount));



			Gdx.app.log("World Info: "+getClass().getName(), "On Month: "+month+", and year "+ years+", there was "+landMass+" pixels of fertile land.");
			setTextureAsPixmap();
		}
	}

	public float isTilesAround(LandType countedTile, int px, int py) {
		float Amount = 0;
		if(px < WORLD.getWidth()-2 && px >2 && py < WORLD.getHeight()-2 && py > 2) {}
		else {
			//Gdx.app.log("TOO", "CLOSE TO EDGE");
			return Amount;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py-1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px-1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px-1, py)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px, py+1)).equals(countedTile)) {
			Amount++;
		}
		
	

		return Amount;

	}

	public boolean shouldChange(float Amount,float divisor, Random ran) {
		if(ran.nextFloat()<(Amount/divisor)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void changeEffect(Effect e) {
		this.SelectedEffect = e;
		linkedWorld.SelectedEffect = e;
	}



}
