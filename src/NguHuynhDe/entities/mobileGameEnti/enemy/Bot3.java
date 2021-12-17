package NguHuynhDe.entities.mobileGameEnti.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot.AIMedium;
import NguHuynhDe.display.SpriteInGame;

public class Bot3 extends Enemy {
	
	
	public Bot3(int x, int y, Board boardgame) {
		super(x, y, boardgame, SpriteInGame.bot3_dead, Game.getPlayerSpeed() / 4, 1000);
		
		GameSprite = SpriteInGame.bot3MoveRight1;
		
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot3MoveRight1, SpriteInGame.bot3MoveRight2, SpriteInGame.bot3MoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot3MoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.bot3MoveLeft1, SpriteInGame.bot3MoveLeft2, SpriteInGame.bot3MoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.bot3MoveLeft1;
				break;
		}
	}
}
