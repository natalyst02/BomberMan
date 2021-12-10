package NguHuynhDe.display;

import java.util.Arrays;

public class SpriteInGame {

	public final int SIZE;
	private int XPoint, YPoint;
	public int[] pixelInGame;
	protected int ScreenGameWidth;
	protected int ScreenGameHeight;
	private SpritePicture _sheet;

	/**
	 * Kích thước texture là 256*256
	 * Chia thành các Sprite nhỏ với size là 16*16 => sẽ có 256 sprite nhỏ
	 * Các sprite có thể được nối với nhau để tạo thành animation
	 */
	public SpriteInGame(int size, int x, int y, SpritePicture sheet, int SW, int SH) {
		SIZE = size;
		pixelInGame = new int[SIZE * SIZE];
		XPoint = x * SIZE;
		YPoint = y * SIZE;
		_sheet = sheet;
		ScreenGameWidth = SW;
		ScreenGameHeight = SH;
		load();
	}

	public SpriteInGame(int size, int color) {
		SIZE = size;

		pixelInGame = new int[SIZE * SIZE];
		setColor(color);
	}

	public static SpriteInGame voidSprite = new SpriteInGame(16, 0xffffff); //set mau den

	/**
	 * Lấy các hình ảnh thực thể tĩnh từ classic.png
	 */

	public static SpriteInGame grass = new SpriteInGame(16, 6, 0, SpritePicture.tiles, 16, 16);


	public static SpriteInGame brick = new SpriteInGame(16, 7, 0, SpritePicture.tiles, 16, 16);

	public static SpriteInGame wall = new SpriteInGame(16, 5, 0, SpritePicture.tiles, 16, 16);

	public static SpriteInGame portal = new SpriteInGame(16, 4, 0, SpritePicture.tiles, 14, 14);


	/**
	 * Lấy các hình ảnh người chơi từ classic.png
	 */
	public static SpriteInGame playerMoveUp = new SpriteInGame(16, 0, 0, SpritePicture.tiles, 12, 15);
	public static SpriteInGame playerMoveDown = new SpriteInGame(16, 2, 0, SpritePicture.tiles, 12, 14);
	public static SpriteInGame playerMoveLeft = new SpriteInGame(16, 3, 0, SpritePicture.tiles, 10, 14);
	public static SpriteInGame playerMoveRight = new SpriteInGame(16, 1, 0, SpritePicture.tiles, 10, 15);
	/**
	 * Animattion đi lên của người chơi
	 */
	public static SpriteInGame playerMoveUp_1 = new SpriteInGame(16, 0, 1, SpritePicture.tiles, 12, 15);
	public static SpriteInGame playerMoveUp_2 = new SpriteInGame(16, 0, 2, SpritePicture.tiles, 12, 14);
	/**
	 * Animate đi xuống của người chơi
	 */
	public static SpriteInGame playerMoveDown_1 = new SpriteInGame(16, 2, 1, SpritePicture.tiles, 12, 14);
	public static SpriteInGame playerMoveDown_2 = new SpriteInGame(16, 2, 2, SpritePicture.tiles, 12, 15);
	/**
	 * Animate rẽ trái của người chơi
	 */
	public static SpriteInGame playerMoveLeft_1 = new SpriteInGame(16, 3, 1, SpritePicture.tiles, 11, 16);
	public static SpriteInGame playerMoveLeft_2 = new SpriteInGame(16, 3, 2, SpritePicture.tiles, 12 ,16);
	/**
	 * Animate rẽ pahir của người chơi
	 */
	public static SpriteInGame playerMoveRight_1 = new SpriteInGame(16, 1, 1, SpritePicture.tiles, 11, 16);
	public static SpriteInGame playerMoveRight_2 = new SpriteInGame(16, 1, 2, SpritePicture.tiles, 12, 16);
	/**
	 * Animate người chơi bị tiêu diệt
	 */
	public static SpriteInGame DeadPlayer1 = new SpriteInGame(16, 4, 2, SpritePicture.tiles, 16, 16);
	public static SpriteInGame DeadPlayer2 = new SpriteInGame(16, 5, 2, SpritePicture.tiles, 16, 16);
	public static SpriteInGame DeadPlayer3 = new SpriteInGame(16, 6, 2, SpritePicture.tiles, 16, 16);
	


