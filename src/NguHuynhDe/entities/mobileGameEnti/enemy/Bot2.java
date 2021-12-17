package NguHuynhDe.entities.mobileGameEnti.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot.AILow;
import NguHuynhDe.display.SpriteInGame;

public class Bot2 extends Enemy {
	
	
	public Bot2(int x, int y, Board GameBoard) {
		super(x, y, GameBoard, SpriteInGame.bot2_dead, Game.getPlayerSpeed(), 400);
		
		GameSprite = SpriteInGame.bot2MoveRight1;
		
		gameAI = new AILow();
		DirectionBomb = gameAI.CalcDirectionOfBot();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Dam dong sprite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void selectedSprites() {
		switch(DirectionBomb) {
			case 0:
			case 1:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot2MoveRight1, SpriteInGame.bot2MoveRight2, SpriteInGame.bot2MoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot2MoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot2MoveLeft1, SpriteInGame.bot2MoveLeft2, SpriteInGame.bot2MoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot2MoveLeft1;
				break;
		}
	}
}
