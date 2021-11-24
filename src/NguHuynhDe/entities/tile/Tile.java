package NguHuynhDe.entities.tile;

import NguHuynhDe.entities.Entity;
import NguHuynhDe.level.Coordinates;
import NguHuynhDe.graphics.Screen;
import NguHuynhDe.graphics.Sprite;

public abstract class Tile extends Entity {
	
	
	public Tile(int x, int y, Sprite sprite) {
		_x = x;
		_y = y;
		_sprite = sprite;
	}
	
	@Override
	public boolean collide(Entity e) {
		return false;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderEntity( Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
