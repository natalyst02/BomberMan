package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AILow;
import NguHuynhDe.display.SpriteInGame;

public class Balloom extends Enemy {
	
	
	public Balloom(int x, int y, Board GameBoard) {
		super(x, y, GameBoard, SpriteInGame.deadBalloom, Game.getPlayerSpeed() / 2, 100);
		
		GameSprite = SpriteInGame.balloomMoveLeft1;
		
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.balloomMoveRight1, SpriteInGame.balloomMoveRight2, SpriteInGame.balloomMoveRight3, AnimationOfEnti, 60);
				break;
			case 2:
			case 3:
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.balloomMoveLeft1, SpriteInGame.balloomMoveLeft2, SpriteInGame.balloomMoveLeft3, AnimationOfEnti, 60);
				break;
		}
	}
}
