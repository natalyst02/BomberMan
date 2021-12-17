package NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot;

import NguHuynhDe.entities.mobileGameEnti.Player;
import NguHuynhDe.entities.mobileGameEnti.enemy.Enemy;

public class AIMedium extends AI {
	Player Gplayer;
	Enemy GEnemies;
	
	public AIMedium(Player player, Enemy e) {
		Gplayer = player;
		GEnemies = e;
	}

	@Override
	public int CalcDirectionOfBot() {
		
		if(Gplayer == null)
			return random.nextInt(4);
		
		int randomVertic = random.nextInt(2);
		
		if(randomVertic == 1) {
			int v = CalcRowDirect();
			if(v != -1)
				return v;
			else
				return CalcColDirect();
			
		} else {
			int h = CalcColDirect();
			
			if(h != -1)
				return h;
			else
				return CalcRowDirect();
		}
		
	}
	
	protected int CalcColDirect() {
		if(Gplayer.getTileX() < GEnemies.getTileX())
			return 3;
		else if(Gplayer.getTileX() > GEnemies.getTileX())
			return 1;
		
		return -1;
	}
	
	protected int CalcRowDirect() {
		if(Gplayer.getTileY() < GEnemies.getTileY())
			return 0;
		else if(Gplayer.getTileY() > GEnemies.getTileY())
			return 2;
		return -1;
	}
	

}
