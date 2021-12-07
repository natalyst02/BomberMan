package NguHuynhDe.entities.bomb;

import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.AnimatedEntitiy;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Mob;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.MapLv.Coordinates;
import NguHuynhDe.display.Screen;
import NguHuynhDe.display.Sprite;
import NguHuynhDe.music.Audio;

public class Bomb extends AnimatedEntitiy {

	protected double ExPlodeTiming = 120; //2 giay
	public int DelayEntiTiming = 20; //bien mat
	
	protected Board GameBoard;
	protected boolean BePassed = true;
	protected DirectionalExplosion[] ExplosionDirections = null;
	protected boolean beExploded = false;

	protected Audio _audio = new Audio();
	public Bomb(int x, int y,Board board) {
		_x = x;
		_y = y;
		GameBoard = board;
		GameSprite = Sprite.bomb;
	}
	
	@Override
	public void update() {
		if(ExPlodeTiming > 0) 
			ExPlodeTiming--;
		else {
			if(!beExploded) 
				explosion();
			else
				updateExplosions();
			
			if(DelayEntiTiming > 0) 
				DelayEntiTiming--;
			else
				remove();
		}
			
		animate();
	}
	
	@Override
	public void render(Screen screen) {
		if(beExploded) {
			GameSprite =  Sprite.bombbeExploded2;
			renderExplosions(screen);
		} else
			GameSprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, AnimationOfEnti, 60);
		
		int xBomb = (int)_x << 4;
		int yBomb = (int)_y << 4;
		
		screen.renderEntity(xBomb, yBomb , this);
	}
	
	public void renderExplosions(Screen screen) {
		for (int i = 0; i < ExplosionDirections.length; i++) {
			ExplosionDirections[i].render(screen);
		}
	}
	
	public void updateExplosions() {
		for (int i = 0; i < ExplosionDirections.length; i++) {
			ExplosionDirections[i].update();
		}
	}
	
	public void explode() {
		ExPlodeTiming = 0;
	}
	
	protected void explosion() {
		BePassed = true;
		beExploded = true;
		
		Mob a = GameBoard.getMobAt(_x, _y);
		if(a != null)  {
			a.kill();
		}

		_audio.playSound("res/sounds/bomb.wav",0);
		ExplosionDirections = new DirectionalExplosion[4];
		
		for (int i = 0; i < ExplosionDirections.length; i++) {
			ExplosionDirections[i] = new DirectionalExplosion((int)_x, (int)_y, i, Game.getBombRadius(), GameBoard);
		}
	}
	
	public Explosion ExplosionPoint(int x, int y) {
		if(!beExploded) return null;
		
		for (int i = 0; i < ExplosionDirections.length; i++) {
			if(ExplosionDirections[i] == null) return null;
			Explosion e = ExplosionDirections[i].ExplosionPoint(x, y);
			if(e != null) return e;
		}
		
		return null;
	}
	
	public boolean isExploded() {
		return beExploded;
	}
	

	@Override
	public boolean checkCollide(Entity e) {
		
		if(e instanceof Player) {
			double DistanceX = e.getX() - Coordinates.tileToPixel(getX());
			double DistanceY = e.getY() - Coordinates.tileToPixel(getY());
			
			if(!(DistanceX >= -10 && DistanceX < 16 && DistanceY >= 1 && DistanceY <= 28)) {
				BePassed = false;
			}
			
			return BePassed;
		}
		
		if(e instanceof DirectionalExplosion) {
			explode();
			return true;
		}
		
		return false;
	}
}
