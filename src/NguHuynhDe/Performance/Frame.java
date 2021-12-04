package NguHuynhDe.Performance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
 import javax.swing.ImageIcon;
import NguHuynhDe.Game;
import NguHuynhDe.Performance.menu.ImagePanel;
import NguHuynhDe.Performance.menu.Menu;
import NguHuynhDe.music.Audio;

public class Frame extends JFrame {
	
	public GamePanel _gamepane;
	private JPanel _containerpane;
	private InfoPanel _infopanel;
	private MainPanel _MainPane;
	private final Audio _audio = new Audio();
	
	private Game _game;

	public Frame() {
		setJMenuBar(new Menu(this));
		
		_containerpane = new JPanel(new BorderLayout());
		_gamepane = new GamePanel(this);
		_infopanel = new InfoPanel(_gamepane.getGame());
		_MainPane = new MainPanel(_gamepane.getGame());
		ImageIcon icon=  new ImageIcon("res/textures/background11.png");
		ImagePanel buttonPanel = new ImagePanel(icon.getImage());

		_containerpane.add(buttonPanel);
		JButton redButton = new JButton("TWO PLAYER");
		redButton.setLocation(260, 200);
		redButton.setSize(140, 30);
		buttonPanel.add(redButton);

		JButton blueButton = new JButton("AI PLAY");
		blueButton.setLocation(260, 250);
		blueButton.setSize(140, 30);
		buttonPanel.add(blueButton);

		JButton resetButton = new JButton("NEW GAME");
		resetButton.setLocation(260, 300);
		resetButton.setSize(140, 30);

		buttonPanel.add(resetButton);
		//_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		//_containerpane.add(_gamepane, BorderLayout.PAGE_END);

		_game = _gamepane.getGame();

			resetButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					_containerpane.remove(buttonPanel);
					_containerpane.add(_infopanel, BorderLayout.PAGE_START);

					_containerpane.add(_gamepane, BorderLayout.LINE_START);

					new Thread(){
						public void run(){
							_game.start(); //the main recursive function
						}
					}.start();
				}
			});



		_containerpane.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		add(_containerpane);
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		_audio.playSound("res/sounds/audiopb.wav",100);
		_game.start();

	}
	
	/*
	|--------------------------------------------------------------------------
	| Game Related
	|--------------------------------------------------------------------------
	 */
	public void newGame() {
		_game.getBoard().newGame();
	}
	
	public void changeLevel(int i) {
		_game.getBoard().changeLevel(i);
	}

	public void pauseGame() {
		_game.getBoard().gamePause();
	}

	public void resumeGame() {
		_game.getBoard().gameResume();
	}
	
	public boolean isRunning() {
		return _game.isRunning();
	}
	
	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	
	public void setLives(int lives) {
		_infopanel.setLives(lives);
	}
	
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}
	
	public boolean validCode(String str) {
		return _game.getBoard().getLevel().validCode(str) != -1;
	}
	
	public void changeLevelByCode(String str) {
		_game.getBoard().changeLevelByCode(str);
	}

}
