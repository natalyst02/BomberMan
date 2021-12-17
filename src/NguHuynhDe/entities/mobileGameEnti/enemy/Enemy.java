package NguHuynhDe.entities.mobileGameEnti.enemy;

import java.awt.Color;

import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.Notification;
import NguHuynhDe.entities.mobileGameEnti.MobileEnti;
import NguHuynhDe.entities.mobileGameEnti.Player;
import NguHuynhDe.MapLv.LoadGameMap;
import NguHuynhDe.entities.bomb.HandleDirectionBomb;
import NguHuynhDe.entities.mobileGameEnti.enemy.RandomAIBot.AI;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;

public abstract class Enemy extends MobileEnti {

	protected int ScoresGame;
	
	protected double EnemySpeed;
	protected AI gameAI;

	protected final double StepsMax;
	protected final double TheRest;
	protected double GameSteps;
	
	protected int GameAnimation = 30;
	protected SpriteInGame DeletedSprite;
	
	public Enemy(int x, int y, Board board, SpriteInGame dead, double speed, int points) {
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
			afterwardKill();
			return;
		}
		
		if(CheckAlive)
			calcTransfer();
	}
	
	@Override
	public void render(ScreenInGame screen) {
		
		if(CheckAlive)
			selectedSprites();
		else {
			if(DelayEntiTiming > 0) {
				GameSprite = DeletedSprite;
				AnimationOfEnti = 0;
			} else {
				GameSprite = SpriteInGame.movingSprite(SpriteInGame.deadBot1, SpriteInGame.deadBot2, SpriteInGame.deadBot3, AnimationOfEnti, 60);
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
	public void calcTransfer() {
		int xP = 0, yP = 0;
		if(GameSteps <= 0){
			DirectionBomb = gameAI.CalcDirectionOfBot();
			GameSteps = StepsMax;
		}
			
		if(DirectionBomb == 0) yP--; 
		if(DirectionBomb == 2) yP++;
		if(DirectionBomb == 3) xP--;
		if(DirectionBomb == 1) xP++;
		
		if(possibleTransfer(xP, yP)) {
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
	public boolean possibleTransfer(double x, double y) {
		
		double xMove = _x, yMove = _y - 16; //tru them y de them chinh xac
		
		//trừ 15 đến 16 (kích thước sprite), vì vậy nếu chúng tôi thêm 1 ô, chúng tôi sẽ nhận được ô pixel tiếp theo với
		//tránh rung lắc bên trong gạch với sự trợ giúp của các bước
		if(DirectionBomb == 0) { yMove += GameSprite.getSize() -1 ; xMove += GameSprite.getSize()/2; } 
		if(DirectionBomb == 1) {yMove += GameSprite.getSize()/2; xMove += 1;}
		if(DirectionBomb == 2) { xMove += GameSprite.getSize()/2; yMove += 1;}
		if(DirectionBomb == 3) { xMove += GameSprite.getSize() -1; yMove += GameSprite.getSize()/2;}
		
		int xx = LoadGameMap.changePixelToTile(xMove) +(int)x;
		int yy = LoadGameMap.changePixelToTile(yMove) +(int)y;
		
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
		if(e instanceof HandleDirectionBomb) {
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
	protected void afterwardKill() {
		if(DelayEntiTiming > 0) --DelayEntiTiming;
		else {
			
			if(GameAnimation > 0) --GameAnimation;
			else
				remove();
		}
	}
	
	protected abstract void selectedSprites();
}
