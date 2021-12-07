package NguHuynhDe.MapLv;

import NguHuynhDe.Board;
import NguHuynhDe.exceptions.LoadLevelException;

public abstract class Level implements ILevel {

	protected int ScreenWidth, ScreenHeight, modeG;
	protected String[] FileGameTiles;
	protected Board GameBoard;

	protected static String[] Mode = { 
		"L1",
		"L2",
		"L3",
		"L4",
		"L5",
		};

	public Level(String path, Board board) throws LoadLevelException {
		loadLevel(path);
		GameBoard = board;
	}

	@Override
	public abstract void loadLevel(String path) throws LoadLevelException;
	
	public abstract void createEntities();

	/*
	|--------------------------------------------------------------------------
	| Mode Game
	|--------------------------------------------------------------------------
	 */
	public int validCode(String str) {
		for (int i = 0; i < Mode.length; i++) {
			if (Mode[i].equals(str)) {
				return i;
			}
		}
		return -1;
	}
	
	public String getMode() {
		return Mode[modeG -1];
	}

	public int getWidth() {
		return ScreenWidth;
	}

	public int getHeight() {
		return ScreenHeight;
	}

	public int getLevel() {
		return modeG;
	}

}
