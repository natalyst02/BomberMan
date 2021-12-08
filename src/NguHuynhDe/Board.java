package NguHuynhDe;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import NguHuynhDe.entities.mob.MobileEnti;
import NguHuynhDe.entities.mob.Player;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.Notification;
import NguHuynhDe.entities.bomb.Bomb;
import NguHuynhDe.entities.bomb.Explosion;
import NguHuynhDe.entities.tile.powerup.Powerup;
import NguHuynhDe.exceptions.LoadLevelException;
import NguHuynhDe.display.IRender;
import NguHuynhDe.display.ScreenInGame;
import NguHuynhDe.KeyBoard.Keyboard;
import NguHuynhDe.MapLv.SetupLevel;
import NguHuynhDe.MapLv.ModeGame;
import NguHuynhDe.music.Audio;

public class Board implements IRender {

	private final List<Notification> NotiList = new ArrayList<>();
	public int ScreenWidth, ScreenHeight;
	/**
	 * Thực thể không di chuyển
	 */
	public Entity[] EntiGameList;
	/**
	 * Thực thể di chuyển
	 */
	public List<MobileEnti> MobList = new ArrayList<>();/**
	 /**
	 * Màn chơi
	 */
	protected ModeGame modeG;
	/**
	 */
	protected Game GamePlay;
	/**
	 * Input từ keyboard
	 */
	protected Keyboard InputFromKeyboard;
	protected ScreenInGame ScreenGame;
	/**
	 * Bomb
	 */
	protected List<Bomb> bombsList = new ArrayList<>();
	private int ScreenGameToShow = -1; //1:endgame, 2:changelevel, 3:paused
	public int getScreen(){
		return ScreenGameToShow;
	}
	/**
	 * Thời gian
	 */
	private int GameTime = Game.TIME;
	/**
	 * Điểm
	 */
	private int ScoresGame = Game.POINTS;
	/**
	 * Mạng
	 */
	private int GameLives = Game.LIVES;
	/**
	 * Audio
	 */
	protected Audio musicGame = new Audio();
	protected boolean checkLose = false;
	protected boolean checkFirstBlood = false;

	public Board(Game game, Keyboard input, ScreenInGame screen) {
		GamePlay = game;
		InputFromKeyboard = input;
		ScreenGame = screen;

		changeLevel(1); //start in level 1
	}


	/**
	 |--------------------------------------------------------------------------
	 | Render
	 |--------------------------------------------------------------------------

	 */
	/**
	 * Update được gọi liên tục trong mỗi khung hình
	 *
	 */
	@Override
	public void update() {
		if( GamePlay.isPaused() ) return;

		updateEntities();
		updateMobs();
		updateBombs();
		updateMessages();
		detectEndGame();

		if (!checkLose) AceNotify();
		if (!checkFirstBlood) FirstBloodNotify();

		for (int i = 0; i < MobList.size(); i++) {
			MobileEnti a = MobList.get(i);
			if (a.checkBeRemoved()) MobList.remove(i);
		}
	}

	/**
	 * Render xuất hình ảnh ra màn hình
	 *
	 */
	@Override
	public void render(ScreenInGame screen) {
		if( GamePlay.isPaused() ) return;

		int x0 = ScreenInGame.xOffset >> 4;
		int x1 = (ScreenInGame.xOffset + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE;
		int y0 = ScreenInGame.yOffset >> 4;
		int y1 = (ScreenInGame.yOffset + screen.getHeight()) / Game.TILES_SIZE; //

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				EntiGameList[x + y * modeG.getWidth()].render(screen);
			}
		}

