package NguHuynhDe.display;

import java.util.Arrays;

public class Sprite {

	public final int SIZE;
	private int XPoint, YPoint;
	public int[] pixelInGame;
	protected int ScreenGameWidth;
	protected int ScreenGameHeight;
	private SpriteSheet _sheet;

	/**
	 * Kích thước texture là 256*256
	 * Chia thành các Sprite nhỏ với size là 16*16 => sẽ có 256 sprite nhỏ
	 * Các sprite có thể được nối với nhau để tạo thành animation
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet, int SW, int SH) {
		SIZE = size;
		pixelInGame = new int[SIZE * SIZE];
		XPoint = x * SIZE;
		YPoint = y * SIZE;
		_sheet = sheet;
		ScreenGameWidth = SW;
		ScreenGameHeight = SH;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;

		pixelInGame = new int[SIZE * SIZE];
		setColor(color);
	}

	public static Sprite voidSprite = new Sprite(16, 0xffffff); //set mau den

	/**
	 * Lấy các hình ảnh thực thể tĩnh từ classic.png
	 */

	public static Sprite grass = new Sprite(16, 6, 0, SpriteSheet.tiles, 16, 16);


	public static Sprite brick = new Sprite(16, 7, 0, SpriteSheet.tiles, 16, 16);

	public static Sprite wall = new Sprite(16, 5, 0, SpriteSheet.tiles, 16, 16);

	public static Sprite portal = new Sprite(16, 4, 0, SpriteSheet.tiles, 14, 14);


	/**
	 * Lấy các hình ảnh người chơi từ classic.png
	 */
	public static Sprite playerMoveUp = new Sprite(16, 0, 0, SpriteSheet.tiles, 12, 15);
	public static Sprite playerMoveDown = new Sprite(16, 2, 0, SpriteSheet.tiles, 12, 14);
	public static Sprite playerMoveLeft = new Sprite(16, 3, 0, SpriteSheet.tiles, 10, 14);
	public static Sprite playerMoveRight = new Sprite(16, 1, 0, SpriteSheet.tiles, 10, 15);
	/**
	 * Animattion đi lên của người chơi
	 */
	public static Sprite playerMoveUp_1 = new Sprite(16, 0, 1, SpriteSheet.tiles, 12, 15);
	public static Sprite playerMoveUp_2 = new Sprite(16, 0, 2, SpriteSheet.tiles, 12, 14);
	/**
	 * Animate đi xuống của người chơi
	 */
	public static Sprite playerMoveDown_1 = new Sprite(16, 2, 1, SpriteSheet.tiles, 12, 14);
	public static Sprite playerMoveDown_2 = new Sprite(16, 2, 2, SpriteSheet.tiles, 12, 15);
	/**
	 * Animate rẽ trái của người chơi
	 */
	public static Sprite playerMoveLeft_1 = new Sprite(16, 3, 1, SpriteSheet.tiles, 11, 16);
	public static Sprite playerMoveLeft_2 = new Sprite(16, 3, 2, SpriteSheet.tiles, 12 ,16);
	/**
	 * Animate rẽ pahir của người chơi
	 */
	public static Sprite playerMoveRight_1 = new Sprite(16, 1, 1, SpriteSheet.tiles, 11, 16);
	public static Sprite playerMoveRight_2 = new Sprite(16, 1, 2, SpriteSheet.tiles, 12, 16);
	/**
	 * Animate người chơi bị tiêu diệt
	 */
	public static Sprite DeadPlayer1 = new Sprite(16, 4, 2, SpriteSheet.tiles, 16, 16);
	public static Sprite DeadPlayer2 = new Sprite(16, 5, 2, SpriteSheet.tiles, 16, 16);
	public static Sprite DeadPlayer3 = new Sprite(16, 6, 2, SpriteSheet.tiles, 16, 16);
	


