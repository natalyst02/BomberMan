package NguHuynhDe.MapLv;


import NguHuynhDe.exceptions.LoadLevelException;

public interface ILevel {

	public void loadLevel(String path) throws LoadLevelException;
}
