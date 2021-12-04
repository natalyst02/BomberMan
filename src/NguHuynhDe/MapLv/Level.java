package NguHuynhDe.MapLv;

import NguHuynhDe.Board;
import NguHuynhDe.exceptions.LoadLevelException;

public abstract class Level implements ILevel {

	protected int _width, _height, _level;
	protected String[] _lineTiles;
	protected Board _board;

	protected static String[] Mode = { //TODO: change this code system to actualy load the code from each level.txt
		"L1",
		"L2",
		"L3",
		"L4",
		"L5",
		};

	public Level(String path, Board board) throws LoadLevelException {
		loadLevel(path);
		_board = board;
	}

	@Override
	public abstract void loadLevel(String path) throws LoadLevelException;
	
	public abstract void createEntities();

	/*
	|--------------------------------------------------------------------------
	| Mode
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
	
	public String getActualCode() {
		return Mode[_level -1];
	}

	public int getWidth() {
		return _width;
	}

	public int getHeight() {
		return _height;
	}

	public int getLevel() {
		return _level;
	}

}
