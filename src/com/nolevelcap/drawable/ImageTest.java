package com.nolevelcap.drawable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;

public class ImageTest extends Drawable{
	
	public Sprite IMAGE;
	private float opacity;
	
	public ImageTest(TextureHandler DATA, TextureRegion image, float opacity, Rectangle bounds, boolean INTERACTABLE) {
		super(DATA, bounds, INTERACTABLE);
		IMAGE = new Sprite(image);
		IMAGE.setAlpha(opacity);
		IMAGE.setBounds(IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		this.setOpacity(opacity);
	}

	@Override
	public void render(SpriteBatch batch) {
		//Gdx.app.log("TEST", BOUNDS.x+" 123 "+BOUNDS.y);
		IMAGE.draw(batch);
	}

	public void logic() {
		// TODO Auto-generated method stub
		super.logic();
	}

	@Override
	public void onClick(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHover(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRelease(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(TextureHandler DATA) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void whileHovered(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outHovered(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
		IMAGE.setAlpha(opacity);
	}
	
	public void increaseOpacity(float opacity) {
		this.opacity += opacity;
		IMAGE.setAlpha(this.opacity);
	}
}
