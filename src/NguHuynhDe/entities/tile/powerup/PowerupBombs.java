package NguHuynhDe.entities.tile.powerup;

import NguHuynhDe.Game;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.display.Sprite;

public class PowerupBombs extends Powerup {

	public PowerupBombs(int x, int y, int level, Sprite sprite) {
		super(x, y, level, sprite);
	}
	
	@Override
	public boolean collide(Entity e) {
		
		if(e instanceof Player) {
			((Player) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {
		_active = true;
		Game.addBombRate(1);
	}
	


}
