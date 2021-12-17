package NguHuynhDe.entities.mobileGameEnti.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot.AIMedium;
import NguHuynhDe.display.SpriteInGame;

public class Bot4 extends Enemy {
	
	
	public Bot4(int x, int y, Board boardgame) {
		super(x, y, boardgame, SpriteInGame.bot4_dead, Game.getPlayerSpeed() * 2, 800);
		
		GameSprite = SpriteInGame.bot4MoveRight1;
		
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot4MoveRight1, SpriteInGame.bot4MoveRight2, SpriteInGame.bot4MoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot4MoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot4MoveLeft1, SpriteInGame.bot4MoveLeft2, SpriteInGame.bot4MoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot4MoveLeft1;
				break;
		}
	}
}
