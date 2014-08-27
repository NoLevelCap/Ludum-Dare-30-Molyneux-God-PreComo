package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;

public abstract class TextButton extends Drawable {
	
	TextureRegion Container;
	public String button_Text;

	public TextButton(TextureHandler DATA, String ContainerKey, String button_Text, Rectangle IMAGEBOUNDS) {
		super(DATA, IMAGEBOUNDS, true);
		Container = DATA.getTexture(ContainerKey);
		this.button_Text = button_Text;
		}

	@Override
	public void load(TextureHandler DATA) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Container, IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		
		DATA.getFont("Helvetica").setColor(Color.BLACK);
		DATA.getFont("Helvetica").draw(batch, button_Text, IMAGEBOUNDS.x+2+((IMAGEBOUNDS.width-DATA.getFont("Helvetica").getBounds(button_Text).width)/2), IMAGEBOUNDS.y+28);
		
		if(HOVERED && !CLICKED) {
			batch.draw(DATA.getTexture("Grey"),IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		} else if(CLICKED) {
			batch.draw(DATA.getTexture("Black"),IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		}
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(int mx, int my) {

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
		buttonEffect();
	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub

	}
	
	public abstract void buttonEffect();

}
