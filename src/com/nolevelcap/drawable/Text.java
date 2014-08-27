package com.nolevelcap.drawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.data.TextureHandler;

public class Text extends Drawable{
	private String input;
	private BitmapFont font;
	private boolean Wrapped;
	private float WrapWidth;
	public boolean Render;
	public Color TEMP, Chosen;
	
	public Text(TextureHandler DATA, String input, Vector2 location, boolean INTERACTABLE, boolean ForceRender) {
		this(DATA, input, location,  new BitmapFont(), INTERACTABLE);
		super.ForceRender = ForceRender;
	}
	
	public Text(TextureHandler DATA, String input, Vector2 location, boolean INTERACTABLE) {
		this(DATA, input, location,  new BitmapFont(), INTERACTABLE);
	}
	
	public Text(TextureHandler DATA, String input, Vector2 location , BitmapFont font, boolean INTERACTABLE) {
		super(DATA, new Rectangle(location.x, location.y, font.getBounds(input).width, font.getBounds(input).height), INTERACTABLE);
		this.font = font;
		this.font.setColor(Color.WHITE);
		this.input = input;
		this.Wrapped = false;
		this.WrapWidth = 0.0f;
		this.Render = true;
		this.Chosen = Color.WHITE;
	}

	@Override
	public void render(SpriteBatch batch) {
		//Gdx.app.log("TEST", BOUNDS.x+" 123 "+BOUNDS.y);
		if(Render) {
			TEMP = font.getColor();
			font.setColor(Chosen);
		if(Wrapped && WrapWidth != 0.0f) {
			font.drawWrapped(batch, input, IMAGEBOUNDS.x, IMAGEBOUNDS.y, WrapWidth);
		} else {
			font.drawMultiLine(batch, input, IMAGEBOUNDS.x, IMAGEBOUNDS.y);
		}
			font.setColor(TEMP);
		}
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
		super.logic();
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}
	
	public void setColor(Color color) {
		Chosen = new Color(color);
	}

	@Override
	public void onClick(int mx, int my) {
		Gdx.app.log("ABC", "1234");
	}

	@Override
	public void onHover(int mx, int my) {
		Gdx.app.log("ABC", "12234");
	}
	
	public void toggleRender() {
		if(Render) {
			Render = false;
		} else {
			Render = true;
		}
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
}
