package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AILow;
import NguHuynhDe.display.Sprite;

public class Balloom extends Enemy {
	
	
	public Balloom(int x, int y, Board GameBoard) {
		super(x, y, GameBoard, Sprite.deadBalloom, Game.getPlayerSpeed() / 2, 100);
		
		GameSprite = Sprite.balloomMoveLeft1;
		
		_ai = new AILow();
		DirectionBomb = _ai.calculateDirection();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Dam Dong Spite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void chooseSprite() {
		switch(DirectionBomb) {
			case 0:
			case 1:
					GameSprite = Sprite.movingSprite(Sprite.balloomMoveRight1, Sprite.balloomMoveRight2, Sprite.balloomMoveRight3, AnimationOfEnti, 60);
				break;
			case 2:
			case 3:
					GameSprite = Sprite.movingSprite(Sprite.balloomMoveLeft1, Sprite.balloomMoveLeft2, Sprite.balloomMoveLeft3, AnimationOfEnti, 60);
				break;
		}
	}
}
