package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AILow;
import NguHuynhDe.display.SpriteInGame;

public class Doll extends Enemy {
	
	
	public Doll(int x, int y, Board GameBoard) {
		super(x, y, GameBoard, SpriteInGame.doll_dead, Game.getPlayerSpeed(), 400);
		
		GameSprite = SpriteInGame.dollMoveRight1;
		
		gameAI = new AILow();
		DirectionBomb = gameAI.calculateDirection();
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.dollMoveRight1, SpriteInGame.dollMoveRight2, SpriteInGame.dollMoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.dollMoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.dollMoveLeft1, SpriteInGame.dollMoveLeft2, SpriteInGame.dollMoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.dollMoveLeft1;
				break;
		}
	}
}
