package NguHuynhDe.MapLv;

import NguHuynhDe.Board;
import NguHuynhDe.Except.LevelExcep;

public abstract class ModeGame implements GeneralLoadLv {

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

	public ModeGame(String path, Board board) throws LevelExcep {
		loadLevel(path);
		GameBoard = board;
	}

	@Override
	public abstract void loadLevel(String path) throws LevelExcep;
	
	public abstract void creatNewEnti();

	/*
	|--------------------------------------------------------------------------
	| Mode Game
	|--------------------------------------------------------------------------
	 */
	public int trueMode(String str) {
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
