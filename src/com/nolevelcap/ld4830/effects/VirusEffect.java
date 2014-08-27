package com.nolevelcap.ld4830.effects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.ld4830.drawables.World.LandType;

public class VirusEffect extends Effect {

	public VirusEffect(EFFECTS effect) {
		super(effect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pixmap affect(Pixmap map, World world, int px, int py) {
		for(Vector2 point: getSpiralSelectionPoints(15, 15, px, py)) {
			if(point.x>1 && point.x<map.getWidth()-1 && point.y>1 && point.y<map.getHeight()-1) {
				if(shouldChange(isTilesAround(map, (int) point.x, (int) point.y, LandType.FLand),8f, r)&& !LandType.getLandType(map.getPixel((int) point.x, (int) point.y)).equals(LandType.Water)) {
					if(LandType.getLandType(map.getPixel((int) point.x, (int) point.y)).equals(LandType.City) && !LandType.getLandType(map.getPixel((int) point.x, (int) point.y)).equals(LandType.FLand)) {
					map.setColor(LandType.FLand.id);
					map.drawPixel((int) point.x, (int) point.y);
					}
				}
			}
		}
		
		world.setTextureAsPixmap();
		return null;
	}

}
