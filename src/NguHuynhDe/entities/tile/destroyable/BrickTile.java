package NguHuynhDe.entities.tile.destroyable;


import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.bomb.DirectionalExplosion;
import NguHuynhDe.entities.mob.enemy.Kondoria;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.MapLv.Coordinates;
import NguHuynhDe.display.SpriteInGame;

public class BrickTile extends DestroyableTile {
	
	public BrickTile(int x, int y, SpriteInGame sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(ScreenInGame screen) {
		int x = Coordinates.tileToPixel(_x);
		int y = Coordinates.tileToPixel(_y);
		
		if(beDemolished) {
			GameSprite = movingSprite(SpriteInGame.BeExplodedBrick, SpriteInGame.BeExplodedBrick1, SpriteInGame.BeExplodedBrick2);
			
			screen.RenderEntitiesWithSpriteBelow(x, y, this, SpriteBelowP);
		}
		else
			screen.renderEntity( x, y, this);
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof DirectionalExplosion)
			destroy();
		
		if(e instanceof Kondoria)
			return true;
			
		return false;
	}
	
	
}
