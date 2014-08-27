package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.ld4830.WorldsLayer;

public class TimeButton extends Drawable {
	
	TextureRegion Container, Icon;
	public boolean Selected;
	public int time;
	private WorldsLayer worldl;

	public TimeButton(TextureHandler DATA, String ContainerKey, String IconKey, int time, Rectangle IMAGEBOUNDS, WorldsLayer worldl) {
		super(DATA, IMAGEBOUNDS, true);
		Container = DATA.getTexture(ContainerKey);
		Icon = DATA.getTexture(IconKey);
		this.worldl = worldl;
		this.time = time;
		}

	@Override
	public void load(TextureHandler DATA) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Container, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		batch.draw(Icon, IMAGEBOUNDS.x+6, IMAGEBOUNDS.y+6, Icon.getRegionWidth()*3, Icon.getRegionHeight()*3);
		
		if(HOVERED || Selected) {
			batch.draw(DATA.getTexture("Grey"),IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		} 
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(int mx, int my) {
		if(!Selected) {
			
			for(int z=0; z<3; z++) {
				TimeButton mon = (TimeButton) Parent.ScreenElements.get("TIME_"+z);
				if(mon != null && !mon.equals(this)) {
					if(mon.Selected) {
						Gdx.app.log("Selected: ", "TIME_"+z);
						mon.Selected = false;
					}
				} else {
					Gdx.app.log("ERROR WITH: ", "TIME_"+z);
				}
			}
			worldl.INTERVAL = time;
			Selected = true;
		
		
		}
	}

	@Override
	public void onHover(int mx, int my) {
	}

	@Override
	public void whileHovered(int mx, int my) {
	}

	@Override
	public void outHovered(int mx, int my) {
	}

	@Override
	public void onRelease(int mx, int my) {
	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub

	}

}
