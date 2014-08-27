package com.nolevelcap.ld4830.effects;

import com.badlogic.gdx.graphics.Pixmap;
import com.nolevelcap.ld4830.WorldsLayer.EFFECTS;
import com.nolevelcap.ld4830.drawables.World;

public class NoEffect extends Effect {

	public NoEffect(EFFECTS effect) {
		super(effect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pixmap affect(Pixmap map, World world, int px, int py) {
		// TODO Auto-generated method stub
		return map;
	}

}
