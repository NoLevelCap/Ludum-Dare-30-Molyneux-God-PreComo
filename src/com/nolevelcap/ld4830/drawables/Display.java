package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.drawable.Text;
import com.nolevelcap.ld4830.WorldsLayer.ResNames;

public class Display extends Drawable {
	
	TextureRegion Container, Icon;
	ResNames name;
	World LinkedWorld;

	public Display(TextureHandler DATA, String ContainerKey, String IconKey, ResNames name, Rectangle IMAGEBOUNDS, World LinkedWorld) {
		super(DATA, IMAGEBOUNDS, true);
		Container = DATA.getTexture(ContainerKey);
		Icon = DATA.getTexture(IconKey);
		this.name =name;
		this.LinkedWorld = LinkedWorld;
	}

	@Override
	public void load(TextureHandler DATA) {
	
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Container, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		batch.draw(Icon, IMAGEBOUNDS.x+6, IMAGEBOUNDS.y+6, Icon.getRegionWidth()*3, Icon.getRegionHeight()*3);
		
		DATA.getFont("BASIC_ARIAL").setColor(Color.BLACK);
		switch(name) {
		case Goldilocks:
			DATA.getFont("BASIC_ARIAL").draw(batch, LinkedWorld.goldilocks+"%", IMAGEBOUNDS.x+52, IMAGEBOUNDS.y+30);
			break;
		case LandMass:
			DATA.getFont("BASIC_ARIAL").draw(batch, ((int)Math.ceil(LinkedWorld.LANDMASS*100))+"%", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			break;
		case Population:
			if(LinkedWorld.population>1000000000) {
				DATA.getFont("BASIC_ARIAL").draw(batch, ((int) Math.floor(LinkedWorld.population/1000000000f))+"B", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			} else if(LinkedWorld.population>1000000) {
				DATA.getFont("BASIC_ARIAL").draw(batch, ((int) Math.floor(LinkedWorld.population/1000000f))+"M", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			} else if(LinkedWorld.population>1000) {
				DATA.getFont("BASIC_ARIAL").draw(batch, ((int) Math.floor(LinkedWorld.population/1000f))+"K", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			}
			break;
		case Resources:
			if(LinkedWorld.resources>1000000000) {
				DATA.getFont("BASIC_ARIAL").draw(batch, "£"+((int) Math.floor(LinkedWorld.resources/1000000000f))+"B", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			} else if(LinkedWorld.resources>1000000) {
				DATA.getFont("BASIC_ARIAL").draw(batch, "£"+((int) Math.floor(LinkedWorld.resources/1000000f))+"M", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			} else if(LinkedWorld.resources>1000) {
				DATA.getFont("BASIC_ARIAL").draw(batch, "£"+((int) Math.floor(LinkedWorld.resources/1000f))+"K", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			}
			break;
		case Temperature:
			DATA.getFont("BASIC_ARIAL").draw(batch, LinkedWorld.Temperature+"C", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
			break;
		default:
			break;
		}
		
		
		if(HOVERED) {
		}
		
		
	}
	
	

	@Override
	public void logic() {
		super.logic();
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(int mx, int my) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHover(int mx, int my) {
		Text t = (Text) Parent.ScreenElements.get("TOOLTIP");
		t.Render = true;
		t.setInput(name.Tooltip);
	}

	@Override
	public void whileHovered(int mx, int my) {
		Text t = (Text) Parent.ScreenElements.get("TOOLTIP");
		t.Render = true;
	}

	@Override
	public void outHovered(int mx, int my) {
		Text t = (Text) Parent.ScreenElements.get("TOOLTIP");
		t.Render = false;
	}

	@Override
	public void onRelease(int mx, int my) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub

	}

}
