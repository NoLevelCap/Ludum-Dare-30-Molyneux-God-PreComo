package com.nolevelcap.ld4830.effects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.ld4830.drawables.World.LandType;

public class EncouragePopGrowth extends Effect {

	public EncouragePopGrowth(EFFECTS effect) {
		super(effect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pixmap affect(Pixmap map, World world, int px, int py) {
		map.setColor(LandType.City.id);
		for(int x=-1; x<1; x++) {
			for(int y=-1; y<1; y++) {
				if(px+x>1 && px+x<map.getWidth()-1 && py+y>1 && py+y<map.getHeight()-1 && !LandType.getLandType(map.getPixel(px+x,py+y)).equals(LandType.Water)) {
				map.drawPixel(px+x, py+y);
				}
			}
		}
		
		//for(int i=0; i<5;i++) {
		for(Vector2 point: getSpiralSelectionPoints(5, 5, px, py)) {
			if(point.x>1 && point.x<map.getWidth()-1 && point.y>1 && point.y<map.getHeight()-1) {
				if(shouldChange(isTilesAround(map, (int) point.x, (int) point.y, LandType.City),8f, r) && !LandType.getLandType(map.getPixel((int)point.x,(int) point.y)).equals(LandType.Water)) {
					map.setColor(LandType.City.id);
					map.drawPixel((int) point.x, (int) point.y);
				}
			}
		}
		//}
		world.setTextureAsPixmap();
		return map;
	}

}
