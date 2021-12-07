package NguHuynhDe.entities;

import java.util.LinkedList;

import NguHuynhDe.entities.tile.destroyable.DestroyableTile;
import NguHuynhDe.display.Screen;

public class LayeredEntity extends Entity {
	
	protected LinkedList<Entity> EntiGameList = new LinkedList<Entity>();
	
	public LayeredEntity(int x, int y, Entity ... entities) {
		_x = x;
		_y = y;
		
		for (int i = 0; i < entities.length; i++) {
			EntiGameList.add(entities[i]); 
			
			if(i > 1) { //load hoat anh chong len
				if(entities[i] instanceof DestroyableTile)
					((DestroyableTile)entities[i]).addBelowSprite(entities[i-1].getSprite());
			}
		}
	}
	
	@Override
	public void update() {
		clearRemoved();
		getTopEntity().update();
	}
	
	@Override
	public void render(Screen screen) {
		getTopEntity().render(screen);
	}
	
	public Entity getTopEntity() {
		
		return EntiGameList.getLast();
	}
	
	private void clearRemoved() {
		Entity top  = getTopEntity();
		
		if(top.checkBeRemoved())  {
			EntiGameList.removeLast();
		}
	}
	
	public void addBeforeTop(Entity e) {
		EntiGameList.add(EntiGameList.size() - 1, e);
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		return getTopEntity().checkCollide(e);
	}

}
