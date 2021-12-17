package NguHuynhDe.Performance;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import NguHuynhDe.Game;
import NguHuynhDe.Except.GameExcept;

public class GameDisplay extends JPanel {

	private Game GamePlay;
	
	public GameDisplay(Frame frame) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
		
		try {
			GamePlay = new Game(frame);
			
			add(GamePlay);
			
			GamePlay.setVisible(true);
			
		} catch (GameExcept e) {
			e.printStackTrace();
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
