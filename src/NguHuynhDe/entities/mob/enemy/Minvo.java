package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AIMedium;
import NguHuynhDe.display.Sprite;

public class Minvo extends Enemy {
	
	
	public Minvo(int x, int y, Board boardgame) {
		super(x, y, boardgame, Sprite.minvo_dead, Game.getPlayerSpeed() * 2, 800);
		
		GameSprite = Sprite.minvoMoveRight1;
		
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
					GameSprite = Sprite.movingSprite(Sprite.minvoMoveRight1, Sprite.minvoMoveRight2, Sprite.minvoMoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.minvoMoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = Sprite.movingSprite(Sprite.minvoMoveLeft1, Sprite.minvoMoveLeft2, Sprite.minvoMoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.minvoMoveLeft1;
				break;
		}
	}
}
