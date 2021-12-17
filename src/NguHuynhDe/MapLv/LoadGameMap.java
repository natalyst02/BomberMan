package NguHuynhDe.MapLv;

import NguHuynhDe.Game;

public class LoadGameMap {
	
	public static int changePixelToTile(double i) {
		return (int)(i / Game.TILES_SIZE);
	}
	
	public static int changeTileToPixel(int i) {
		return i * Game.TILES_SIZE;
	}
	
	public static int changeTileToPixel(double i) {
		return (int)(i * Game.TILES_SIZE);
	}
	
	
}
