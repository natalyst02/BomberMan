package NguHuynhDe.entities.mobileGameEnti;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NguHuynhDe.Game;
import NguHuynhDe.Board;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.Notification;
import NguHuynhDe.music.Audio;
import NguHuynhDe.entities.bomb.GenaralBombExplo;
import NguHuynhDe.entities.bomb.HandleDirectionBomb;
import NguHuynhDe.entities.mobileGameEnti.enemy.Enemy;
import NguHuynhDe.entities.tile.powerup.Powerup;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.display.SpriteInGame;
import NguHuynhDe.KeyBoard.Keyboard;
import NguHuynhDe.MapLv.LoadGameMap;

public class Player extends MobileEnti {

	private List<GenaralBombExplo> bombsList;
	protected Keyboard InputFromKeyboard;
	protected Audio musicGame = new Audio();
	protected int DelaySetBomb = 0;
	public static boolean undead=false;
	public static List<Powerup> PowerUpList = new ArrayList<Powerup>();

	/** tao khien
	 */
	protected Shield PlayerShield = new Shield(0,0,0,0,GameBoard,this);

	protected void SetShield(){
		if (InputFromKeyboard.R){
			int xS = LoadGameMap.changePixelToTile(_x + GameSprite.getSize() / 2f);
			int yS = LoadGameMap.changePixelToTile( (_y + GameSprite.getSize() / 2f) - GameSprite.getSize() );
			PosShield(xS,yS);
		}
	}

	protected void PosShield(int x, int y){
		if (PlayerShield.getcoolDown() <= 0){
			PlayerShield = new Shield(x,y,300,1800,GameBoard,this);
			PlayerShield.setActive(true);
		}
	}





	public Player(int x, int y, Board board) {
		super(x, y, board);
		bombsList = GameBoard.getBoList();
		InputFromKeyboard = GameBoard.getInput();
		GameSprite = SpriteInGame.playerMoveRight;
	}


	/*
	|--------------------------------------------------------------------------
	| Render
	|--------------------------------------------------------------------------
	 */
	@Override
	public void update() {
		clearBombs();
		if(CheckAlive == false) {
			afterwardKill();
			return;
		}

		if(DelaySetBomb < -7500) DelaySetBomb = 0; else DelaySetBomb--;

		animate();

		SetShield();


		calcTransfer();

		detectPlaceBomb();
		PlayerShield.update();
	}

	@Override
	public void render(ScreenInGame screen) {
		CalcOffsetPoint();

		if(CheckAlive) {
			selectedSprites();

		}

		else
			GameSprite = SpriteInGame.movingSprite(SpriteInGame.DeadPlayer1, SpriteInGame.DeadPlayer2, AnimationOfEnti, 20);

		screen.renderEntity((int)_x, (int)_y - GameSprite.SIZE, this);

		PlayerShield.render(screen);
	}

	public void CalcOffsetPoint() {
		int ScrollGame = ScreenInGame.CalcOffsetPoint(GameBoard, this);
		ScreenInGame.setPointOffset(ScrollGame, 0);
	}


	/*
	|--------------------------------------------------------------------------
	| dat bom
	|--------------------------------------------------------------------------
	 */
	private void detectPlaceBomb() {
		if(InputFromKeyboard.space && Game.getBombRate() > 0 && DelaySetBomb < 0) {

			int xB = LoadGameMap.changePixelToTile(_x + GameSprite.getSize() / 2);
			int yB = LoadGameMap.changePixelToTile( (_y + GameSprite.getSize() / 2) - GameSprite.getSize() );

			placeBomb(xB,yB);
			Game.addBombRate(-1);

			DelaySetBomb = 30;
		}
	}

	protected void placeBomb(int x, int y) {
		musicGame.playSound("res/sounds/place_bomb.wav",0);
		GenaralBombExplo bo = new GenaralBombExplo(x, y, GameBoard);
		GameBoard.addBomb(bo);
	}

	private void clearBombs() {
		Iterator<GenaralBombExplo> bombInList = bombsList.iterator();

		GenaralBombExplo bo;
		while(bombInList.hasNext()) {
			bo = bombInList.next();
			if(bo.checkBeRemoved())  {
				bombInList.remove();
				Game.addBombRate(1);
			}
		}

	}

	/*
	|--------------------------------------------------------------------------
	| Check va cham
	|--------------------------------------------------------------------------
	 */
	@Override
	public void kill() {
		if (!PlayerShield.getActive() && !undead){
			if(!CheckAlive) return;

			CheckAlive = false;

			GameBoard.addLives(-1);
			musicGame.playSound("res/sounds/dead.wav",0);
			Notification noti = new Notification("-1 LIVE", getMessX(), getMessY(), 2, Color.white, 14);
			GameBoard.addMessage(noti);
		}
	}

