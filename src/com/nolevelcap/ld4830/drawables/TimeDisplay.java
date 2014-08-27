package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;

public class TimeDisplay extends Drawable {
	
	TextureRegion Container;
	public World world;

	public TimeDisplay(TextureHandler DATA, Rectangle IMAGEBOUNDS, World LinkedWorld) {
		super(DATA, IMAGEBOUNDS, true);
		Container = DATA.getTexture("TimeContainer");
		world = LinkedWorld;
	}

	@Override
	public void load(TextureHandler DATA) {
		DATA.addTexture("TimeContainer", Gdx.files.internal("points.png"));
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Container, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		
		DATA.getFont("BASIC_ARIAL").setColor(Color.BLACK);
		DATA.getFont("BASIC_ARIAL").draw(batch, "M "+world.month+", Y "+world.years, IMAGEBOUNDS.x+ (IMAGEBOUNDS.width-DATA.getFont("BASIC_ARIAL").getBounds("M "+world.month+", Y "+world.years).width)/2f, IMAGEBOUNDS.y+30);
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
