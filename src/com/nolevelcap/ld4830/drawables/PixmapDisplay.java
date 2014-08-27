package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.ld4830.drawables.World.LandType;

public class PixmapDisplay extends Drawable{
	Pixmap displayMap;
	Texture displayColor;
	World LinkedWorld;
	int lastColor;

	public PixmapDisplay(TextureHandler DATA, Rectangle IMAGEBOUNDS, World LinkedWorld) {
		super(DATA, IMAGEBOUNDS, false);
		displayMap = new Pixmap(1, 1, Format.RGBA8888);
		setColor(LandType.Border.id);
		this.LinkedWorld = LinkedWorld;
	}

	@Override
	public void load(TextureHandler DATA) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void logic() {
		if(LinkedWorld.getWorldColor((int)Parent.OnScreenMouseCoords.x, (int)Parent.OnScreenMouseCoords.y)!=lastColor && LinkedWorld.getWorldColor((int)Parent.OnScreenMouseCoords.x, (int)Parent.OnScreenMouseCoords.y)!=0) {
			setColor(LinkedWorld.getWorldColor((int)Parent.OnScreenMouseCoords.x, (int)Parent.OnScreenMouseCoords.y));
		}
		super.logic();
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(DATA.getTexture("DisplayColorCase"), IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		batch.draw(displayColor, IMAGEBOUNDS.x+6, IMAGEBOUNDS.y+6, IMAGEBOUNDS.width-12, IMAGEBOUNDS.height-12);
	}
	
	public void setColor(int Color) {
		lastColor = Color;
		displayMap.setColor(Color);
		displayMap.drawPixel(0, 0);
		displayColor = new Texture(displayMap);
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

	@Override
	public void onRelease(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub
		
	}

}