	public static Sprite playerMoveUp1 = new Sprite(16, 0, 0, SpriteSheet.tiles1, 12, 15);
	public static Sprite playerMoveDown1 = new Sprite(16, 2, 0, SpriteSheet.tiles1, 12, 14);
	public static Sprite playerMoveLeft1 = new Sprite(16, 3, 0, SpriteSheet.tiles1, 10, 14);
	public static Sprite playerMoveRight1 = new Sprite(16, 1, 0, SpriteSheet.tiles1, 10, 15);
	/**
	 * Animate đi lên của người chơi
	 */
	public static Sprite playerMoveUp_11 = new Sprite(16, 0, 1, SpriteSheet.tiles1, 12, 15);
	public static Sprite playerMoveUp_21 = new Sprite(16, 0, 2, SpriteSheet.tiles1, 12, 14);
	/**
	 * Animate đi xuống của người chơi
	 */
	public static Sprite playerMoveDown_11 = new Sprite(16, 2, 1, SpriteSheet.tiles1, 12, 14);
	public static Sprite playerMoveDown_21 = new Sprite(16, 2, 2, SpriteSheet.tiles1, 12, 15);
	/**
	 * Animate rẽ trái của người chơi
	 */
	public static Sprite playerMoveLeft_11 = new Sprite(16, 3, 1, SpriteSheet.tiles1, 11, 16);
	public static Sprite playerMoveLeft_21 = new Sprite(16, 3, 2, SpriteSheet.tiles1, 12 ,16);
	/**
	 * Animate rẽ pahir của người chơi
	 */
	public static Sprite playerMoveRight_11 = new Sprite(16, 1, 1, SpriteSheet.tiles1, 11, 16);
	public static Sprite playerMoveRight_21 = new Sprite(16, 1, 2, SpriteSheet.tiles1, 12, 16);
	/**
	 * Animate người chơi bị tiêu diệt
	 */
	public static Sprite DeadPlayer11 = new Sprite(16, 4, 2, SpriteSheet.tiles1, 16, 16);
	public static Sprite DeadPlayer21 = new Sprite(16, 5, 2, SpriteSheet.tiles1, 16, 16);
	public static Sprite DeadPlayer31 = new Sprite(16, 6, 2, SpriteSheet.tiles1, 16, 16);



	//



