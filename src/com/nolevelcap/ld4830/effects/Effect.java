package com.nolevelcap.ld4830.effects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.ld4830.drawables.World.LandType;

public abstract class Effect {
	
	public EFFECTS effect;
	public Random r;
	
	public Effect(EFFECTS effect) {
		this.effect = effect;
		this.r = new Random();
	}
	
	public abstract Pixmap affect(Pixmap map, World world, int px, int py);
	
	public static float isTilesAround(Pixmap WORLD, int px, int py, LandType countedTile) {
		float Amount = 0;
		if(px < WORLD.getWidth() && px >0 && py < WORLD.getHeight() && py > 0) {}
		else {
			Gdx.app.log("TOO", "CLOSE TO EDGE");
			return Amount;
		}
		
		if(LandType.getLandType(WORLD.getPixel(px+1, py)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py-1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px-1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px-1, py)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px, py+1)).equals(countedTile)) {
			Amount++;
		}
	

		return Amount;

	}
	
	public static float isTilesAround(Pixmap WORLD, int px, int py, LandType... countedTiles) {
		float Amount = 0;
		if(px < WORLD.getWidth() && px >0 && py < WORLD.getHeight() && py > 0) {}
		else {
			Gdx.app.log("TOO", "CLOSE TO EDGE");
			return Amount;
		}
		
		for(LandType countedTile: countedTiles) {
		if(LandType.getLandType(WORLD.getPixel(px+1, py)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py-1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px+1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px-1, py+1)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px-1, py)).equals(countedTile)) {
			Amount++;
		}
		if(LandType.getLandType(WORLD.getPixel(px, py+1)).equals(countedTile)) {
			Amount++;
		}
		}
	

		return Amount;

	}

	public static boolean shouldChange(float Amount,float divisor, Random ran) {
		if(ran.nextFloat()<(Amount/divisor)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Array<Vector2> getSpiralSelectionPoints(int X, int Y, int cx, int cy){
		Array<Vector2> point = new Array<Vector2>();
		
		int x=0, y=0, dx = 0, dy = -1;
	    int t = Math.max(X,Y);
	    int maxI = t*t;

	    for (int i=0; i < maxI; i++){
	        if ((-X/2 <= x) && (x <= X/2) && (-Y/2 <= y) && (y <= Y/2)) {
	        	point.add(new Vector2(x+cx, y+cy));
	        }

	        if( (x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x == 1-y))) {
	            t=dx; dx=-dy; dy=t;
	        }   
	        x+=dx; 
	        y+=dy;
	    }
		return point;	
	}
}
