package NguHuynhDe.entities.tile;

import NguHuynhDe.entities.Entity;
import NguHuynhDe.MapLv.Coordinates;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;

public abstract class Tile extends Entity {
	
	
	public Tile(int x, int y, SpriteInGame sprite) {
		_x = x;
		_y = y;
		GameSprite = sprite;
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		return false;
	}
	
	@Override
	public void render(ScreenInGame screen) {
		screen.renderEntity( Coordinates.tileToPixel(_x), Coordinates.tileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
