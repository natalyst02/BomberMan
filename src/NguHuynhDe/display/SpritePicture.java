package NguHuynhDe.display;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpritePicture {

	private String strPath;
	public final int SIZE;
	public int[] pixelInGame;
	
	public static SpritePicture tiles = new SpritePicture("/textures/classics.png", 256);
	public static SpritePicture tiles1 = new SpritePicture("/res/textures/PictureDemo/classics1.png", 256);

	public SpritePicture(String path, int size) {
		strPath = path;
		SIZE = size;
		pixelInGame = new int[SIZE * SIZE];
		load();
	}
	
	private void load() {
		try {
			URL urlLink = SpritePicture.class.getResource(strPath);
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
