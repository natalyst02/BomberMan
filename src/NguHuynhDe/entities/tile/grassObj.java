package NguHuynhDe.entities.tile;


import NguHuynhDe.entities.Entity;
import NguHuynhDe.display.SpriteInGame;

public class grassObj extends TileObj {

	public grassObj(int x, int y, SpriteInGame sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		return true;
	}
}