	@Override
	protected void afterwardKill() {
		if(DelayEntiTiming > 0) --DelayEntiTiming;
		else {
			if(bombsList.size() == 0) {

				if(GameBoard.getLives() == 0)
					GameBoard.endGame();
				else
					GameBoard.restartLevel();
			}
		}
	}

	/*
	|--------------------------------------------------------------------------
	| Move dam dong
	|--------------------------------------------------------------------------
	 */
	@Override
	protected void calcTransfer() {
		int xP = 0, yP = 0;
		if(InputFromKeyboard.up) yP--;
		if(InputFromKeyboard.down) yP++;
		if(InputFromKeyboard.left) xP--;
		if(InputFromKeyboard.right) xP++;

		if(xP != 0 || yP != 0)  {
			move(xP * Game.getPlayerSpeed(), yP * Game.getPlayerSpeed());
			CheckMove = true;
		} else {
			CheckMove = false;
		}

	}

	@Override
	public boolean possibleTransfer(double x, double y) {
		for (int c = 0; c < 4; c++) { //check 4 goc
			double xP = ((_x + x) + c % 2 * 11) / Game.TILES_SIZE; 
			double yP = ((_y + y) + c / 2 * 12 - 13) / Game.TILES_SIZE; 

			Entity a = GameBoard.getEntity(xP, yP, this);

			if(!a.checkCollide(this))
				return false;
		}

		return true;
	}

	@Override
	public void move(double xP, double yP) {
		if(xP > 0) DirectionBomb = 1;
		if(xP < 0) DirectionBomb = 3;
		if(yP > 0) DirectionBomb = 2;
		if(yP < 0) DirectionBomb = 0;

		if(possibleTransfer(0, yP)) { //check va cham
			_y += yP;
		}

		if(possibleTransfer(xP, 0)) {
			_x += xP;
		}
	}


	/**
	 * Xử lý va chạm cho người chơi
	 */
	@Override
	public boolean checkCollide(Entity e) {
		if(e instanceof HandleDirectionBomb) {
			kill();
			return false;
		}

		//  có khiên thì kẻ địch chết
		if(e instanceof Enemy) {
			if (PlayerShield.getActive() && undead){
				((Enemy) e).kill();
			}
			else {
				kill();
				return true;
			}
		}

		return true;
	}

	/*
	|--------------------------------------------------------------------------
	| Nang Cap
	|--------------------------------------------------------------------------
	 */
	public void addPowerup(Powerup p) {
		if(p.checkBeRemoved()) return;
		musicGame.playSound("res/sounds/power_up.wav",0);
		PowerUpList.add(p);

		p.setValues();
	}


	/*
	|--------------------------------------------------------------------------
	| Xu ly Sprite
	|--------------------------------------------------------------------------
	 */

	private void selectedSprites() {

		switch(DirectionBomb) {
			case 0:
				if(undead){
					GameSprite = SpriteInGame.playerMoveUp1;

					if(CheckMove) {
						GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveUp_11, SpriteInGame.playerMoveUp_21, AnimationOfEnti, 20);
					}
				}
			else {
					GameSprite = SpriteInGame.playerMoveUp;

					if (CheckMove) {
						GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveUp_1, SpriteInGame.playerMoveUp_2, AnimationOfEnti, 20);
					}
				}
			break;
		case 1:
			if(undead){
				GameSprite = SpriteInGame.playerMoveRight1;
				if(CheckMove) {
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveRight_11, SpriteInGame.playerMoveRight_21, AnimationOfEnti, 20);
			}}
			else{
			GameSprite = SpriteInGame.playerMoveRight;
			if(CheckMove) {
				GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveRight_1, SpriteInGame.playerMoveRight_2, AnimationOfEnti, 20);
			}}

			break;
		case 2:
			if(undead){
				GameSprite = SpriteInGame.playerMoveDown1;
				if(CheckMove) {
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveDown_11, SpriteInGame.playerMoveDown_21, AnimationOfEnti, 20);
				}
			}
			else {
				GameSprite = SpriteInGame.playerMoveDown;
				if (CheckMove) {
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveDown_1, SpriteInGame.playerMoveDown_2, AnimationOfEnti, 20);
				}
			}
			break;
		case 3:
			if(undead)
			{
				GameSprite = SpriteInGame.playerMoveLeft1;
				if(CheckMove) {
					GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveLeft_11, SpriteInGame.playerMoveLeft_21, AnimationOfEnti, 20);
				}
			}
			else{
			GameSprite = SpriteInGame.playerMoveLeft;
			if(CheckMove) {
				GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveLeft_1, SpriteInGame.playerMoveLeft_2, AnimationOfEnti, 20);
			}}

			break;
		default:
			GameSprite = SpriteInGame.playerMoveRight;
			if(CheckMove) {
				GameSprite = SpriteInGame.movingSprite(SpriteInGame.playerMoveRight_1, SpriteInGame.playerMoveRight_2, AnimationOfEnti, 20);
			}

			break;
		}
	}
}
