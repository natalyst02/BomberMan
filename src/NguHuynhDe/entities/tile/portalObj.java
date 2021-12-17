package NguHuynhDe.entities.tile;

import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mobileGameEnti.Player;
import NguHuynhDe.display.SpriteInGame;

public class portalObj extends TileObj {

	protected Board GameBoard;
	
	public portalObj(int x, int y, Board boardgame, SpriteInGame sprite) {
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
