package NguHuynhDe.entities.bomb;

import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.MobileEnti;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;


public class Explosion extends Entity {

	protected boolean lastCheck = false;
	protected Board GameBoard;
	protected SpriteInGame GameSprite1, GameSprite2;
	
	public Explosion(int x, int y, int direction, boolean lastStatus, Board board) {
		_x = x;
		_y = y;
		lastCheck = lastStatus;
		GameBoard = board;
		
		switch (direction) {
			case 0:
				if(lastStatus == false) {
					GameSprite = SpriteInGame.explosion_vertical2;
				} else {
					GameSprite = SpriteInGame.explosion_vertical_toplastCheck2;
				}
			break;
			case 1:
				if(lastStatus == false) {
					GameSprite = SpriteInGame.explosion_horizontal2;
				} else {
					GameSprite = SpriteInGame.explosion_horizontal_rightlastCheck2;
				}
				break;
			case 2:
				if(lastStatus == false) {
					GameSprite = SpriteInGame.explosion_vertical2;
				} else {
					GameSprite = SpriteInGame.explosion_vertical_downlastCheck2;
				}
				break;
			case 3: 
				if(lastStatus == false) {
					GameSprite = SpriteInGame.explosion_horizontal2;
				} else {
					GameSprite = SpriteInGame.explosion_horizontal_leftlastCheck2;
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