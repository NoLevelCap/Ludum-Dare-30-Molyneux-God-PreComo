package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.drawable.Text;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;

public class Button extends Drawable {
	
	TextureRegion Container, Icon;
	public boolean Selected;
	private EFFECTS effect;
	private World world;

	public Button(TextureHandler DATA, String ContainerKey, String IconKey, EFFECTS effect, Rectangle IMAGEBOUNDS, World World) {
		super(DATA, IMAGEBOUNDS, true);
		Container = DATA.getTexture(ContainerKey);
		Icon = DATA.getTexture(IconKey);
		this.effect = effect;
		this.world = World;
		}

	@Override
	public void load(TextureHandler DATA) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Container, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		batch.draw(Icon, IMAGEBOUNDS.x+6, IMAGEBOUNDS.y+2, Icon.getRegionWidth()*3, Icon.getRegionHeight()*3);
		
		if(HOVERED || Selected && effect.Available) {
			batch.draw(DATA.getTexture("Grey"),IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		} 
		
		if(!effect.Available) {
			batch.draw(DATA.getTexture("Black"),IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		}
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(int mx, int my) {
		if(!Selected) {
			world.changeEffect(effect.getAttributedEffect());
			
			for(int z=0; z<20; z++) {
				Button mon = (Button) Parent.ScreenElements.get("OPTIONS_"+z);
				if(mon != null && !mon.equals(this)) {
					if(mon.Selected) {
						Gdx.app.log("Selected: ", "OPTIONS_"+z);
						mon.Selected = false;
					}
				} else {
					Gdx.app.log("ERROR WITH: ", "OPTIONS_"+z);
				}
			}
			Selected = true;
		
		
		} else {
			Selected = false;
		}
	}

	@Override
	public void onHover(int mx, int my) {
		if(effect.Available) {
		Text t = (Text) Parent.ScreenElements.get("TOOLTIP");
		t.Render = true;
		t.setInput(effect.Tooltip+": "+effect.cost);
		}
	}

	@Override
	public void whileHovered(int mx, int my) {
		if(effect.Available) {
		Text t = (Text) Parent.ScreenElements.get("TOOLTIP");
		t.Render = true;
		}
	}

	@Override
	public void outHovered(int mx, int my) {
		if(effect.Available) {
		Text t = (Text) Parent.ScreenElements.get("TOOLTIP");
		t.Render = false;
		}
	}

	@Override
	public void onRelease(int mx, int my) {
	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub

	}

}
