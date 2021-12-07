package NguHuynhDe.entities.mob;

import NguHuynhDe.Game;
import NguHuynhDe.Board;
import NguHuynhDe.entities.AnimatedEntitiy;
import NguHuynhDe.display.Screen;

public abstract class Mob extends AnimatedEntitiy {
	
	protected Board GameBoard;
	protected int DirectionBomb = -1;
	protected boolean CheckAlive = true;
	protected boolean CheckMove = false;
	public int DelayEntiTiming = 80;
	
	public Mob(int x, int y, Board board) {
		_x = x;
		_y = y;
		GameBoard = board;
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(Screen screen);
	
	protected abstract void calculateMove();
	
	protected abstract void move(double xa, double ya);
	
	public abstract void kill();
	
	protected abstract void afterKill();
	
	protected abstract boolean canMove(double x, double y);
	
	public boolean beAliveP() {
		return CheckAlive;
	}
	
	public boolean beMovingP() {
		return CheckMove;
	}
	
	public int getDirection() {
		return DirectionBomb;
	}
	
	protected double getMessX() {
		return (_x * Game.SCALE) + (GameSprite.SIZE / 2 * Game.SCALE);
	}
	
	protected double getMessY() {
		return (_y* Game.SCALE) - (GameSprite.SIZE / 2 * Game.SCALE);
	}
	
}
