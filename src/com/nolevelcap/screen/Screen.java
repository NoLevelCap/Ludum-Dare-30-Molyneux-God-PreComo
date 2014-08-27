package com.nolevelcap.screen;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SnapshotArray;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;

public abstract class Screen {

	public SnapshotArray<Layer> layers;
	public HashMap<String, Object> ScreenElements;
	public Vector2 MousePos;
	protected TextureHandler handler;

	public Screen(TextureHandler handler) {
		layers = new SnapshotArray<Layer>(true, 0, Layer.class);
		ScreenElements = new HashMap<String, Object>();
		this.handler = handler;

		this.addTextures(handler);
		this.addObjects(handler);
	}

	public void render() {
		layers.reverse();
		for(Layer child: layers.begin()) {
			if(child != null) { 
				child.render();
			}
		}
		layers.reverse();
	}

	public void logic() {
		OnScreenTest();


		for(Layer child: layers.begin()) {
			if(child != null) { 
				child.logic();
			}
		}

		customLogic();

		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			if(Gdx.input.isKeyPressed(Keys.R)) {
				Gdx.app.log("SCREEN", "RELOADING OBJECTS");
				for(Layer child: layers.begin()) {
					if(child != null) { 
						child.CHILDREN.clear();
						child.addObjects(child.DATA);
					}
				}
			}
		}
	}

	public void addObject(Layer obj) {
		layers.add(obj);
		obj.Z = layers.indexOf(obj, true);
		updateZIndexes();
	}

	public void addObject(Layer obj, int index) {
		layers.insert(index, obj);
		obj.Z = index;
		updateZIndexes();
	}

	public void moveObject(Layer obj, int index) {
		layers.removeValue(obj, true);
		layers.insert(index, obj);
		obj.Z = index;
		updateZIndexes();
	}

	public void updateZIndexes(){
		for(Layer uO: layers.begin()) {
			if(uO != null) {
				uO.Z = layers.indexOf(uO, true);
				uO.tempZ = uO.Z;
			}
		}
	}

	public void resize(int width, int height) {
		for(Layer child: layers.begin()) {
			if(child != null) { 
				child.resize(width, height);
			}
		}
	}

	private void OnScreenTest() {
		for(Object o0: ScreenElements.values()) {
			if(o0 instanceof Drawable) {
				Drawable u0 = (Drawable)o0;
				if(u0 != null && !u0.TRANSPARENT && u0.INTERACTABLE) {
					for(Object o1: ScreenElements.values()) {
						if(o1 instanceof Drawable) {
							Drawable u1 = (Drawable)o1;
							if(u1 != null && !u1.TRANSPARENT && u1 != u0 && u1.INTERACTABLE) {
								if(u1.TEMPCOLLISIONBOUNDS.overlaps(u0.TEMPCOLLISIONBOUNDS)) {
									u1.onCollide(u0);
								}
								if(u1.TEMPCOLLISIONBOUNDS.overlaps(u0.TEMPCOLLISIONBOUNDS) && u1.Z < u0.Z || u1.Parent.Z < u0.Parent.Z) {
									if(!u1.TEMPCOLLISIONBOUNDS.contains(u0.TEMPCOLLISIONBOUNDS) && u1.MOUSEHANDLINGA) {
										u0.NEWBOUNDSARRAY.add(u1.TEMPCOLLISIONBOUNDS);
										u0.NEWBOUNDS = true;
										u0.MOUSEHANDLLING = true;
									} else{
										u0.ONSCREEN = false;
										u0.MOUSEHANDLLING = false;
										u0.NEWBOUNDS = false;	

									}
								} else {

								}
							}
						}
					}
				}
			}
		}
	}

	public void REFRESH_OBJECTS() {
		layers.clear();
		addObjects(handler);
		for(Layer layer: layers) {
			layer.REFRESH_OBJECTS();
		}
	}



	public abstract void addTextures(TextureHandler handler);

	public abstract void addObjects(TextureHandler handler);

	public abstract void customLogic();
}
