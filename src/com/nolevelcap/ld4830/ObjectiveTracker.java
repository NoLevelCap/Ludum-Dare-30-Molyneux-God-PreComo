package com.nolevelcap.ld4830;

import com.nolevelcap.ld4830.drawables.World;

public class ObjectiveTracker {
	
	private ResourceList WORLD1, WORLD2, TARGETWORLD1, TARGETWORLD2;
	private Objective target;
	
	public ObjectiveTracker(World WORLD1, World WORLD2, Objective target) {
		this.WORLD1 = new ResourceList(WORLD1.LANDMASS, WORLD1.population, WORLD1.resources, WORLD1.Temperature, WORLD1.goldilocks);
		this.WORLD2 = new ResourceList(WORLD2.LANDMASS, WORLD2.population, WORLD2.resources, WORLD2.Temperature, WORLD2.goldilocks);

		this.TARGETWORLD1 = Objective.generateTarget(target, this.WORLD1);
		this.TARGETWORLD2 = Objective.generateTarget(target, this.WORLD2);
		
		this.target = target;
	}
	
	public boolean completed(World WORLD1, World WORLD2) {
		ResourceList NEWW1 = new ResourceList(WORLD1.LANDMASS, WORLD1.population, WORLD1.resources, WORLD1.Temperature, WORLD1.goldilocks);
		ResourceList NEWW2 = new ResourceList(WORLD2.LANDMASS, WORLD2.population, WORLD2.resources, WORLD2.Temperature, WORLD2.goldilocks);
		
		if(target.Completed(TARGETWORLD1, NEWW1) && target.Completed(TARGETWORLD2, NEWW2)) {
			return true;
		} else {
			return false;
		}
	}

	public Objective getTarget() {
		return target;
	}
	
	
	
	
}
