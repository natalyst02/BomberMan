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
	private JPanel TaskBarPane;
	private TaskBar panelTaskBar;
	private MainDisplay _MainPane;
	private final Audio musicGame = new Audio();
	
	private Game GamePlay;

	public Frame() {
		setJMenuBar(new Menu(this));
		
		TaskBarPane = new JPanel(new BorderLayout());
		GamePlaypane = new GameDisplay(this);
		panelTaskBar = new TaskBar(GamePlaypane.getGame());
		_MainPane = new MainDisplay(GamePlaypane.getGame());
		ImageIcon icon=  new ImageIcon("res/textures/background11.png");
		ImagePanel buttonPanel = new ImagePanel(icon.getImage());

		TaskBarPane.add(buttonPanel);
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
		//TaskBarPane.add(panelTaskBar, BorderLayout.PAGE_START);
		//TaskBarPane.add(GamePlaypane, BorderLayout.PAGE_END);

		GamePlay = GamePlaypane.getGame();

			resetButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					TaskBarPane.remove(buttonPanel);
					TaskBarPane.add(panelTaskBar, BorderLayout.PAGE_START);

					TaskBarPane.add(GamePlaypane, BorderLayout.LINE_START);

					new Thread(){
						public void run(){
							GamePlay.start(); //the main recursive function
						}
					}.start();
				}
			});



		TaskBarPane.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		add(TaskBarPane);
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		musicGame.playSound("res/sounds/audiopb.wav",100);
		GamePlay.start();

	}
	
	/*
	|--------------------------------------------------------------------------
	| Contrustor
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
		panelTaskBar.setTime(time);
	}
	
	public void setLives(int lives) {
		panelTaskBar.setLives(lives);
	}
	
	public void setPoints(int points) {
		panelTaskBar.setPoints(points);
	}
	
	public boolean trueMode(String str) {
		return GamePlay.getBoard().getLevel().trueMode(str) != -1;
	}
	
	public void changeLevelByCode(String str) {
		GamePlay.getBoard().changeLevelByCode(str);
	}

}