	/**
	 | Mobiles Ent
	 */
	//BALLOM
	public static Sprite balloomMoveLeft1 = new Sprite(16, 9, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite balloomMoveLeft2 = new Sprite(16, 9, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite balloomMoveLeft3 = new Sprite(16, 9, 2, SpriteSheet.tiles, 16, 16);

	public static Sprite balloomMoveRight1 = new Sprite(16, 10, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite balloomMoveRight2 = new Sprite(16, 10, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite balloomMoveRight3 = new Sprite(16, 10, 2, SpriteSheet.tiles, 16, 16);

	public static Sprite deadBalloom = new Sprite(16, 9, 3, SpriteSheet.tiles, 16, 16);

	//ONEAL
	public static Sprite oneal_MoveLeft1 = new Sprite(16, 11, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite oneal_MoveLeft2 = new Sprite(16, 11, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite oneal_MoveLeft3 = new Sprite(16, 11, 2, SpriteSheet.tiles, 16, 16);

	public static Sprite oneal_MoveRight1 = new Sprite(16, 12, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite oneal_MoveRight2 = new Sprite(16, 12, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite oneal_MoveRight3 = new Sprite(16, 12, 2, SpriteSheet.tiles, 16, 16);

	public static Sprite oneal_dead = new Sprite(16, 11, 3, SpriteSheet.tiles, 16, 16);

	//Doll
	public static Sprite dollMoveLeft1 = new Sprite(16, 13, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite dollMoveLeft2 = new Sprite(16, 13, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite dollMoveLeft3 = new Sprite(16, 13, 2, SpriteSheet.tiles, 16, 16);

	public static Sprite dollMoveRight1 = new Sprite(16, 14, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite dollMoveRight2 = new Sprite(16, 14, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite dollMoveRight3 = new Sprite(16, 14, 2, SpriteSheet.tiles, 16, 16);

	public static Sprite doll_dead = new Sprite(16, 13, 3, SpriteSheet.tiles, 16, 16);

	//Minvo
	public static Sprite minvoMoveLeft1 = new Sprite(16, 8, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite minvoMoveLeft2 = new Sprite(16, 8, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite minvoMoveLeft3 = new Sprite(16, 8, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite minvoMoveRight1 = new Sprite(16, 9, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite minvoMoveRight2 = new Sprite(16, 9, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite minvoMoveRight3 = new Sprite(16, 9, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite minvo_dead = new Sprite(16, 8, 8, SpriteSheet.tiles, 16, 16);

	//Kondoria
	public static Sprite kondoriaMoveLeft1 = new Sprite(16, 10, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoriaMoveLeft2 = new Sprite(16, 10, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoriaMoveLeft3 = new Sprite(16, 10, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite kondoriaMoveRight1 = new Sprite(16, 11, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoriaMoveRight2 = new Sprite(16, 11, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite kondoriaMoveRight3 = new Sprite(16, 11, 7, SpriteSheet.tiles, 16, 16);

	public static Sprite kondoria_dead = new Sprite(16, 10, 8, SpriteSheet.tiles, 16, 16);

	//ALL
	public static Sprite mob_dead1 = new Sprite(16, 15, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite mob_dead2 = new Sprite(16, 15, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite mob_dead3 = new Sprite(16, 15, 2, SpriteSheet.tiles, 16, 16);

	/*
	|--------------------------------------------------------------------------
	| Bomb Sprites
	|--------------------------------------------------------------------------
	 */
	public static Sprite bomb = new Sprite(16, 0, 3, SpriteSheet.tiles, 15, 15);
	public static Sprite bomb_1 = new Sprite(16, 1, 3, SpriteSheet.tiles, 13, 15);
	public static Sprite bomb_2 = new Sprite(16, 2, 3, SpriteSheet.tiles, 12, 14);


	/**
	 * Animate boom nổ sang các hướng khác
	 */
	public static Sprite bombbeExploded = new Sprite(16, 0, 4, SpriteSheet.tiles, 16, 16);
	public static Sprite bombbeExploded1 = new Sprite(16, 0, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite bombbeExploded2 = new Sprite(16, 0, 6, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_vertical = new Sprite(16, 1, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical1 = new Sprite(16, 2, 5, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical2 = new Sprite(16, 3, 5, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_horizontal = new Sprite(16, 1, 7, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal1 = new Sprite(16, 1, 8, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal2 = new Sprite(16, 1, 9, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_horizontal_leftlastCheck = new Sprite(16, 0, 7, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_leftlastCheck1 = new Sprite(16, 0, 8, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_leftlastCheck2 = new Sprite(16, 0, 9, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_horizontal_rightlastCheck = new Sprite(16, 2, 7, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_rightlastCheck1 = new Sprite(16, 2, 8, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_horizontal_rightlastCheck2 = new Sprite(16, 2, 9, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_vertical_toplastCheck = new Sprite(16, 1, 4, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_toplastCheck1 = new Sprite(16, 2, 4, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_toplastCheck2 = new Sprite(16, 3, 4, SpriteSheet.tiles, 16, 16);

	public static Sprite explosion_vertical_downlastCheck = new Sprite(16, 1, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_downlastCheck1 = new Sprite(16, 2, 6, SpriteSheet.tiles, 16, 16);
	public static Sprite explosion_vertical_downlastCheck2 = new Sprite(16, 3, 6, SpriteSheet.tiles, 16, 16);

	/**
	 |--------------------------------------------------------------------------
	 | Brick Explosion
	 |--------------------------------------------------------------------------
	 */
	public static Sprite BeExplodedBrick = new Sprite(16, 7, 1, SpriteSheet.tiles, 16, 16);
	public static Sprite BeExplodedBrick1 = new Sprite(16, 7, 2, SpriteSheet.tiles, 16, 16);
	public static Sprite BeExplodedBrick2 = new Sprite(16, 7, 3, SpriteSheet.tiles, 16, 16);

	/**
	 |--------------------------------------------------------------------------
	 | Powerups_Item
	 |--------------------------------------------------------------------------
	 */
	public static Sprite powerupBombs = new Sprite(16, 0, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_flames = new Sprite(16, 1, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerupEnemySpeed = new Sprite(16, 2, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_wallpass = new Sprite(16, 3, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_detonator = new Sprite(16, 4, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_bombpass = new Sprite(16, 5, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_undead = new Sprite(16, 6, 10, SpriteSheet.tiles, 16, 16);


	/**
	 |--------------------------------------------------------------------------
	 | Shield
	 |--------------------------------------------------------------------------
	 */
	public static Sprite shield = new Sprite(16, 15, 15, SpriteSheet.tiles, 16, 16);


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
	 | Moving Sprites
	 |--------------------------------------------------------------------------
	 */
	public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
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

	public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
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