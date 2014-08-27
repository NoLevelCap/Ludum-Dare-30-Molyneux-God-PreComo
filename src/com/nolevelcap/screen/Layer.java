package com.nolevelcap.screen;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;

public abstract class Layer {
	protected TextureHandler DATA;
	public SnapshotArray<Drawable> CHILDREN;
	public HashMap<String, Object> ScreenElements;
	public Viewport VIEWPORT;
	public SpriteBatch BATCH;
	public int tempZ, Z;
	public Vector2 OnScreenMouseCoords;
	protected Screen Parent;
	
	public Layer(Screen Parent, TextureHandler data, Viewport viewport, ShaderProgram prog) {
		this(Parent, data, viewport);
		BATCH.setShader(prog);
	}
	
	public Layer(Screen Parent, TextureHandler data, Viewport viewport) {
		this.DATA = data;
		this.Parent = Parent;
		this.ScreenElements = Parent.ScreenElements;
		CHILDREN = new SnapshotArray<Drawable>(true, 0, Drawable.class);
		BATCH = new SpriteBatch();
		VIEWPORT = viewport;
		VIEWPORT.getCamera().translate(VIEWPORT.getWorldWidth()/2, VIEWPORT.getWorldHeight()/2, 0);
		VIEWPORT.update((int) viewport.getWorldWidth(),(int) viewport.getWorldHeight());
					OnScreenMouseCoords = new Vector2(
					((float) (Gdx.input.getX()-VIEWPORT.getScreenX())/VIEWPORT.getScreenWidth())*VIEWPORT.getWorldWidth()+VIEWPORT.getCamera().position.x-VIEWPORT.getWorldWidth()/2,
					((float) ((Gdx.graphics.getHeight() - Gdx.input.getY())-VIEWPORT.getScreenY())/VIEWPORT.getScreenHeight())*VIEWPORT.getWorldHeight()+VIEWPORT.getCamera().position.y-VIEWPORT.getWorldHeight()/2
					);
		
		init();
		addTextures(DATA);
		addObjects(DATA);
	}
	
	public void render() {
		//Gdx.app.log("MOUSE", VIEWPORT+"");
		Camera VIEWCAM = VIEWPORT.getCamera();
		BATCH.setProjectionMatrix(VIEWCAM.combined);
		BATCH.begin();
		CHILDREN.reverse();
		for(Drawable child: CHILDREN.begin()) {
			if(child != null) { 
				child.render(BATCH);
			}
		}
		CHILDREN.reverse();
		BATCH.end();
		VIEWCAM.update();
	}
	
	public void logic() {	
		customLogic();
		
		MouseHandlling();
		
		for(Drawable child: CHILDREN.items) {
			if(child != null) {
				child.logic();
			}
		}
		
		//WINDOW = new Rectangle(VIEWPORT.getScreenX(), VIEWPORT.getScreenY(), VIEWPORT.getScreenWidth(), VIEWPORT.getScreenHeight());
		VIEWPORT.update(VIEWPORT.getScreenWidth(), VIEWPORT.getScreenHeight());
		
	}
	
	private void MouseHandlling() {
		//Gdx.app.log("MOUSE", OnScreenMouseCoords.x+"/"+OnScreenMouseCoords.y+"..."+"");
		
		Rectangle windowOnScreen = new Rectangle(VIEWPORT.getScreenX(), VIEWPORT.getScreenY(), VIEWPORT.getScreenWidth(), VIEWPORT.getScreenHeight());
		
		//Gdx.app.log("MOUSE", VIEWPORT+"");
		
		if(windowOnScreen.contains(Gdx.input.getX(), (Gdx.graphics.getHeight() - Gdx.input.getY()))) {
			//Gdx.app.log("INFO1212", "ON SCREEN");
			OnScreenMouseCoords = new Vector2(
					((float) (Gdx.input.getX()-VIEWPORT.getScreenX())/VIEWPORT.getScreenWidth())*VIEWPORT.getWorldWidth()+VIEWPORT.getCamera().position.x-VIEWPORT.getWorldWidth()/2,
					((float) ((Gdx.graphics.getHeight() - Gdx.input.getY())-VIEWPORT.getScreenY())/VIEWPORT.getScreenHeight())*VIEWPORT.getWorldHeight()+VIEWPORT.getCamera().position.y-VIEWPORT.getWorldHeight()/2
					);
		}
		
	}
	
	public void addObject(String key, Drawable obj) {
		CHILDREN.add(obj);
		obj.Z = CHILDREN.indexOf(obj, true);
		updateZIndexes();
		obj.setParent(this);
		Parent.ScreenElements.put(key, obj);
	}
	
	public void addObject(String key, Drawable obj, int index) {
		CHILDREN.insert(index, obj);
		obj.Z = index;
		updateZIndexes();
		obj.setParent(this);
		Parent.ScreenElements.put(key, obj);
	}
	
	public void moveObject(Drawable obj, int index) {
		CHILDREN.removeValue(obj, true);
		CHILDREN.insert(index, obj);
		obj.Z = index;
		updateZIndexes();
	}
	
	public void addCustomObject(String KEY, Object obj) {
		Parent.ScreenElements.put(KEY, obj);
	}
	
	public void updateZIndexes(){
		for(Drawable uO: CHILDREN.begin()) {
			if(uO != null) {
				uO.Z = CHILDREN.indexOf(uO, true);
				uO.tempZ = uO.Z;
			}
		}
	}
	
	public void REFRESH_OBJECTS() {
		CHILDREN.clear();
		addObjects(DATA);
	}
	
	public void resize(int width, int height) {
		VIEWPORT.update(width, height);
	}
	
	public abstract void init();
	
	public abstract void addTextures(TextureHandler handler);
	
	public abstract void addObjects(TextureHandler handler);
	
	public abstract void customLogic();
}
