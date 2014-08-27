package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;

public class GodDisplay extends Drawable {
	
	TextureRegion Container, Icon;
	public int amount;

	public GodDisplay(TextureHandler DATA, String ContainerKey, String IconKey, Rectangle IMAGEBOUNDS, int STARTING_AMOUNT) {
		super(DATA, IMAGEBOUNDS, true);
		Container = DATA.getTexture(ContainerKey);
		Icon = DATA.getTexture(IconKey);
		amount = STARTING_AMOUNT;
	}

	@Override
	public void load(TextureHandler DATA) {
	
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Container, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		batch.draw(Icon, IMAGEBOUNDS.x+6, IMAGEBOUNDS.y+6, Icon.getRegionWidth()*3, Icon.getRegionHeight()*3);
		
		DATA.getFont("BASIC_ARIAL").setColor(Color.BLACK);
		if(amount>1000) {
			DATA.getFont("BASIC_ARIAL").draw(batch, ((double)amount/1000)+"K", IMAGEBOUNDS.x+52, IMAGEBOUNDS.y+30);
		} else {
			DATA.getFont("BASIC_ARIAL").draw(batch, amount+"", IMAGEBOUNDS.x+56, IMAGEBOUNDS.y+30);
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
	}

	@Override
	public void whileHovered(int mx, int my) {
	}

	@Override
	public void outHovered(int mx, int my) {
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
