package NguHuynhDe.entities;

import NguHuynhDe.MapLv.LoadGameMap;
import NguHuynhDe.display.GeneralRender;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;

public abstract class Entity implements GeneralRender {

	protected double _x, _y;
	protected boolean _removed = false;
	protected SpriteInGame GameSprite;
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(ScreenInGame screen);
	
	public void remove() {
		_removed = true;
	}
	
	public boolean checkBeRemoved() {
		return _removed;
	}
	
	public SpriteInGame getSprite() {
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
		return LoadGameMap.changePixelToTile(_x + GameSprite.SIZE / 2); // tinh toan va cham
	}
	
	public int getTileY() {
		return LoadGameMap.changePixelToTile(_y - GameSprite.SIZE / 2); //tinh toan va cham
	}
}
