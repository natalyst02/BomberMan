package NguHuynhDe.entities.tile.powerup;

import NguHuynhDe.entities.tile.Tile;
import NguHuynhDe.display.SpriteInGame;

public abstract class Powerup extends Tile {

	protected int PUduration = -1; // -1 la vo han
	protected boolean beActive = false;
	protected int modeG;
	
	public Powerup(int x, int y, int level, SpriteInGame sprite) {
		super(x, y, sprite);
		modeG = level;
	}
	
	public abstract void setValues();
	
	public void removeLive() {
		if(PUduration > 0)
			PUduration--;
		
		if(PUduration == 0)
			beActive = false;
	}
	
	public int getDuration() {
		return PUduration;
	}
	
	public int getLevel() {
		return modeG;
	}

	public void setDuration(int duration) {
		this.PUduration = duration;
	}

	public boolean beActive() {
		return beActive;
	}

	public void setActive(boolean active) {
		this.beActive = active;
	}
}
