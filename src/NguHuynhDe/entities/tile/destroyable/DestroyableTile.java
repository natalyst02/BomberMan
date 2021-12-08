package NguHuynhDe.entities.tile.destroyable;

import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.bomb.DirectionalExplosion;
import NguHuynhDe.entities.tile.Tile;
import NguHuynhDe.display.SpriteInGame;

public class DestroyableTile extends Tile {

	private final int AnimationMax = 7500; 
	private int AnimationOfEnti = 0;
	protected boolean beDemolished = false;
	protected int DisppearTime = 20;
	protected SpriteInGame SpriteBelowP = SpriteInGame.grass;
	
	public DestroyableTile(int x, int y, SpriteInGame sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		if(beDemolished) {
			if(AnimationOfEnti < AnimationMax) AnimationOfEnti++; else AnimationOfEnti = 0; //reset animation
			if(DisppearTime > 0) 
				DisppearTime--;
			else
				remove();
		}
	}

	public boolean beDestroyed() {
		return beDemolished;
	}
	
	public void destroy() {
		beDemolished = true;
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof DirectionalExplosion)
			destroy();
			
		return false;
	}
	
	public void addBelowSprite(SpriteInGame sprite) {
		SpriteBelowP = sprite;
	}
	
	protected SpriteInGame movingSprite(SpriteInGame normal, SpriteInGame x1, SpriteInGame x2) {
		int CalcDestroy = AnimationOfEnti % 30;
		
		if(CalcDestroy < 10) {
			return normal;
		}
			
		if(CalcDestroy < 20) {
			return x1;
		}
			
		return x2;
	}
	
}
