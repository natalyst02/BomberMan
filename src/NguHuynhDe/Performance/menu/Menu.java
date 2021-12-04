package NguHuynhDe.Performance.menu;

import javax.swing.JMenuBar;

import NguHuynhDe.Performance.Frame;

public class Menu extends JMenuBar {
	
	public Menu(Frame frame) {
		add( new Game(frame) );
		add( new Options(frame) );
		add( new Help(frame) );
	}
	
}
