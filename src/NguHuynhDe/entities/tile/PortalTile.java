package NguHuynhDe.entities.tile;

import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.display.Sprite;

public class PortalTile extends Tile {

	protected Board GameBoard;
	
	public PortalTile(int x, int y, Board boardgame, Sprite sprite) {
		super(x, y, sprite);
		GameBoard = boardgame;
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof Player) {
			
			if(GameBoard.detectNoEnemies() == false)
				return false;
			
			if(e.getTileX() == getX() && e.getTileY() == getY()) {
				if(GameBoard.detectNoEnemies())
					GameBoard.nextLevel();
			}
			
			return true;
		}
		
		return false;
	}

}
