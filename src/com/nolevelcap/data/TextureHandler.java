package com.nolevelcap.data;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class TextureHandler {
	
	private HashMap<String, TextureRegion> textures;
	private HashMap<String, Sound> sounds;
	private HashMap<String, BitmapFont> fonts;
	
	Sound playingSound;
	
	public TextureHandler() {
		textures = new HashMap<String, TextureRegion>();
		sounds = new HashMap<String, Sound>();
		fonts = new HashMap<String, BitmapFont>();
		
		//playingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Hover.wav"));
	}
	
	public void addTexture(String key, Texture value) {
		addTexture(key, new TextureRegion(value));
	}
	
	public void addTexture(String key, TextureRegion value) {
		if(textures.containsKey(key)) {
			Gdx.app.error("TEXTURE HANDLER ERROR", key+" IS ALREADY BEING USED, THE OLD VALUE IS NOW NOT LOADED");
		}
		if(textures.containsValue(value)) {
			Gdx.app.error("TEXTURE HANDLER ERROR", "Key ALREADY ADDED");
		}
		textures.put(key, value);
	}
	
	public void addTexture(String key, FileHandle file) {
		Texture value = new Texture(file, true);
		//value.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		addTexture(key, new TextureRegion(value));
	}
	
	public void addTexture(String key, FileHandle file, Rectangle bounds) {
		Texture value = new Texture(file);
		addTexture(key, new TextureRegion(value, (int) bounds.x, (int) bounds.y, (int)bounds.width, (int) bounds.height));
	}
	
	public void addTexture(String key, String TexKey, Rectangle bounds) {
		Texture value = getTexture(TexKey).getTexture();
		addTexture(key, new TextureRegion(value, (int) bounds.x, (int) bounds.y, (int)bounds.width, (int) bounds.height));
	}
	
	public TextureRegion getTexture(String key) {
		if(textures.containsKey(key) && textures.get(key) != null) {
			return textures.get(key);
		} else {
			new Exception();
			return null;
		}
		
	}
	
	public Array<TextureRegion> getFrames(String key, int amount) {
		Array<TextureRegion> RETURN_TEXS = new Array<TextureRegion>();
		for(int i=1; i<=amount; i++) {
			if(textures.containsKey(key+"_"+amount) && textures.get(key+"_"+amount) != null) {
				RETURN_TEXS.add(textures.get(key+"_"+i));
			} else {
				new Exception();
				return null;
			}
		}
		return RETURN_TEXS;
		
	}
	
	public void clearTextures() {
		textures.clear();
	}
	
	public void addSound(String key, Sound value) {
		sounds.put(key, value);
	}
	
	public void addSound(String key, FileHandle file) {
		Sound value = Gdx.audio.newSound(file);
		sounds.put(key, value);
	}
	
	public Sound getSound(String key) {
		if(sounds.containsKey(key)) {
			return sounds.get(key);
		} else {
			new Exception();
		}
		return null;
	}
	
	public void playSound(String key, float volume) {
		if(!(playingSound.equals(sounds.get(key)))){
			playingSound = getSound(key);
			getSound(key).play(volume);
		}
	}
	
	public void playSound(String key) {
		if(!(playingSound.equals(getSound(key)))){
			playingSound = getSound(key);
			getSound(key).play(1.0f);
		}
	}
	
	public void clearSounds() {
		sounds.clear();
	}
	
	public void addFont(String key) {
		BitmapFont font = new BitmapFont();
		fonts.put(key, font);
		
	}
	
	public void addFont(String key, FileHandle file) {
		BitmapFont font = new BitmapFont(file);
		fonts.put(key, font);
		
	}
	
	public BitmapFont getFont(String key) {
		if(fonts.containsKey(key)) {
			return fonts.get(key);
		} else {
			new Exception();
		}
		return null;
		
	}
	
	public void clearFonts() {
		
	}
}
