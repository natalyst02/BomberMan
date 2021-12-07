package NguHuynhDe.entities.mob.enemy.ai;

import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.entities.mob.enemy.Enemy;

public class AIMedium extends AI {
	Player _player;
	Enemy _e;
	
	public AIMedium(Player player, Enemy e) {
		_player = player;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		
		if(_player == null)
			return random.nextInt(4);
		
		int vertical = random.nextInt(2);
		
		if(vertical == 1) {
			int v = calculateRowDirection();
			if(v != -1)
				return v;
			else
				return calculateColDirection();
			
		} else {
			int h = calculateColDirection();
			
			if(h != -1)
				return h;
			else
				return calculateRowDirection();
		}
		
	}
	
	protected int calculateColDirection() {
		if(_player.getTileX() < _e.getTileX())
			return 3;
		else if(_player.getTileX() > _e.getTileX())
			return 1;
		
		return -1;
	}
	
	protected int calculateRowDirection() {
		if(_player.getTileY() < _e.getTileY())
			return 0;
		else if(_player.getTileY() > _e.getTileY())
			return 2;
		return -1;
	}
	

}
