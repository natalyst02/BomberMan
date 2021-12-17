package NguHuynhDe.entities.tile;

import NguHuynhDe.entities.Entity;
import NguHuynhDe.MapLv.LoadGameMap;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;

public abstract class TileObj extends Entity {
	
	
	public TileObj(int x, int y, SpriteInGame sprite) {
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
		screen.renderEntity( LoadGameMap.changeTileToPixel(_x), LoadGameMap.changeTileToPixel(_y), this);
	}
	
	@Override
	public void update() {}
}
