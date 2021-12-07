package NguHuynhDe.Performance.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import NguHuynhDe.Game;
import NguHuynhDe.Performance.Frame;

public class Options extends JMenu implements ChangeListener {

	Frame GameFrame;
	
	public Options(Frame frame) {
		super("Options");
		
		GameFrame = frame;
		
		JMenuItem pause = new JMenuItem("Pause");
		pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		pause.addActionListener(new MenuActionListener(frame));
		add(pause);
		
		JMenuItem resume = new JMenuItem("Resume");
		resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		resume.addActionListener(new MenuActionListener(frame));
		add(resume);
		
		addSeparator();
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	        int fps = (int)source.getValue();
	        
	        NguHuynhDe.Game.SCALE = fps;
	        System.out.println( Game.SCALE);
	        
	        GameFrame.GamePlaypane.changeSize();
			  GameFrame.revalidate();
			  GameFrame.pack();
	    }
	}
	
	class MenuActionListener implements ActionListener {
		public Frame GameFrame;
		public MenuActionListener(Frame frame) {
			GameFrame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("Pause")) {
				  GameFrame.pauseGame();
			  }
				  
			  if(e.getActionCommand().equals("Resume")) {
				  GameFrame.resumeGame();
			  }
		  }
	}
}
