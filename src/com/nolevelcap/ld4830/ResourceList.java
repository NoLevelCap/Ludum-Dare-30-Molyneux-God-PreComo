package com.nolevelcap.ld4830;

import com.badlogic.gdx.Gdx;

public class ResourceList {
	private float WLand;
	private long WPop, WRes;
	private int WTemp;
	private int WGold;
	
	public ResourceList(float Land, long Pop, long Res, int Temp, int Gold) {
		this.WGold = Gold;
		this.WLand = Land;
		this.WPop = Pop;
		this.WRes = Res;
		this.WTemp = Temp;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ResourceList) {
			ResourceList res = (ResourceList) obj;
			if(res.WGold == WGold && res.WLand == WLand && res.WPop == WPop && res.WRes == WRes && res.WTemp == WTemp) {
				return true;
			} else {
				return false;
			}
		} else {
			return super.equals(obj);
		}
	}
	
	public boolean biggerThan(Object obj) {
		if(obj instanceof ResourceList) {
			ResourceList res = (ResourceList) obj;
			if(res.WGold <= WGold && res.WLand <= WLand && res.WPop <= WPop && res.WRes <= WRes && res.WTemp <= WTemp) {
				return true;
			} else {
				Gdx.app.log("GETTING TO ", " THIS POINT" + (res.WLand <= WLand));
				return false;
			}
		} else { 
			return false;
		}
	}



	public float getWLand() {
		return WLand;
	}

	public void setWLand(float wLand) {
		WLand = wLand;
	}

	public long getWPop() {
		return WPop;
	}

	public void setWPop(long wPop) {
		WPop = wPop;
	}

	public long getWRes() {
		return WRes;
	}

	public void setWRes(long wRes) {
		WRes = wRes;
	}

	public int getWTemp() {
		return WTemp;
	}

	public void setWTemp(int wTemp) {
		WTemp = wTemp;
	}

	public int getWGold() {
		return WGold;
	}

	public void setWGold(int wGold) {
		WGold = wGold;
	}
	
	
}
