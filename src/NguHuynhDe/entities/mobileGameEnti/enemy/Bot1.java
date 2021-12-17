package NguHuynhDe.entities.mobileGameEnti.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot.AILow;
import NguHuynhDe.display.SpriteInGame;

public class Bot1 extends Enemy {
	
	
	public Bot1(int x, int y, Board GameBoard) {
		super(x, y, GameBoard, SpriteInGame.deadbot1, Game.getPlayerSpeed() / 2, 100);
		
		GameSprite = SpriteInGame.bot1MoveLeft1;
		
		gameAI = new AILow();
		DirectionBomb = gameAI.CalcDirectionOfBot();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Dam Dong Spite
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void selectedSprites() {
		switch(DirectionBomb) {
			case 0:
			case 1:
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot1MoveRight1, SpriteInGame.bot1MoveRight2, SpriteInGame.bot1MoveRight3, AnimationOfEnti, 60);
				break;
			case 2:
			case 3:
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot1MoveLeft1, SpriteInGame.bot1MoveLeft2, SpriteInGame.bot1MoveLeft3, AnimationOfEnti, 60);
				break;
		}
	}
}
