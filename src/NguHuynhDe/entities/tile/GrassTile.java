package NguHuynhDe.entities.tile;


import NguHuynhDe.entities.Entity;
import NguHuynhDe.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		return true;
	}
}
