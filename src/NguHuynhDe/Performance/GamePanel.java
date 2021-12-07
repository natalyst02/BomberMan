package NguHuynhDe.Performance;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import NguHuynhDe.Game;
import NguHuynhDe.exceptions.BombermanException;

public class GamePanel extends JPanel {

	private Game GamePlay;
	
	public GamePanel(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		
		try {
			GamePlay = new Game(frame);
			
			add(GamePlay);
			
			GamePlay.setVisible(true);
			
		} catch (BombermanException e) {
			e.printStackTrace();
			//TODO: so we got a error hum..
			System.exit(0);
		}
		setVisible(true);
		setFocusable(true);
		
	}
	
	public void changeSize() {
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		revalidate();
		repaint();
	}
	
	public Game getGame() {
		return GamePlay;
	}
	
}
