package NguHuynhDe.level;


import NguHuynhDe.exceptions.LoadLevelException;

public interface ILevel {

	public void loadLevel(String path) throws LoadLevelException;
}
