package NguHuynhDe.Performance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import NguHuynhDe.Game;

public class TaskBar extends JPanel {
	
	private JLabel timeShow;
	private JLabel pointShow;
	private JLabel livesShow;

	public TaskBar(Game game) {
		setLayout(new GridLayout());
		
		timeShow = new JLabel("Time: " + game.getGBoard().getTime());
		timeShow.setForeground(Color.white);
		timeShow.setHorizontalAlignment(JLabel.CENTER);
		
		pointShow = new JLabel("Points: " + game.getGBoard().getPoints());
		pointShow.setForeground(Color.white);
		pointShow.setHorizontalAlignment(JLabel.CENTER);
		
		livesShow = new JLabel("Lives: " + game.getGBoard().getLives());
		livesShow.setForeground(Color.white);
		livesShow.setHorizontalAlignment(JLabel.CENTER);
		
		add(timeShow);
		add(pointShow);
		add(livesShow);
		
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 40));

	}
	
	public void setTime(int t) {
		timeShow.setText("Time: " + t);
	}

	public void setLives(int t) {
		livesShow.setText("Lives: " + t);
		
	}

	public void setPoints(int t) {
		pointShow.setText("Points: " + t);		
	}
	
}
