package NguHuynhDe.entities.bomb;

import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Mob;
import NguHuynhDe.display.Screen;

public class DirectionalExplosion extends Entity {

	protected Board GameBoard;
	protected int DirectionBomb;
	private int RadiusBomb;
	protected int xBegin, yBegin;
	protected Explosion[] ExplosionDirections;
	
	public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
		xBegin = x;
		yBegin = y;
		_x = x;
		_y = y;
		DirectionBomb = direction;
		RadiusBomb = radius;
		GameBoard = board;
		
		ExplosionDirections = new Explosion[ calcDisPermited() ];
		setExploGame();
		}
	
	private void setExploGame() {
		boolean checkLast = false;
		
		int xP = (int)_x;
		int yP = (int)_y;
		for (int i = 0; i < ExplosionDirections.length; i++) {
			checkLast = i == ExplosionDirections.length -1 ? true : false;
			
			switch (DirectionBomb) {
				case 0: yP--; break;
				case 1: xP++; break;
				case 2: yP++; break;
				case 3: xP--; break;
			}
			ExplosionDirections[i] = new Explosion(xP, yP, DirectionBomb, checkLast, GameBoard);
		}
	}
	
	private int calcDisPermited() {
		int radiusBo = 0;
		int x = (int)_x;
		int y = (int)_y;
		while(radiusBo < RadiusBomb) {
			if(DirectionBomb == 0) y--;
			if(DirectionBomb == 1) x++;
			if(DirectionBomb == 2) y++;
			if(DirectionBomb == 3) x--;
			
			Entity a = GameBoard.getEntity(x, y, null);
			
			if(a instanceof Mob) ++radiusBo; // vu no tac dong xung quanh
			
			if(a.checkCollide(this) == false)
				break;
			
			++radiusBo;
		}
		return radiusBo;
	}
	
	public Explosion ExplosionPoint(int x, int y) {
		for (int i = 0; i < ExplosionDirections.length; i++) {
			if(ExplosionDirections[i].getX() == x && ExplosionDirections[i].getY() == y) 
				return ExplosionDirections[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		
		for (int i = 0; i < ExplosionDirections.length; i++) {
			ExplosionDirections[i].render(screen);
		}
	}

	@Override
	public boolean checkCollide(Entity e) {
		return true;
	}
}