		renderBombs(screen);
		renderMobs(screen);
	}


	/**
	 |--------------------------------------------------------------------------
	 | Change Level
	 |--------------------------------------------------------------------------
	 */

	public void newGame() {
		resetProperties();
		changeLevel(1);
	}

	@SuppressWarnings("static-access")
	private void resetProperties() {
		ScoresGame = Game.POINTS;
		GameLives = Game.LIVES;
		Player.PowerUpList.clear();
		checkFirstBlood = false;
		checkLose = false;
		GamePlay.playerSpeed = 1.0;
		GamePlay.bombRadius = 1;
		GamePlay.bombRate = 1;

	}

	@SuppressWarnings("static-access")
	private void resetPoint() {
		ScoresGame = Game.POINTS;
		Player.PowerUpList.clear();
		checkFirstBlood = false;
		checkLose = false;
		GamePlay.playerSpeed = 1.0;
		GamePlay.bombRadius = 1;
		GamePlay.bombRate = 1;
	}


	public void restartLevel() {
		changeLevel(modeG.getLevel());
		resetPoint();
	}
	/**
	 * Hack nextLevel
	 */
	public void nextLevel() {
		if (modeG.getLevel() <= 5){changeLevel(modeG.getLevel() + 1);}
		else{
			endGame();
		}
	}
	/**
	 * Hack mạng
	 */

	public void setNeverDie(){
		GameLives = 1000000;
	}
	/**
	 * Hack prevLevel
	 */
	public void prevLevel() {
		if (modeG.getLevel() > 1){
			changeLevel(modeG.getLevel() - 1);
		}
		else{
			restartLevel();
		}
	}


	/**
	 * Music
	 */


	public void changeLevel(int level) {
		GameTime = Game.TIME;
		ScreenGameToShow = 2;
		GamePlay.resetScreenDelay();
		GamePlay.pause();
		MobList.clear();
		bombsList.clear();
		NotiList.clear();
		checkFirstBlood = false;
		checkLose = false;

		try {
			modeG = new SetupLevel("levels/Level" + level + ".txt", this);
			EntiGameList = new Entity[modeG.getHeight() * modeG.getWidth()];

			modeG.createEntities();
		} catch (LoadLevelException e) {
			endGame(); 
		}
	}

	// Thay đổi level bằng code cheat
	public void changeLevelByCode(String str) {
		int i = modeG.trueMode(str);

		if (i != -1) changeLevel(i + 1);
	}

	public boolean isPowerupUsed(int x, int y, int level) {
		Powerup p;
		for (int i = 0; i < Player.PowerUpList.size(); i++) {
			p = Player.PowerUpList.get(i);
			if(p.getX() == x && p.getY() == y && level == p.getLevel())
				return true;
		}

		return false;
	}

	/**
	 |--------------------------------------------------------------------------
	 | Detections
	 |--------------------------------------------------------------------------
	 */

	/**
	 * Kiểm tra thời gian
	 */
	protected void detectEndGame() {
		if(GameTime <= 0)
			restartLevel();
	}

	/**
	 * GameOver
	 */
	public void endGame() {
		ScreenGameToShow = 1;
		GamePlay.resetScreenDelay();
		GamePlay.pause();
	}

	/**
	 * Kiểm tra kẻ địch trên sàn
	 */
	public boolean detectNoEnemies() {
		int AllEnTi = 0;
		for (MobileEnti mob : MobList) {
			if (!(mob instanceof Player))
				++AllEnTi;
		}
		return AllEnTi == 0;
	}

	public void AceNotify(){
		if (!detectNoEnemies()) {
		}
		else {
			musicGame.playSound("res/sounds/mk.wav",0);
			checkLose = true;
		}

	}

	public void FirstBloodNotify(){
		if (!detectFirstBlood()) {
		}
		else {
			musicGame.playSound("res/sounds/firstblood.wav",0);
			checkFirstBlood = true;
		}

	}

	public boolean  detectFirstBlood(){
		int AllEnTi = 0;
		for (MobileEnti mob : MobList) {
			if (!(mob instanceof Player))
				if (mob.beAliveP())
					++AllEnTi;
		}
		return AllEnTi == MobList.size() - 2;
	}


	/**
	 |--------------------------------------------------------------------------
	 | Pause va Resume
	 |--------------------------------------------------------------------------
	 */
	public void gamePause() {

		GamePlay.resetScreenDelay();
		if(ScreenGameToShow <= 0)
			ScreenGameToShow = 3;
		GamePlay.pause();
	}

	public void gameResume() {
		GamePlay.resetScreenDelay();
		ScreenGameToShow = -1;
		GamePlay.run();
	}

	/**
	 |--------------------------------------------------------------------------
	 | kiem tra man hinh
	 |--------------------------------------------------------------------------
	 */

	public void drawScreen(Graphics g) {
		switch (ScreenGameToShow) {
			case 1:
				ScreenGame.setEndScene(g, ScoresGame, modeG.getMode());
				break;
			case 2:
				ScreenGame.setLevelScene(g, modeG.getLevel());
				break;
			case 3:
				ScreenGame.setPausedScene(g);
				break;
		}
	}

	/**
	 |--------------------------------------------------------------------------
	 | check Enti
	 |--------------------------------------------------------------------------
	 */

	public Entity getEntity(double x, double y, MobileEnti m) {

		Entity tmp;

		tmp = getExplosionPoint((int) x, (int) y);
		if (tmp != null) return tmp;

		tmp = getBombPos(x, y);
		if (tmp != null) return tmp;

		tmp = getMobAtExcluding((int) x, (int) y, m);
		if (tmp != null) return tmp;

		tmp = getEntityAt((int)x, (int)y);

		return tmp;
	}

	public List<Bomb> getBoList() {
		return bombsList;
	}

	public Bomb getBombPos(double x, double y) {
		Iterator<Bomb> bombInList = bombsList.iterator();
		Bomb b;
		while(bombInList.hasNext()) {
			b = bombInList.next();
			if(b.getX() == (int)x && b.getY() == (int)y)
				return b;
		}

		return null;
	}

	public MobileEnti getMobAt(double x, double y) {
		Iterator<MobileEnti> MobInList = MobList.iterator();

		MobileEnti currentP;
		while(MobInList.hasNext()) {
			currentP = MobInList.next();

			if(currentP.getTileX() == x && currentP.getTileY() == y)
				return currentP;
		}

		return null;
	}

	public Player getPlayer() {
		Iterator<MobileEnti> itr = MobList.iterator();

		MobileEnti currentP;
		while(itr.hasNext()) {
			currentP = itr.next();

			if(currentP instanceof Player)
				return (Player) currentP;
		}

		return null;
	}

	public MobileEnti getMobAtExcluding(int x, int y, MobileEnti a) {
		Iterator<MobileEnti> MobInList = MobList.iterator();

		MobileEnti currentP;
		while(MobInList.hasNext()) {
			currentP = MobInList.next();
			if(currentP == a) {
				continue;
			}

			if(currentP.getTileX() == x && currentP.getTileY() == y) {
				return currentP;
			}

		}

		return null;
	}

	public Explosion getExplosionPoint(int x, int y) {
		Iterator<Bomb> bombInList = bombsList.iterator();
		Bomb bo;
		while(bombInList.hasNext()) {
			bo = bombInList.next();

			Explosion e = bo.ExplosionPoint(x, y);
			if(e != null) {
				return e;
			}

		}

		return null;
	}

	public Entity getEntityAt(double x, double y) {
		return EntiGameList[(int)x + (int)y * modeG.getWidth()];
	}

	/**
	 |--------------------------------------------------------------------------
	 | Them Bot
	 |--------------------------------------------------------------------------
	 */
	public void addEntitie(int pos, Entity e) {
		EntiGameList[pos] = e;
	}
	

	public void addMob(MobileEnti e) {
		MobList.add(e);
	}

	public void addBomb(Bomb e) {
		bombsList.add(e);
	}

	public void addMessage(Notification e) {
		NotiList.add(e);
	}

	/**
	 |--------------------------------------------------------------------------
	 | Render
	 |--------------------------------------------------------------------------
	 */
	protected void renderEntities(ScreenInGame screen) {
		for (Entity entity : EntiGameList) {
			entity.render(screen);
		}
	}

	protected void renderMobs(ScreenInGame screen) {

		for (MobileEnti mob : MobList) mob.render(screen);
	}

	protected void renderBombs(ScreenInGame screen) {

		for (Bomb bomb : bombsList) bomb.render(screen);
	}

	public void renderMessages(Graphics g) {
		Notification noti;
		for (Notification message : NotiList) {
			noti = message;

			g.setFont(new Font("Arial", Font.PLAIN, noti.getSize()));
			g.setColor(noti.getColor());
			g.drawString(noti.getMessage(), (int) noti.getX() - ScreenInGame.xOffset * Game.SCALE, (int) noti.getY());
		}
	}

	/**
	 |--------------------------------------------------------------------------
	 | Update
	 |--------------------------------------------------------------------------
	 */
	protected void updateEntities() {
		if (GamePlay.isPaused()) return;
		for (Entity entity : EntiGameList) {
			entity.update();
		}
	}

	protected void updateMobs() {
		if(GamePlay.isPaused()) return;
		Iterator<MobileEnti> itr = MobList.iterator();

		while(itr.hasNext() && !GamePlay.isPaused())
			itr.next().update();
	}

	//	protected void updateShield() {
