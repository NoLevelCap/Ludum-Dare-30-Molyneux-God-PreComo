package com.nolevelcap.ld4830.effects;

import com.badlogic.gdx.graphics.Pixmap;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.drawables.World;
import com.nolevelcap.ld4830.drawables.World.LandType;

public class FreezeEffect extends Effect {

	public FreezeEffect(EFFECTS effect) {
		super(effect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pixmap affect(Pixmap map, World world, int px, int py) {
		
		for(int x=-2; x<2; x++) {
			for(int y=-2; y<2; y++) {
				if(px+x>1 && px+x<map.getWidth()-1 && py+y>1 && py+y<map.getHeight()-1) {
					if(LandType.getLandType(map.getPixel((int)px+x,(int) py+y)).equals(LandType.Water)) {
						map.setColor(LandType.Ice.id);
						map.drawPixel(px+x, py+y);
					} else if(LandType.getLandType(map.getPixel((int)px+x,(int) py+y)).equals(LandType.FLand)) {
						map.setColor(LandType.Land_Ice.id);
						map.drawPixel(px+x, py+y);
					}
				
				}
			}
		}
		
		world.setTextureAsPixmap();
		return null;
	}

}
