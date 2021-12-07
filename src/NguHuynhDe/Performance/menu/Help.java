package NguHuynhDe.Performance.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import NguHuynhDe.Performance.Frame;
import NguHuynhDe.Performance.InfoDialog;

public class Help extends JMenu {

	public Help(Frame frame)  {
		super("Help");
		
		/*
		 * How to play
		 */
		JMenuItem instructions = new JMenuItem("How to play");
		instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		instructions.addActionListener(new MenuActionListener(frame));
		add(instructions);

		
	}
	
	class MenuActionListener implements ActionListener {
		public Frame GameFrame;
		public MenuActionListener(Frame frame) {
			GameFrame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("How to play")) {
				  new InfoDialog(GameFrame, "How to Play", "Movement: W,A,S,D or UP,DOWN, RIGHT, LEFT\nPut Bombs: SPACE, X", JOptionPane.QUESTION_MESSAGE);
			  }

			  
		  }
	}
}
