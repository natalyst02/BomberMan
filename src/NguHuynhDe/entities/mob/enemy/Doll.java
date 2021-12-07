package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AILow;
import NguHuynhDe.display.Sprite;

public class Doll extends Enemy {
	
	
	public Doll(int x, int y, Board GameBoard) {
		super(x, y, GameBoard, Sprite.doll_dead, Game.getPlayerSpeed(), 400);
		
		GameSprite = Sprite.dollMoveRight1;
		
		_ai = new AILow();
		DirectionBomb = _ai.calculateDirection();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Dam dong sprite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void chooseSprite() {
		switch(DirectionBomb) {
			case 0:
			case 1:
				if(CheckMove)
					GameSprite = Sprite.movingSprite(Sprite.dollMoveRight1, Sprite.dollMoveRight2, Sprite.dollMoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.dollMoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = Sprite.movingSprite(Sprite.dollMoveLeft1, Sprite.dollMoveLeft2, Sprite.dollMoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.dollMoveLeft1;
				break;
		}
	}
}
