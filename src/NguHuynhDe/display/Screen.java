package NguHuynhDe.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import NguHuynhDe.Board;
import NguHuynhDe.Game;
import NguHuynhDe.entities.Entity;
import NguHuynhDe.entities.mob.Player;

public class Screen {
	protected int ScreenWidth, ScreenHeight;
	public int[] pixelInGame;
	private int transColor = 0xffff00ff;
	private Image mshi;
	
	public static int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		ScreenWidth = width;
		ScreenHeight = height;
		
		pixelInGame = new int[width * height];
		
	}
	
	public void clear() {
		for (int i = 0; i < pixelInGame.length; i++) {
			pixelInGame[i] = 0;
		}
	}
	
	public void renderEntity(int PointX, int PointY, Entity GameEntity) {
		PointX -= xOffset;
		PointY -= yOffset;
		for (int y = 0; y < GameEntity.getSprite().getSize(); y++) {
			int PointY2 = y + PointY; // them toa do moi
			for (int x = 0; x < GameEntity.getSprite().getSize(); x++) {
				int PointX2 = x + PointX; // them toa do moi
				if(PointX2 < -GameEntity.getSprite().getSize() || PointX2 >= ScreenWidth || PointY2 < 0 || PointY2 >= ScreenHeight) break;
				if(PointX2 < 0) PointX2 = 0; //bat dau tu diem 0
				int color = GameEntity.getSprite().getPixel(x + y * GameEntity.getSprite().getSize());

				 if(color != transColor) pixelInGame[PointX2 + PointY2 * ScreenWidth] = color;

			}
		}
	}
	
	public void RenderEntitiesWithSpriteBelow(int PointX, int PointY, Entity GameEntity, Sprite BelowPoint) {
		PointX -= xOffset;
		PointY -= yOffset;
		for (int y = 0; y < GameEntity.getSprite().getSize(); y++) {
			int PointY2 = y + PointY;
			for (int x = 0; x < GameEntity.getSprite().getSize(); x++) {
				int PointX2 = x + PointX;
				if(PointX2 < -GameEntity.getSprite().getSize() || PointX2 >= ScreenWidth || PointY2 < 0 || PointY2 >= ScreenHeight) break;
				if(PointX2 < 0) PointX2 = 0;
				int color = GameEntity.getSprite().getPixel(x + y * GameEntity.getSprite().getSize());
				if(color != transColor) 
					pixelInGame[PointX2 + PointY2 * ScreenWidth] = color;
				else
					pixelInGame[PointX2 + PointY2 * ScreenWidth] = BelowPoint.getPixel(x + y * BelowPoint.getSize());
			}
		}
	}
	
	public static void setPointOffset(int xO, int yO) {
		xOffset = xO;
		yOffset = yO;
	}
	
	public static int CalcOffsetPoint(Board gameboard, Player player) {
		if(player == null) return 0;
		int OffsetTemp = xOffset;
		
		double playerPointX = player.getX() / 16;
		double addOn = 0.5;
		int firstBreakpoint = gameboard.getWidth() / 4;
		int lastBreakpoint = gameboard.getWidth() - firstBreakpoint;
		
		if( playerPointX > firstBreakpoint + addOn && playerPointX < lastBreakpoint - addOn) {
			OffsetTemp = (int)player.getX()  - (Game.WIDTH / 2);
		}
		
		return OffsetTemp;
	}
	
	/*
	Giao dien chinh
	 */
	public void setEndScene(Graphics GameScene, int ScoresGame, String mode) {

		GameScene.setColor(Color.black);
		GameScene.fillRect(0, 0, getGameWidth(), getGameHeight());
		mshi = new ImageIcon("src/NguHuynhDe/display/backgr.png").getImage();
		GameScene.drawImage(mshi,0, 0,getGameWidth(), getGameHeight(),
				null);
		Font GameText = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		GameScene.setFont(GameText);
		GameScene.setColor(Color.blue);
		setTextInScreen("GAME OVER", getGameWidth(), getGameHeight() - (Game.TILES_SIZE * 2) * Game.SCALE, GameScene);

		GameText = new Font("Arial", Font.PLAIN, 10 * Game.SCALE);
		GameScene.setFont(GameText);
		GameScene.setColor(Color.red);
		setTextInScreen("ScoresGame: " + ScoresGame, getGameWidth(), getGameHeight() + (Game.TILES_SIZE * 2) * Game.SCALE, GameScene);


		GameText = new Font("Arial", Font.PLAIN, 10 * Game.SCALE);
		GameScene.setFont(GameText);
		GameScene.setColor(Color.red);
		setTextInScreen("YOU ARE SO NOOB !", getGameWidth(), getGameHeight() + (Game.TILES_SIZE * 6) * Game.SCALE, GameScene);
	}

	public void setLevelScene(Graphics GameScene, int mode) {
		GameScene.setColor(Color.black);
		GameScene.fillRect(0, 0, getGameWidth(), getGameHeight());
		mshi = new ImageIcon("src/NguHuynhDe/display/backgr.png").getImage();
		GameScene.drawImage(mshi,0, 0,getGameWidth(), getGameHeight(),
				null);
		Font GameText = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		GameScene.setFont(GameText);
		GameScene.setColor(Color.white);
		setTextInScreen("LEVEL " + mode, getGameWidth(), getGameHeight(), GameScene);
		
	}
	
	public void setPausedScene(Graphics GameScene) {
		Font GameText = new Font("Arial", Font.PLAIN, 20 * Game.SCALE);
		GameScene.setFont(GameText);
		GameScene.setColor(Color.white);
		setTextInScreen("PAUSED", getGameWidth(), getGameHeight(), GameScene);
		
	}
	
	
	
	public void setTextInScreen(String s, int w, int h, Graphics g) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / 2;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
	    g.drawString(s, x, y);
	 }
	
	public int getWidth() {
		return ScreenWidth;
	}
	
	public int getHeight() {
		return ScreenHeight;
	}
	
	public int getGameWidth() {
		return ScreenWidth * Game.SCALE;
	}
	
	public int getGameHeight() {
		return ScreenHeight * Game.SCALE;
	}
}
