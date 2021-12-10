package NguHuynhDe.entities.mob.enemy;


import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.mob.enemy.ai.AIMedium;
import NguHuynhDe.display.SpriteInGame;

public class Kondoria extends Enemy {
	
	
	public Kondoria(int x, int y, Board boardgame) {
		super(x, y, boardgame, SpriteInGame.kondoria_dead, Game.getPlayerSpeed() / 4, 1000);
		
		GameSprite = SpriteInGame.kondoriaMoveRight1;
		
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
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.kondoriaMoveRight1, SpriteInGame.kondoriaMoveRight2, SpriteInGame.kondoriaMoveRight3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.kondoriaMoveLeft1;
				break;
			case 2:
			case 3:
				if(CheckMove)
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.kondoriaMoveLeft1, SpriteInGame.kondoriaMoveLeft2, SpriteInGame.kondoriaMoveLeft3, AnimationOfEnti, 60);
				else
					GameSprite = SpriteInGame.kondoriaMoveLeft1;
				break;
		}
	}
}
