package com.nolevelcap.ld4830.drawables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.nolevelcap.data.TextureHandler;
import com.nolevelcap.drawable.Drawable;
import com.nolevelcap.ld4830.Objective;
import com.nolevelcap.ld4830.ObjectiveTracker;

public class ObjectiveDisplay extends Drawable{
	
	public ObjectiveTracker objective;
	private World W1, W2;

	public ObjectiveDisplay(TextureHandler DATA, Rectangle IMAGEBOUNDS, World W1, World W2) {
		super(DATA, IMAGEBOUNDS, false);
		this.W1 = W1;
		this.W2 = W2;
	}

	@Override
	public void load(TextureHandler DATA) {
		DATA.addFont("BIG ARIAL", Gdx.files.internal("Fonts/Arial_128.fnt"));
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(DATA.getTexture("ObjectiveDisplay"), IMAGEBOUNDS.x, IMAGEBOUNDS.y, IMAGEBOUNDS.width, IMAGEBOUNDS.height);
		DATA.getFont("BIG ARIAL").setColor(Color.BLACK);
		if(objective != null) {
		DATA.getFont("BIG ARIAL").draw(batch, objective.getTarget().objectiveText, IMAGEBOUNDS.x+6, IMAGEBOUNDS.y+DATA.getFont("BIG ARIAL").getBounds("100").height+30);
		}
		
	}
	
	public void checkCompleted() {
		if(objective != null) {
			if(objective.completed(W1, W2)){
				((GodDisplay) Parent.ScreenElements.get("godPoints")).amount+=objective.getTarget().reward;
				objective = new ObjectiveTracker(W1, W2, Objective.generateObjective(W1, W2));
				
			}
		} else {
			objective = new ObjectiveTracker(W1, W2, Objective.generateObjective(W1, W2));
		}
	}

	@Override
	public void onCollide(Drawable obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHover(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void whileHovered(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outHovered(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRelease(int mx, int my) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(int amount) {
		// TODO Auto-generated method stub
		
	}

}
