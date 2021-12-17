package NguHuynhDe.entities.mobileGameEnti.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot.AIMedium;
import NguHuynhDe.display.SpriteInGame;

public class Bot5 extends Enemy {
	
	public Bot5(int x, int y, Board boardgame) {
		super(x, y, boardgame, SpriteInGame.bot5dead, Game.getPlayerSpeed(), 200);
		
		GameSprite = SpriteInGame.bot5MoveLeft1;
		
		gameAI = new AIMedium(GameBoard.getPlayer(), this);
		DirectionBomb  = gameAI.CalcDirectionOfBot();
	}
	
	/*
	|--------------------------------------------------------------------------
	| Sprite dam dong
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void selectedSprites() {
		switch(DirectionBomb) {
			case 0:
			case 1:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot5MoveRight1, SpriteInGame.bot5MoveRight2, SpriteInGame.bot5MoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot5MoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot5MoveLeft1, SpriteInGame.bot5MoveLeft2, SpriteInGame.bot5MoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot5MoveLeft1;
				break;
		}
	}
}
