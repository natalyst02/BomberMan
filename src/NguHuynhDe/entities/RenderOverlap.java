package NguHuynhDe.entities;

import java.util.LinkedList;

import NguHuynhDe.entities.tile.Demolished.otherObj;
import NguHuynhDe.display.ScreenInGame;

public class RenderOverlap extends Entity {
	
	protected LinkedList<Entity> EntiGameList = new LinkedList<Entity>();
	
	public RenderOverlap(int x, int y, Entity ... entities) {
		_x = x;
		_y = y;
		
		for (int i = 0; i < entities.length; i++) {
			EntiGameList.add(entities[i]); 
			
			if(i > 1) { //load hoat anh chong len
				if(entities[i] instanceof otherObj)
					((otherObj)entities[i]).addBelowSprite(entities[i-1].getSprite());
			}
		}
	}
	
	@Override
	public void update() {
		clearRemoved();
		getPriorEntity().update();
	}
	
	@Override
	public void render(ScreenInGame screen) {
		getPriorEntity().render(screen);
	}
	
	public Entity getPriorEntity() {
		
		return EntiGameList.getLast();
	}
	
	private void clearRemoved() {
		Entity top  = getPriorEntity();
		
		if(top.checkBeRemoved())  {
			EntiGameList.removeLast();
		}
	}
	
	public void addBeforePrior(Entity e) {
		EntiGameList.add(EntiGameList.size() - 1, e);
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		return getPriorEntity().checkCollide(e);
	}

}
