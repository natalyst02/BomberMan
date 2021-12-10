package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AIMedium;
import NguHuynhDe.display.SpriteInGame;

public class Oneal extends Enemy {
	
	public Oneal(int x, int y, Board boardgame) {
		super(x, y, boardgame, SpriteInGame.oneal_dead, Game.getPlayerSpeed(), 200);
		
		GameSprite = SpriteInGame.oneal_MoveLeft1;
		
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.oneal_MoveRight1, SpriteInGame.oneal_MoveRight2, SpriteInGame.oneal_MoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.oneal_MoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.oneal_MoveLeft1, SpriteInGame.oneal_MoveLeft2, SpriteInGame.oneal_MoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.oneal_MoveLeft1;
				break;
		}
	}
}
