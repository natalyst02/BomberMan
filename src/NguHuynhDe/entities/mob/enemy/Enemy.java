package NguHuynhDe.entities.mob.enemy;

import java.awt.Color;

import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.Notification;
import NguHuynhDe.entities.mob.Mob;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.MapLv.Coordinates;
import NguHuynhDe.entities.bomb.DirectionalExplosion;
import NguHuynhDe.entities.mob.enemy.ai.AI;
import NguHuynhDe.display.Screen;
import NguHuynhDe.display.Sprite;

public abstract class Enemy extends Mob {

	protected int ScoresGame;
	
	protected double EnemySpeed;
	protected AI _ai;

	protected final double StepsMax;
	protected final double TheRest;
	protected double GameSteps;
	
	protected int GameAnimation = 30;
	protected Sprite DeletedSprite;
	
	public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
		super(x, y, board);
		
		ScoresGame = points;
		EnemySpeed = speed;
		
		StepsMax = Game.TILES_SIZE / EnemySpeed;
		TheRest = (StepsMax - (int) StepsMax) / StepsMax;
		GameSteps = StepsMax;
		
		DelayEntiTiming = 20;
		DeletedSprite = dead;
	}
	
	/*
	|--------------------------------------------------------------------------
	| Render dam dong
	|--------------------------------------------------------------------------
	 */
	@Override
	public void update() {
		animate();
		
		if(CheckAlive == false) {
			afterKill();
			return;
		}
		
		if(CheckAlive)
			calculateMove();
	}
	
	@Override
	public void render(Screen screen) {
		
		if(CheckAlive)
			chooseSprite();
		else {
			if(DelayEntiTiming > 0) {
				GameSprite = DeletedSprite;
				AnimationOfEnti = 0;
			} else {
				GameSprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, AnimationOfEnti, 60);
			}
				
		}
			
		screen.renderEntity((int)_x, (int)_y - GameSprite.SIZE, this);
	}
	
	/*
	|--------------------------------------------------------------------------
	| dam dong di chuyen
	|--------------------------------------------------------------------------
	 */
	@Override
	public void calculateMove() {
		int xP = 0, yP = 0;
		if(GameSteps <= 0){
			DirectionBomb = _ai.calculateDirection();
			GameSteps = StepsMax;
		}
			
		if(DirectionBomb == 0) yP--; 
		if(DirectionBomb == 2) yP++;
		if(DirectionBomb == 3) xP--;
		if(DirectionBomb == 1) xP++;
		
		if(canMove(xP, yP)) {
			GameSteps -= 1 + TheRest;
			move(xP * EnemySpeed, yP * EnemySpeed);
			CheckMove = true;
		} else {
			GameSteps = 0;
			CheckMove = false;
		}
	}
	
	@Override
	public void move(double xP, double yP) {
		if(!CheckAlive) return;

			_y += yP;
			_x += xP;
	}
	
	@Override
	public boolean canMove(double x, double y) {
		
		double xMove = _x, yMove = _y - 16; //tru them y de them chinh xac
		
		//trừ 15 đến 16 (kích thước sprite), vì vậy nếu chúng tôi thêm 1 ô, chúng tôi sẽ nhận được ô pixel tiếp theo với
		//tránh rung lắc bên trong gạch với sự trợ giúp của các bước
		if(DirectionBomb == 0) { yMove += GameSprite.getSize() -1 ; xMove += GameSprite.getSize()/2; } 
		if(DirectionBomb == 1) {yMove += GameSprite.getSize()/2; xMove += 1;}
		if(DirectionBomb == 2) { xMove += GameSprite.getSize()/2; yMove += 1;}
		if(DirectionBomb == 3) { xMove += GameSprite.getSize() -1; yMove += GameSprite.getSize()/2;}
		
		int xx = Coordinates.pixelToTile(xMove) +(int)x;
		int yy = Coordinates.pixelToTile(yMove) +(int)y;
		
		Entity a = GameBoard.getEntity(xx, yy, this);
		
		return a.checkCollide(this);
	}
	
	/*
	|--------------------------------------------------------------------------
	| Check va cham va giet dam dong
	|--------------------------------------------------------------------------
	 */
	@Override
	public boolean checkCollide(Entity e) {
		if(e instanceof DirectionalExplosion) {
			kill();
			return false;
		}
		
		if(e instanceof Player) {
			((Player) e).kill();
			return false;
		}
		
		return true;
	}
	
	@Override
	public void kill() {
		if(CheckAlive == false) return;
		CheckAlive = false;
		
		GameBoard.addPoints(ScoresGame);

		Notification gamemess = new Notification("+" + ScoresGame, getMessX(), getMessY(), 2, Color.white, 14);
		GameBoard.addMessage(gamemess);
	}
	
	
	@Override
	protected void afterKill() {
		if(DelayEntiTiming > 0) --DelayEntiTiming;
		else {
			
			if(GameAnimation > 0) --GameAnimation;
			else
				remove();
		}
	}
	
	protected abstract void chooseSprite();
}
