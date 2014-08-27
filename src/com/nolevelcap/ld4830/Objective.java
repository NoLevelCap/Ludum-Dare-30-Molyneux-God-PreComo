package com.nolevelcap.ld4830;

import java.util.Random;

import com.nolevelcap.ld4830.drawables.World;

public enum Objective {
	GDP_UP("Raise GDP 10%", 100, 1, 3, false),
	GDP_DOWN("Lower GDP 10%", 100, 1, 3, true),
	POP_UP("Raise Population 10%", 100, 1, 3, false),
	POP_DOWN("Lower Population 10%", 100, 1, 3, true),
	TEMP_UP("Raise Temp 10%", 100, 1, 3, false),
	TEMP_DOWN("Lower Temp 10%", 100, 1, 3, true),
	LANDMASS_UP("Raise Land 10%", 100, 1, 3, false),
	LANDMASS_DOWN("Lower Land 10%", 100, 1, 3, true),
	WORLDHEALTH_UP("Raise Health 10%", 100, 1, 3, false),
	WORLDHEALTH_DOWN("Lower Health 10%", 100, 1, 3, true);
	
	public String objectiveText;
	public int reward, compYears, compMonths;
	public boolean lowering;
	
	Objective(String objectiveText, int reward, int compYears, int compMonths, boolean lowering) {
		this.compMonths = compMonths;
		this.compYears = compYears;
		this.objectiveText = objectiveText;
		this.reward = reward;
		this.lowering = lowering;
	}
	
	public static ResourceList generateTarget(Objective obj, ResourceList WORLDRES) {
		switch(obj) {
		case GDP_DOWN:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), (long) (WORLDRES.getWRes()*0.9f), WORLDRES.getWTemp(), WORLDRES.getWGold());
		case GDP_UP:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), (long) (WORLDRES.getWRes()*1.1f), WORLDRES.getWTemp(), WORLDRES.getWGold());
		case LANDMASS_DOWN:
			return new ResourceList(WORLDRES.getWLand() * 0.9f, WORLDRES.getWPop(), WORLDRES.getWRes(), WORLDRES.getWTemp(), WORLDRES.getWGold());
		case LANDMASS_UP:
			return new ResourceList(WORLDRES.getWLand() * 1.1f, WORLDRES.getWPop(), WORLDRES.getWRes(), WORLDRES.getWTemp(), WORLDRES.getWGold());
		case POP_DOWN:
			return new ResourceList(WORLDRES.getWLand(), (long) (WORLDRES.getWPop() * 0.9f), WORLDRES.getWRes(), WORLDRES.getWTemp(), WORLDRES.getWGold());
		case POP_UP:
			return new ResourceList(WORLDRES.getWLand(), (long) (WORLDRES.getWPop() * 1.1f), WORLDRES.getWRes(), WORLDRES.getWTemp(), WORLDRES.getWGold());
		case TEMP_DOWN:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), WORLDRES.getWRes(), (int) (WORLDRES.getWTemp() * 0.9f), WORLDRES.getWGold());
		case TEMP_UP:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), WORLDRES.getWRes(), (int) (WORLDRES.getWTemp() * 1.1f), WORLDRES.getWGold());
		case WORLDHEALTH_DOWN:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), WORLDRES.getWRes(), WORLDRES.getWTemp(), (int) (WORLDRES.getWGold() * 0.9f));
		case WORLDHEALTH_UP:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), WORLDRES.getWRes(), WORLDRES.getWTemp(), (int) (WORLDRES.getWGold() * 1.1f));
		default:
			return new ResourceList(WORLDRES.getWLand(), WORLDRES.getWPop(), WORLDRES.getWRes(), WORLDRES.getWTemp(), WORLDRES.getWGold());
		
		}
	}
	
	public boolean Completed(ResourceList TARGET, ResourceList WORLD) {
		switch (this) {
		case GDP_DOWN:
			if(TARGET.getWRes()<WORLD.getWRes()) {
				return false;
			} else {
				return true;
			}
		case GDP_UP:
			if(TARGET.getWRes()<WORLD.getWRes()) {
				return true;
			} else {
				return false;
			}
		case LANDMASS_DOWN:
			if(TARGET.getWLand()<WORLD.getWLand()) {
				return false;
			} else {
				return true;
			}
		case LANDMASS_UP:
			if(TARGET.getWLand()<WORLD.getWLand()) {
				return true;
			} else {
				return false;
			}
		case POP_DOWN:
			if(TARGET.getWPop()<WORLD.getWPop()) {
				return false;
			} else {
				return true;
			}
		case POP_UP:
			if(TARGET.getWPop()<WORLD.getWPop()) {
				return true;
			} else {
				return false;
			}
		case TEMP_DOWN:
			if(TARGET.getWTemp()<WORLD.getWTemp()) {
				return false;
			} else {
				return true;
			}
		case TEMP_UP:
			if(TARGET.getWTemp()<WORLD.getWTemp()) {
				return true;
			} else {
				return false;
			}
		case WORLDHEALTH_DOWN:
			if(TARGET.getWGold()<WORLD.getWGold()) {
				return false;
			} else {
				return true;
			}
		case WORLDHEALTH_UP:
			if(TARGET.getWGold()<WORLD.getWGold()) {
				return true;
			} else {
				return false;
			}
		default:
			return false;

		}
	}
	
	public static Objective generateObjective(World W1, World W2) {
		Random r = new Random();
		return values()[r.nextInt(values().length)];
	}
}