	public static SpriteInGame playerMoveUp1 = new SpriteInGame(16, 0, 0, SpritePicture.tiles1, 12, 15);
	public static SpriteInGame playerMoveDown1 = new SpriteInGame(16, 2, 0, SpritePicture.tiles1, 12, 14);
	public static SpriteInGame playerMoveLeft1 = new SpriteInGame(16, 3, 0, SpritePicture.tiles1, 10, 14);
	public static SpriteInGame playerMoveRight1 = new SpriteInGame(16, 1, 0, SpritePicture.tiles1, 10, 15);
	/**
	 * Animate đi lên của người chơi
	 */
	public static SpriteInGame playerMoveUp_11 = new SpriteInGame(16, 0, 1, SpritePicture.tiles1, 12, 15);
	public static SpriteInGame playerMoveUp_21 = new SpriteInGame(16, 0, 2, SpritePicture.tiles1, 12, 14);
	/**
	 * Animate đi xuống của người chơi
	 */
	public static SpriteInGame playerMoveDown_11 = new SpriteInGame(16, 2, 1, SpritePicture.tiles1, 12, 14);
	public static SpriteInGame playerMoveDown_21 = new SpriteInGame(16, 2, 2, SpritePicture.tiles1, 12, 15);
	/**
	 * Animate rẽ trái của người chơi
	 */
	public static SpriteInGame playerMoveLeft_11 = new SpriteInGame(16, 3, 1, SpritePicture.tiles1, 11, 16);
	public static SpriteInGame playerMoveLeft_21 = new SpriteInGame(16, 3, 2, SpritePicture.tiles1, 12 ,16);
	/**
	 * Animate rẽ pahir của người chơi
	 */
	public static SpriteInGame playerMoveRight_11 = new SpriteInGame(16, 1, 1, SpritePicture.tiles1, 11, 16);
	public static SpriteInGame playerMoveRight_21 = new SpriteInGame(16, 1, 2, SpritePicture.tiles1, 12, 16);
	/**
	 * Animate người chơi bị tiêu diệt
	 */
	public static SpriteInGame DeadPlayer11 = new SpriteInGame(16, 4, 2, SpritePicture.tiles1, 16, 16);
	public static SpriteInGame DeadPlayer21 = new SpriteInGame(16, 5, 2, SpritePicture.tiles1, 16, 16);
	public static SpriteInGame DeadPlayer31 = new SpriteInGame(16, 6, 2, SpritePicture.tiles1, 16, 16);
	


