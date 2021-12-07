package NguHuynhDe;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


import NguHuynhDe.exceptions.BombermanException;
import NguHuynhDe.display.Screen;
import NguHuynhDe.Performance.Frame;
import NguHuynhDe.input.Keyboard;

public class Game extends Canvas {
	
	/*
	|--------------------------------------------------------------------------
	| title game
	|--------------------------------------------------------------------------
	 */
	public static final double VERSION = 5.0;
	
	public static final int TILES_SIZE = 16,
							WIDTH = TILES_SIZE * (int)(31 / 2),
							HEIGHT = 13 * TILES_SIZE;

	public static int SCALE = 3;
	
	public static final String TITLE = "Bomberman NGU HUYNH DE " + VERSION;

	private static final int BOMBRATE = 1;
	private static final int BOMBRADIUS = 1;
	private static final double PLAYERSPEED = 1.0;
	
	public static final int TIME = 200;
	public static final int POINTS = 0;
	public static final int LIVES = 1;
	
	protected static int SCREENDELAY = 3;
	

	protected static int bombRate = BOMBRATE;
	protected static int bombRadius = BOMBRADIUS;
	protected static double playerSpeed = PLAYERSPEED;

	
	
	//FPS
	protected int ScreenGameDelay = SCREENDELAY;
	
	private Keyboard InputFromKeyboard;
	private boolean PlayerInRun = false;
	private boolean GPaused = true;
	
	private Board GameBoard;
	private Screen screen;
	private Frame GameFrame;
	
	//render 
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(Frame frame) throws BombermanException {
		GameFrame = frame;
		GameFrame.setTitle(TITLE);
		
		screen = new Screen(WIDTH, HEIGHT);
		InputFromKeyboard = new Keyboard();
		
		GameBoard = new Board(this, InputFromKeyboard, screen);
		addKeyListener(InputFromKeyboard);
	}
	
	
	private void renderGame() {
		BufferStrategy str = getBufferStrategy();
		if(str == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		GameBoard.render(screen);
		
		for (int i = 0; i < pixels.length; i++) { //load anh
			pixels[i] = screen.pixelInGame[i];
		}
		
		Graphics g = str.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		GameBoard.renderMessages(g);
		
		g.dispose(); 
		str.show();
	}
	
	private void renderScreen() {
		BufferStrategy str = getBufferStrategy();
		if(str == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		Graphics g = str.getDrawGraphics();
		
		GameBoard.drawScreen(g);

		g.dispose();
		str.show();
	}

	private void update() {
		InputFromKeyboard.update();
		GameBoard.update();
	}
	
	public void start() {
		PlayerInRun = true;
		
		long  lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; //
		double TimeDiff = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(PlayerInRun) {
			long now = System.nanoTime();
			TimeDiff += (now - lastTime) / ns;
			lastTime = now;
			while(TimeDiff >= 1) {
				update();
				updates++;
				TimeDiff--;
			}
			
			if(GPaused) {
				if(ScreenGameDelay <= 0) {
					GameBoard.setShow(-1);
					GPaused = false;
				}
					
				renderScreen();
			} else {
				renderGame();
			}
				
			
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				GameFrame.setTime(GameBoard.TimeUp());
				GameFrame.setPoints(GameBoard.getPoints());
				GameFrame.setLives(GameBoard.getLives());
				timer += 1000;
				GameFrame.setTitle(TITLE + " | " + updates + " rate, " + frames + " fps");
				updates = 0;
				frames = 0;
				
				if(GameBoard.getShow() == 2)
					--ScreenGameDelay;
			}
		}
	}
	
	/*
	|--------------------------------------------------------------------------
	| setup thong so
	|--------------------------------------------------------------------------
	 */
	public static double getPlayerSpeed() {
		return playerSpeed;
	}
	
	public static int getBombRate() {
		return bombRate;
	}
	
	public static int getBombRadius() {
		return bombRadius;
	}
	
	public static void addPlayerSpeed(double i) {
		playerSpeed += i;
	}
	
	public static void addBombRadius(int i) {
		bombRadius += i;
	}
	
	public static void addBombRate(int i) {
		bombRate += i;
	}

	
	//delay
	public int getScreenDelay() {
		return ScreenGameDelay;
	}
	
	public void decreaseScreenDelay() {
		ScreenGameDelay--;
	}
	
	public void resetScreenDelay() {
		ScreenGameDelay = SCREENDELAY;
	}

	public Keyboard getInput() {
		return InputFromKeyboard;
	}
	
	public Board getBoard() {
		return GameBoard;
	}
	
	public void run() {
		PlayerInRun = true;
		GPaused = false;
	}
	
	public void stop() {
		PlayerInRun = false;
	}
	
	public boolean isRunning() {
		return PlayerInRun;
	}
	
	public boolean isPaused() {
		return GPaused;
	}
	
	public void pause() {
		GPaused = true;
	}
	
}
