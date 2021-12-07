package NguHuynhDe.entities;

import NguHuynhDe.MapLv.Coordinates;
import NguHuynhDe.display.IRender;
import NguHuynhDe.display.Screen;
import NguHuynhDe.display.Sprite;

public abstract class Entity implements IRender {

	protected double _x, _y;
	protected boolean _removed = false;
	protected Sprite GameSprite;
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);
	
	public void remove() {
		_removed = true;
	}
	
	public boolean checkBeRemoved() {
		return _removed;
	}
	
	public Sprite getSprite() {
		return GameSprite;
	}
	
	public abstract boolean checkCollide(Entity e);
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	public int getTileX() {
		return Coordinates.pixelToTile(_x + GameSprite.SIZE / 2); // tinh toan va cham
	}
	
	public int getTileY() {
		return Coordinates.pixelToTile(_y - GameSprite.SIZE / 2); //tinh toan va cham
	}
}
