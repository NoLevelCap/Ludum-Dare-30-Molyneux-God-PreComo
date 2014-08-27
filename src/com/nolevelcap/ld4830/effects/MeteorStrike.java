package com.nolevelcap.ld4830.effects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.ld4830.drawables.World.LandType;

public class MeteorStrike extends Effect{
	
	

	public MeteorStrike(EFFECTS effect) {
		super(effect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pixmap affect(Pixmap map, World world, int px, int py) {
		map.setColor(LandType.Stone.id);
		for(int x=-2; x<2; x++) {
			for(int y=-2; y<2; y++) {
				if(px+x>1 && px+x<map.getWidth()-1 && py+y>1 && py+y<map.getHeight()-1) {
				map.drawPixel(px+x, py+y);
				}
			}
		}
		
		//for(int i=0; i<5;i++) {
		for(Vector2 point: getSpiralSelectionPoints(15, 15, px, py)) {
			if(point.x>1 && point.x<map.getWidth()-1 && point.y>1 && point.y<map.getHeight()-1) {
				if(shouldChange(isTilesAround(map, (int) point.x, (int) point.y, LandType.Stone),8f, r)) {
					map.setColor(LandType.Stone.id);
					map.drawPixel((int) point.x, (int) point.y);
				}
			}
		}
		
		for(Vector2 point: getSpiralSelectionPoints(15, 15, px, py)) {
			if(point.x>1 && point.x<map.getWidth()-1 && point.y>1 && point.y<map.getHeight()-1) {
				if(shouldChange(isTilesAround(map, (int) point.x, (int) point.y, LandType.Desert, LandType.Lava, LandType.Forest, LandType.FLand),1f, r) && LandType.getLandType(map.getPixel((int)point.x,(int) point.y)).equals(LandType.Stone)) {
					map.setColor(LandType.Lava.id);
					map.drawPixel((int) point.x, (int) point.y);
				}
			}
		}
		//}
		
		world.goldilocks -= 15*r.nextDouble();
		world.setTextureAsPixmap();
		return null;
	}

}
