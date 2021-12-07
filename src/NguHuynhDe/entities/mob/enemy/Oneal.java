package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AIMedium;
import NguHuynhDe.display.Sprite;

public class Oneal extends Enemy {
	
	public Oneal(int x, int y, Board boardgame) {
		super(x, y, boardgame, Sprite.oneal_dead, Game.getPlayerSpeed(), 200);
		
		GameSprite = Sprite.oneal_MoveLeft1;
		
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
					GameSprite = Sprite.movingSprite(Sprite.oneal_MoveRight1, Sprite.oneal_MoveRight2, Sprite.oneal_MoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.oneal_MoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = Sprite.movingSprite(Sprite.oneal_MoveLeft1, Sprite.oneal_MoveLeft2, Sprite.oneal_MoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = Sprite.oneal_MoveLeft1;
				break;
		}
	}
}
