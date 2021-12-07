package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AIMedium;
import NguHuynhDe.display.Sprite;

public class Kondoria extends Enemy {
	
	
	public Kondoria(int x, int y, Board boardgame) {
		super(x, y, boardgame, Sprite.kondoria_dead, Game.getPlayerSpeed() / 4, 1000);
		
		GameSprite = Sprite.kondoriaMoveRight1;
		
		_ai = new AIMedium(GameBoard.getPlayer(), this);
		DirectionBomb  = _ai.calculateDirection();
	}
	/*
	|--------------------------------------------------------------------------
	| Sprite dam dong
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void chooseSprite() {
		switch(DirectionBomb) {
			case 0:
			case 1:
				if(CheckMove)
					GameSprite = Sprite.movingSprite(Sprite.kondoriaMoveRight1, Sprite.kondoriaMoveRight2, Sprite.kondoriaMoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.kondoriaMoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = Sprite.movingSprite(Sprite.kondoriaMoveLeft1, Sprite.kondoriaMoveLeft2, Sprite.kondoriaMoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.kondoriaMoveLeft1;
				break;
		}
	}
}
