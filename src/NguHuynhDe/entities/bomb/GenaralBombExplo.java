package NguHuynhDe.entities.bomb;

import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.AnimationInGame;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mobileGameEnti.MobileEnti;
import NguHuynhDe.entities.mobileGameEnti.Player;
import NguHuynhDe.MapLv.LoadGameMap;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;
import NguHuynhDe.music.Audio;

public class GenaralBombExplo extends AnimationInGame {

	protected double ExPlodeTiming = 120; //2 giay
	public int DelayEntiTiming = 20; //bien mat
	
	protected Board GameBoard;
	protected boolean BePassed = true;
	protected HandleDirectionBomb[] ExplosionDirections = null;
	protected boolean beExploded = false;

	protected Audio musicGame = new Audio();
	public GenaralBombExplo(int x, int y,Board board) {
		_x = x;
		_y = y;
		GameBoard = board;
		GameSprite = SpriteInGame.bomb;
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
	public void render(ScreenInGame screen) {
		if(beExploded) {
			GameSprite =  SpriteInGame.bombbeExploded2;
			renderExplosions(screen);
		} else
			GameSprite = SpriteInGame.movingSprite(SpriteInGame.bomb, SpriteInGame.bomb1, SpriteInGame.bomb2, AnimationOfEnti, 60);
		
		int xBomb = (int)_x << 4;
		int yBomb = (int)_y << 4;
		
		screen.renderEntity(xBomb, yBomb , this);
	}
	protected void explosion() {
		BePassed = true;
		beExploded = true;

		MobileEnti a = GameBoard.getMobAt(_x, _y);
		if(a != null)  {
			a.kill();
		}

		musicGame.playSound("res/sounds/bomb.wav",0);
		ExplosionDirections = new HandleDirectionBomb[4];

		for (int i = 0; i < ExplosionDirections.length; i++) {
			ExplosionDirections[i] = new HandleDirectionBomb((int)_x, (int)_y, i, Game.getBombRadius(), GameBoard);
		}
	}
	public void renderExplosions(ScreenInGame screen) {
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

	
	public HandleTypeExplode ExplosionPoint(int x, int y) {
		if(!beExploded) return null;
		
		for (int i = 0; i < ExplosionDirections.length; i++) {
			if(ExplosionDirections[i] == null) return null;
			HandleTypeExplode e = ExplosionDirections[i].ExplosionPoint(x, y);
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
			double DistanceX = e.getX() - LoadGameMap.changeTileToPixel(getX());
			double DistanceY = e.getY() - LoadGameMap.changeTileToPixel(getY());
			
			if(!(DistanceX >= -10 && DistanceX < 16 && DistanceY >= 1 && DistanceY <= 28)) {
				BePassed = false;
			}
			
			return BePassed;
		}
		
		if(e instanceof HandleDirectionBomb) {
			explode();
			return true;
		}
		
		return false;
	}
}
