package NguHuynhDe.entities.tile.powerup;

import NguHuynhDe.Game;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.display.SpriteInGame;

public class PowerupBombs extends Powerup {

	public PowerupBombs(int x, int y, int level, SpriteInGame sprite) {
		super(x, y, level, sprite);
	}
	
	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof Player) {
			((Player) e).addPowerup(this);
			remove();
			return true;
		}
		
		return false;
	}
	
	@Override
	public void setValues() {
		beActive = true;
		Game.addBombRate(1);
	}
	


}
