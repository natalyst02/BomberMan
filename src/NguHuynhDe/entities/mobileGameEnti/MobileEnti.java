package NguHuynhDe.entities.mobileGameEnti;

import NguHuynhDe.Game;
import NguHuynhDe.Board;
import NguHuynhDe.entities.AnimationInGame;
import NguHuynhDe.display.ScreenInGame;

public abstract class MobileEnti extends AnimationInGame {
	
	protected Board GameBoard;
	protected int DirectionBomb = -1;
	protected boolean CheckAlive = true;
	protected boolean CheckMove = false;
	public int DelayEntiTiming = 80;
	
	public MobileEnti(int x, int y, Board board) {
		_x = x;
		_y = y;
		GameBoard = board;
	}
	
	@Override
	public abstract void update();
	
	@Override
	public abstract void render(ScreenInGame screen);
	
	protected abstract void calcTransfer();
	
	protected abstract void move(double xa, double ya);
	
	public abstract void kill();
	
	protected abstract void afterwardKill();
	
	protected abstract boolean possibleTransfer(double x, double y);
	
	public boolean beAliveP() {
		return CheckAlive;
	}
	
	protected double getMessX() {
		return (_x * Game.SCALE) + (GameSprite.SIZE / 2 * Game.SCALE);
	}
	
	protected double getMessY() {
		return (_y* Game.SCALE) - (GameSprite.SIZE / 2 * Game.SCALE);
	}
	
}