//		if (GamePlay.isPaused()) return;
//		Iterator<Shield> itr = PlayerShield.iterator();
//
//	}
	protected void updateBombs() {
		if (GamePlay.isPaused()) return;

		for (Bomb bomb : bombsList) bomb.update();
	}

	protected void updateMessages() {
		if (GamePlay.isPaused()) return;
		Notification noti;
		int left;
		for (int i = 0; i < NotiList.size(); i++) {
			noti = NotiList.get(i);
			left = noti.getDuration();

			if (left > 0)
				noti.setDuration(--left);
			else
				NotiList.remove(i);
		}
	}

	/**
	 |--------------------------------------------------------------------------
	 | Contructors
	 |--------------------------------------------------------------------------
	 */
	public Keyboard getInput() {
		return InputFromKeyboard;
	}

	public ModeGame getLevel() {
		return modeG;
	}

	public Game getGame() {
		return GamePlay;
	}

	public int getShow() {
		return ScreenGameToShow;
	}

	public void setShow(int i) {
		ScreenGameToShow = i;
	}

	public int getTime() {
		return GameTime;
	}

	public int getLives() {
		return GameLives;
	}

	public int TimeUp() {
		if(GamePlay.isPaused())
			return this.GameTime;
		else
			return this.GameTime--;
	}

	public int getPoints() {
		return ScoresGame;
	}

	public void addPoints(int points) {
		this.ScoresGame += points;
	}

	public void addLives(int lives) {
		this.GameLives += lives;
	}

	public int getWidth() {
		return modeG.getWidth();
	}

	public int getHeight() {
		return modeG.getHeight();
	}

}
