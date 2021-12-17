package NguHuynhDe.entities.tile.Demolished;


import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.bomb.HandleDirectionBomb;
import NguHuynhDe.entities.mobileGameEnti.enemy.Bot3;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.MapLv.LoadGameMap;
import NguHuynhDe.display.SpriteInGame;

public class brickObj extends otherObj {
	
	public brickObj(int x, int y, SpriteInGame sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(ScreenInGame screen) {
		int x = LoadGameMap.changeTileToPixel(_x);
		int y = LoadGameMap.changeTileToPixel(_y);
		
		if(beDemolished) {
			GameSprite = movingSprite(SpriteInGame.BeExplodedBrick, SpriteInGame.BeExplodedBrick1, SpriteInGame.BeExplodedBrick2);
			
			screen.RenderEntitiesWithSpriteBelow(x, y, this, SpriteBelowP);
		}
		else
			screen.renderEntity( x, y, this);
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof HandleDirectionBomb)
			destroy();
		
		if(e instanceof Bot3)
			return true;
			
		return false;
	}
	
	
}
