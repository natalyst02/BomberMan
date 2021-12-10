package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AIMedium;
import NguHuynhDe.display.SpriteInGame;

public class Minvo extends Enemy {
	
	
	public Minvo(int x, int y, Board boardgame) {
		super(x, y, boardgame, SpriteInGame.minvo_dead, Game.getPlayerSpeed() * 2, 800);
		
		GameSprite = SpriteInGame.minvoMoveRight1;
		
		gameAI = new AIMedium(GameBoard.getPlayer(), this);
		DirectionBomb  = gameAI.calculateDirection();
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.minvoMoveRight1, SpriteInGame.minvoMoveRight2, SpriteInGame.minvoMoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.minvoMoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.minvoMoveLeft1, SpriteInGame.minvoMoveLeft2, SpriteInGame.minvoMoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.minvoMoveLeft1;
				break;
		}
	}
}
