package NguHuynhDe.entities.bomb;

import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mobileGameEnti.MobileEnti;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;


public class HandleTypeExplode extends Entity {

	protected boolean lastCheck = false;
	protected Board GameBoard;
	
	public HandleTypeExplode(int x, int y, int direction, boolean lastStatus, Board board) {
		_x = x;
		_y = y;
		lastCheck = lastStatus;
		GameBoard = board;
		
		switch (direction) {
			case 0:
				if(lastStatus == false) {
					GameSprite = SpriteInGame.verticalExplo2;
				} else {
					GameSprite = SpriteInGame.verticalExplo_toplastCheck2;
				}
			break;
			case 1:
				if(lastStatus == false) {
					GameSprite = SpriteInGame.HorizonalExplo2;
				} else {
					GameSprite = SpriteInGame.HorizonalExplo_rightlastCheck2;
				}
				break;
			case 2:
				if(lastStatus == false) {
					GameSprite = SpriteInGame.verticalExplo2;
				} else {
					GameSprite = SpriteInGame.verticalExplo_downlastCheck2;
				}
				break;
			case 3: 
				if(lastStatus == false) {
					GameSprite = SpriteInGame.HorizonalExplo2;
				} else {
					GameSprite = SpriteInGame.HorizonalExplo_leftlastCheck2;
				}
				break;
		}
	}
	
	@Override
	public void render(ScreenInGame screen) {
		int xP = (int)_x << 4;
		int yP = (int)_y << 4;
		
		screen.renderEntity(xP, yP , this);
	}
	
	@Override
	public void update() {}

	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof MobileEnti) {
			((MobileEnti)e).kill();
		}
		
		return true;
	}
	

}