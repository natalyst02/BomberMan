package NguHuynhDe.Performance.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import NguHuynhDe.Performance.ModeNoti;
import NguHuynhDe.Performance.Frame;
import NguHuynhDe.Performance.InfoBar;

public class Game extends JMenu {

	public Frame frame;
	
	public Game(Frame frame) {
		super("Game");
		this.frame = frame;
		
		/*
		 * New Game
		 */
		JMenuItem newgame = new JMenuItem("New Game");
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newgame.addActionListener(new MenuActionListener(frame));
		add(newgame);
		
		/*
		 * Scores
		 */
		JMenuItem scores = new JMenuItem("High Scores");
		scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		scores.addActionListener(new MenuActionListener(frame));
		add(scores);
		
		/*
		 * Mode
		 */
		JMenuItem Mode = new JMenuItem("Mode");
		Mode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		Mode.addActionListener(new MenuActionListener(frame));
		add(Mode);
	}
	
	class MenuActionListener implements ActionListener {
		public Frame GameFrame;
		public MenuActionListener(Frame frame) {
			GameFrame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("New Game")) {
				  GameFrame.newGame();
			  }
			  
			  if(e.getActionCommand().equals("High Scores")) {
				  new InfoBar(GameFrame, "High Scores", "YOU SHOULD PLAY ONE MORE", JOptionPane.INFORMATION_MESSAGE);
			  }
			  
			  if(e.getActionCommand().equals("Mode")) {
				  new ModeNoti(GameFrame);
			  }

		  }
		}

}
