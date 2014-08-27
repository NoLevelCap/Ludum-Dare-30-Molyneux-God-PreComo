package com.nolevelcap.drawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.screen.Layer;


public abstract class Drawable {
	
	protected TextureHandler DATA;
	public int tempZ, Z;
	public Rectangle IMAGEBOUNDS, TEMPCOLLISIONBOUNDS, COLLISIONBOUNDS;
	public boolean INTERACTABLE, MOUSEHANDLLING, ACCESSABLE, HOVERED, CLICKED, TRANSPARENT, ONSCREEN, NEWBOUNDS, ForceRender, MOUSEHANDLINGA;
	public long TIMEOFLASTPRESS;
	public long PRESSCOOLDOWN = 100;
	public Array<Rectangle> NEWBOUNDSARRAY;
	public Layer Parent;
	
	
	public Drawable(TextureHandler DATA, Rectangle IMAGEBOUNDS, Rectangle COLLISIONBOUNDS, boolean INTERACTABLE, boolean ForceRender) {
		this(DATA, IMAGEBOUNDS, COLLISIONBOUNDS, INTERACTABLE);
		this.ForceRender = ForceRender;
	}
	
	public Drawable(TextureHandler DATA, Rectangle IMAGEBOUNDS, boolean INTERACTABLE, boolean ForceRender) {
		this(DATA, IMAGEBOUNDS, IMAGEBOUNDS, INTERACTABLE);
		this.ForceRender = ForceRender;
	}
	
	public Drawable(TextureHandler DATA, Rectangle IMAGEBOUNDS, boolean INTERACTABLE) {
		this(DATA, IMAGEBOUNDS, IMAGEBOUNDS, INTERACTABLE);
	}
	
	public Drawable(TextureHandler DATA, Rectangle IMAGEBOUNDS, Rectangle COLLISIONBOUNDS, boolean INTERACTABLE) {
		this.DATA = DATA;
		this.IMAGEBOUNDS = IMAGEBOUNDS;
		this.COLLISIONBOUNDS = COLLISIONBOUNDS;
		if(IMAGEBOUNDS.equals(COLLISIONBOUNDS)) {
			this.TEMPCOLLISIONBOUNDS = COLLISIONBOUNDS;
			this.COLLISIONBOUNDS = new Rectangle(0, 0, COLLISIONBOUNDS.width, COLLISIONBOUNDS.height);
		} else {
			this.TEMPCOLLISIONBOUNDS = new Rectangle(COLLISIONBOUNDS.x+IMAGEBOUNDS.x, COLLISIONBOUNDS.y+IMAGEBOUNDS.y, COLLISIONBOUNDS.width, COLLISIONBOUNDS.height);
		}
		TIMEOFLASTPRESS = System.currentTimeMillis();
		this.INTERACTABLE = INTERACTABLE;
		NEWBOUNDSARRAY = new Array<Rectangle>();
		this.ForceRender = false;
		this.load(DATA);
		this.MOUSEHANDLINGA = true;
	}
	
	public abstract void load(TextureHandler DATA);
	
	public abstract void render(SpriteBatch batch);
	
	public void logic() {
		if(INTERACTABLE) {
		
		int mx = (int) Parent.OnScreenMouseCoords.x;
		int my = (int) Parent.OnScreenMouseCoords.y;
		
		//Gdx.app.log("Interface Artifact - "+mx+", "+my, "HAS NO MOUSE HANDLING");
		
		if(MOUSEHANDLLING && MOUSEHANDLINGA) {
			
			ACCESSABLE = true;
			
			for(Rectangle Area: NEWBOUNDSARRAY) {
				if(Area.contains(mx, my)) {
					ACCESSABLE = false;
				}
			}
			
			NEWBOUNDSARRAY.clear();
			
			//Gdx.app.log("Interface Artifact - "+ACCESSABLE, "HAS MOUSE HANDLING");
			
			if(IMAGEBOUNDS.contains(mx, my) && ACCESSABLE) {
				if(!HOVERED) {
					HOVERED = true;
					onHover(mx, my);
					Gdx.app.log("Interface Artifact - "+this, "IS HOVERED: "+this.IMAGEBOUNDS.x+","+this.IMAGEBOUNDS.y);
				}else {
					whileHovered(mx, my);
				}
				if(Gdx.input.isButtonPressed(Buttons.LEFT) && System.currentTimeMillis() - TIMEOFLASTPRESS >= PRESSCOOLDOWN && !CLICKED) {
					TIMEOFLASTPRESS = System.currentTimeMillis();
					CLICKED = true;
					onClick(mx, my);
					Gdx.app.log("Interface Artifact - "+this, "IS PRESSED"+this.IMAGEBOUNDS.x+","+this.IMAGEBOUNDS.y);
				}
			} else {
				if(HOVERED) {
					outHovered(mx, my);
				}
				HOVERED = false;
				
			}
			
			if(!Gdx.input.isButtonPressed(Buttons.LEFT) && CLICKED) {
				CLICKED = false;
				onRelease(mx, my);
				//Gdx.app.log("Interface Artifact - "+this.getClass().hashCode(), "IS RELEASED");
			}
		} else {
			//Gdx.app.log("Interface Artifact - "+this.getClass().hashCode(), "HAS NO MOUSE HANDLING");
		}
		
		ONSCREEN = true;
		MOUSEHANDLLING = true;
		NEWBOUNDS = false;
		
		/*if(ACTIVE_EVENT !=null) {
				//Gdx.app.log("LAKDNLAKFN", z+"");
			if(ACTIVE_EVENT.isEnded()) {
				onEventEnd();https://www.youtube.com/feed/subscriptions
			}
		}*/
		}
		
		updateColBOUNDS();
		
	}
	
	public void updateColBOUNDS() {
		if(COLLISIONBOUNDS.x!=0 && COLLISIONBOUNDS.y!=0) {
			this.TEMPCOLLISIONBOUNDS = new Rectangle(COLLISIONBOUNDS.x+IMAGEBOUNDS.x, COLLISIONBOUNDS.y+IMAGEBOUNDS.y, COLLISIONBOUNDS.width, COLLISIONBOUNDS.height);
		} else {
			this.TEMPCOLLISIONBOUNDS.x = IMAGEBOUNDS.x;
			this.TEMPCOLLISIONBOUNDS.y = IMAGEBOUNDS.y;
		}
		
	}
	
	public void setParent(Layer Parent) {
		this.Parent = Parent;
	}
	
	public abstract void onCollide(Drawable obj);
	public abstract void onClick(int mx, int my);
	public abstract void onHover(int mx, int my);
	public abstract void whileHovered(int mx, int my);
	public abstract void outHovered(int mx, int my);
	public abstract void onRelease(int mx, int my);
	public abstract void onScroll(int amount);
}
