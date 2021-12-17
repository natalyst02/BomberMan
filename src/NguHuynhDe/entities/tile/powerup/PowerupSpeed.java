package NguHuynhDe.entities.tile.powerup;

import NguHuynhDe.Game;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mobileGameEnti.Player;
import NguHuynhDe.display.SpriteInGame;

public class PowerupSpeed extends Powerup {

	public PowerupSpeed(int x, int y, int level, SpriteInGame sprite) {
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
		Game.addPlayerSpeed(0.1);
	}
	


}
