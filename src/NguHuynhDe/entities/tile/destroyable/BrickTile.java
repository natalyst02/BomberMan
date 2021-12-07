package NguHuynhDe.entities.tile.destroyable;


import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.bomb.DirectionalExplosion;
import NguHuynhDe.entities.mob.enemy.Kondoria;
import NguHuynhDe.display.Screen;
import NguHuynhDe.MapLv.Coordinates;
import NguHuynhDe.display.Sprite;

public class BrickTile extends DestroyableTile {
	
	public BrickTile(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		int x = Coordinates.tileToPixel(_x);
		int y = Coordinates.tileToPixel(_y);
		
		if(beDemolished) {
			GameSprite = movingSprite(Sprite.BeExplodedBrick, Sprite.BeExplodedBrick1, Sprite.BeExplodedBrick2);
			
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