	/**
	 | Mobiles Ent
	 */
	//BALLOM
	public static SpriteInGame balloomMoveLeft1 = new SpriteInGame(16, 9, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame balloomMoveLeft2 = new SpriteInGame(16, 9, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame balloomMoveLeft3 = new SpriteInGame(16, 9, 2, SpritePicture.tiles, 16, 16);

	public static SpriteInGame balloomMoveRight1 = new SpriteInGame(16, 10, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame balloomMoveRight2 = new SpriteInGame(16, 10, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame balloomMoveRight3 = new SpriteInGame(16, 10, 2, SpritePicture.tiles, 16, 16);

	public static SpriteInGame deadBalloom = new SpriteInGame(16, 9, 3, SpritePicture.tiles, 16, 16);

	//ONEAL
	public static SpriteInGame oneal_MoveLeft1 = new SpriteInGame(16, 11, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame oneal_MoveLeft2 = new SpriteInGame(16, 11, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame oneal_MoveLeft3 = new SpriteInGame(16, 11, 2, SpritePicture.tiles, 16, 16);

	public static SpriteInGame oneal_MoveRight1 = new SpriteInGame(16, 12, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame oneal_MoveRight2 = new SpriteInGame(16, 12, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame oneal_MoveRight3 = new SpriteInGame(16, 12, 2, SpritePicture.tiles, 16, 16);

	public static SpriteInGame oneal_dead = new SpriteInGame(16, 11, 3, SpritePicture.tiles, 16, 16);

	//Doll
	public static SpriteInGame dollMoveLeft1 = new SpriteInGame(16, 13, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame dollMoveLeft2 = new SpriteInGame(16, 13, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame dollMoveLeft3 = new SpriteInGame(16, 13, 2, SpritePicture.tiles, 16, 16);

	public static SpriteInGame dollMoveRight1 = new SpriteInGame(16, 14, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame dollMoveRight2 = new SpriteInGame(16, 14, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame dollMoveRight3 = new SpriteInGame(16, 14, 2, SpritePicture.tiles, 16, 16);

	public static SpriteInGame doll_dead = new SpriteInGame(16, 13, 3, SpritePicture.tiles, 16, 16);

	//Minvo
	public static SpriteInGame minvoMoveLeft1 = new SpriteInGame(16, 8, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame minvoMoveLeft2 = new SpriteInGame(16, 8, 6, SpritePicture.tiles, 16, 16);
	public static SpriteInGame minvoMoveLeft3 = new SpriteInGame(16, 8, 7, SpritePicture.tiles, 16, 16);

	public static SpriteInGame minvoMoveRight1 = new SpriteInGame(16, 9, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame minvoMoveRight2 = new SpriteInGame(16, 9, 6, SpritePicture.tiles, 16, 16);
	public static SpriteInGame minvoMoveRight3 = new SpriteInGame(16, 9, 7, SpritePicture.tiles, 16, 16);

	public static SpriteInGame minvo_dead = new SpriteInGame(16, 8, 8, SpritePicture.tiles, 16, 16);

	//Kondoria
	public static SpriteInGame kondoriaMoveLeft1 = new SpriteInGame(16, 10, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame kondoriaMoveLeft2 = new SpriteInGame(16, 10, 6, SpritePicture.tiles, 16, 16);
	public static SpriteInGame kondoriaMoveLeft3 = new SpriteInGame(16, 10, 7, SpritePicture.tiles, 16, 16);

	public static SpriteInGame kondoriaMoveRight1 = new SpriteInGame(16, 11, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame kondoriaMoveRight2 = new SpriteInGame(16, 11, 6, SpritePicture.tiles, 16, 16);
	public static SpriteInGame kondoriaMoveRight3 = new SpriteInGame(16, 11, 7, SpritePicture.tiles, 16, 16);

	public static SpriteInGame kondoria_dead = new SpriteInGame(16, 10, 8, SpritePicture.tiles, 16, 16);

	//ALL
	public static SpriteInGame mob_dead1 = new SpriteInGame(16, 15, 0, SpritePicture.tiles, 16, 16);
	public static SpriteInGame mob_dead2 = new SpriteInGame(16, 15, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame mob_dead3 = new SpriteInGame(16, 15, 2, SpritePicture.tiles, 16, 16);

	/*
	|--------------------------------------------------------------------------
	| Sprites cua bomb
	|--------------------------------------------------------------------------
	 */
	public static SpriteInGame bomb = new SpriteInGame(16, 0, 3, SpritePicture.tiles, 15, 15);
	public static SpriteInGame bomb_1 = new SpriteInGame(16, 1, 3, SpritePicture.tiles, 13, 15);
	public static SpriteInGame bomb_2 = new SpriteInGame(16, 2, 3, SpritePicture.tiles, 12, 14);


	/**
	 * Animation boom nổ sang các hướng khác
	 */
	public static SpriteInGame bombbeExploded = new SpriteInGame(16, 0, 4, SpritePicture.tiles, 16, 16);
	public static SpriteInGame bombbeExploded1 = new SpriteInGame(16, 0, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame bombbeExploded2 = new SpriteInGame(16, 0, 6, SpritePicture.tiles, 16, 16);

	public static SpriteInGame verticalExplo = new SpriteInGame(16, 1, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame verticalExplo1 = new SpriteInGame(16, 2, 5, SpritePicture.tiles, 16, 16);
	public static SpriteInGame verticalExplo2 = new SpriteInGame(16, 3, 5, SpritePicture.tiles, 16, 16);

	public static SpriteInGame HorizonalExplo = new SpriteInGame(16, 1, 7, SpritePicture.tiles, 16, 16);
	public static SpriteInGame HorizonalExplo1 = new SpriteInGame(16, 1, 8, SpritePicture.tiles, 16, 16);
	public static SpriteInGame HorizonalExplo2 = new SpriteInGame(16, 1, 9, SpritePicture.tiles, 16, 16);

	public static SpriteInGame HorizonalExplo_leftlastCheck = new SpriteInGame(16, 0, 7, SpritePicture.tiles, 16, 16);
	public static SpriteInGame HorizonalExplo_leftlastCheck1 = new SpriteInGame(16, 0, 8, SpritePicture.tiles, 16, 16);
	public static SpriteInGame HorizonalExplo_leftlastCheck2 = new SpriteInGame(16, 0, 9, SpritePicture.tiles, 16, 16);

	public static SpriteInGame HorizonalExplo_rightlastCheck = new SpriteInGame(16, 2, 7, SpritePicture.tiles, 16, 16);
	public static SpriteInGame HorizonalExplo_rightlastCheck1 = new SpriteInGame(16, 2, 8, SpritePicture.tiles, 16, 16);
	public static SpriteInGame HorizonalExplo_rightlastCheck2 = new SpriteInGame(16, 2, 9, SpritePicture.tiles, 16, 16);

	public static SpriteInGame verticalExplo_toplastCheck = new SpriteInGame(16, 1, 4, SpritePicture.tiles, 16, 16);
	public static SpriteInGame verticalExplo_toplastCheck1 = new SpriteInGame(16, 2, 4, SpritePicture.tiles, 16, 16);
	public static SpriteInGame verticalExplo_toplastCheck2 = new SpriteInGame(16, 3, 4, SpritePicture.tiles, 16, 16);

	public static SpriteInGame verticalExplo_downlastCheck = new SpriteInGame(16, 1, 6, SpritePicture.tiles, 16, 16);
	public static SpriteInGame verticalExplo_downlastCheck1 = new SpriteInGame(16, 2, 6, SpritePicture.tiles, 16, 16);
	public static SpriteInGame verticalExplo_downlastCheck2 = new SpriteInGame(16, 3, 6, SpritePicture.tiles, 16, 16);

	/**
	 |--------------------------------------------------------------------------
	 | Explosion Brick
	 |--------------------------------------------------------------------------
	 */
	public static SpriteInGame BeExplodedBrick = new SpriteInGame(16, 7, 1, SpritePicture.tiles, 16, 16);
	public static SpriteInGame BeExplodedBrick1 = new SpriteInGame(16, 7, 2, SpritePicture.tiles, 16, 16);
	public static SpriteInGame BeExplodedBrick2 = new SpriteInGame(16, 7, 3, SpritePicture.tiles, 16, 16);

	/**
	 |--------------------------------------------------------------------------
	 | Item Power
	 |--------------------------------------------------------------------------
	 */
	public static SpriteInGame powerupBombs = new SpriteInGame(16, 0, 10, SpritePicture.tiles, 16, 16);
	public static SpriteInGame powerupFlame = new SpriteInGame(16, 1, 10, SpritePicture.tiles, 16, 16);
	public static SpriteInGame powerupEnemySpeed = new SpriteInGame(16, 2, 10, SpritePicture.tiles, 16, 16);
	public static SpriteInGame powerupWallPas = new SpriteInGame(16, 3, 10, SpritePicture.tiles, 16, 16);
	public static SpriteInGame powerupDe = new SpriteInGame(16, 4, 10, SpritePicture.tiles, 16, 16);
	public static SpriteInGame powerupBombPas = new SpriteInGame(16, 5, 10, SpritePicture.tiles, 16, 16);
	public static SpriteInGame powerup_undead = new SpriteInGame(16, 6, 10, SpritePicture.tiles, 16, 16);


	/**
	 |--------------------------------------------------------------------------
	 | Shield
	 |--------------------------------------------------------------------------
	 */
	public static SpriteInGame shield = new SpriteInGame(16, 15, 15, SpritePicture.tiles, 16, 16);


	public void setColor(int color) {
		Arrays.fill(pixelInGame, color);
	}

	private void load() {
		for (int y = 0; y < SIZE; y++)
			for (int x = 0; x < SIZE; x++) {
				pixelInGame[x + y * SIZE] = _sheet.pixelInGame[(x + XPoint) + (y + YPoint) * _sheet.SIZE];
			}
	}

	/**
	 |--------------------------------------------------------------------------
	 | di chuyen
	 |--------------------------------------------------------------------------
	 */
	public static SpriteInGame movingSprite(SpriteInGame normal, SpriteInGame x1, SpriteInGame x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;

		if(calc < diff) {
			return normal;
		}

		if(calc < diff * 2) {
			return x1;
		}

		return x2;
	}

	public static SpriteInGame movingSprite(SpriteInGame x1, SpriteInGame x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2;
	}

	public int getSize() {
		return SIZE;
	}

	public int[] getPixels() {
		return pixelInGame;
	}

	public int getPixel(int i) {
		return pixelInGame[i];
	}

	public int getGameWidth() {
		return ScreenGameWidth;
	}

	public int getGameHeight() {
		return ScreenGameHeight;
	}

}