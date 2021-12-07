package NguHuynhDe.display;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String _path;
	public final int SIZE;
	public int[] pixelInGame;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/classics.png", 256);
	public static SpriteSheet tiles1 = new SpriteSheet("/textures/New folder/classics1.png", 256);

	public SpriteSheet(String path, int size) {
		_path = path;
		SIZE = size;
		pixelInGame = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			URL urlLink = SpriteSheet.class.getResource(_path);
			BufferedImage image = ImageIO.read(urlLink);
			int wG = image.getWidth();
			int hG = image.getHeight();
			image.getRGB(0, 0, wG, hG, pixelInGame, 0, wG);


		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
