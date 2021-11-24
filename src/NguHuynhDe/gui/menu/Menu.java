package NguHuynhDe.gui.menu;

import javax.swing.JMenuBar;

import NguHuynhDe.gui.Frame;

public class Menu extends JMenuBar {
	
	public Menu(Frame frame) {
		add( new Game(frame) );
		add( new Options(frame) );
		add( new Help(frame) );
	}
	
}
