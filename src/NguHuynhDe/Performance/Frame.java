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
	
	public GameDisplay GamePlaypane;
	private JPanel _containerpane;
	private TaskBar _infopanel;
	private MainDisplay _MainPane;
	private final Audio _audio = new Audio();
	
	private Game GamePlay;

	public Frame() {
		setJMenuBar(new Menu(this));
		
		_containerpane = new JPanel(new BorderLayout());
		GamePlaypane = new GameDisplay(this);
		_infopanel = new TaskBar(GamePlaypane.getGame());
		_MainPane = new MainDisplay(GamePlaypane.getGame());
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
		//_containerpane.add(GamePlaypane, BorderLayout.PAGE_END);

		GamePlay = GamePlaypane.getGame();

			resetButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					_containerpane.remove(buttonPanel);
					_containerpane.add(_infopanel, BorderLayout.PAGE_START);

					_containerpane.add(GamePlaypane, BorderLayout.LINE_START);

					new Thread(){
						public void run(){
							GamePlay.start(); //the main recursive function
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
		GamePlay.start();

	}
	
	/*
	|--------------------------------------------------------------------------
	| Game Related
	|--------------------------------------------------------------------------
	 */
	public void newGame() {
		GamePlay.getBoard().newGame();
	}
	
	public void changeLevel(int i) {
		GamePlay.getBoard().changeLevel(i);
	}

	public void pauseGame() {
		GamePlay.getBoard().gamePause();
	}

	public void resumeGame() {
		GamePlay.getBoard().gameResume();
	}
	
	public boolean isRunning() {
		return GamePlay.isRunning();
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
		return GamePlay.getBoard().getLevel().validCode(str) != -1;
	}
	
	public void changeLevelByCode(String str) {
		GamePlay.getBoard().changeLevelByCode(str);
	}

}
