package NguHuynhDe.Performance;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModeNoti implements WindowListener, ActionListener {

	private Frame GameFrame;
	private JFrame NotificationMode;
	private JTextField _mode;
	private boolean checkbeTrue = false;

	public ModeNoti(Frame f) {
		GameFrame = f;

		NotificationMode = new JFrame("Enter a Level");
		final JButton button = new JButton("Load!");
		button.addActionListener(this);

		JPanel pane = new JPanel(new BorderLayout());
		_mode = new JTextField("");

		pane.add(new JLabel("Mode: "), BorderLayout.WEST);
		pane.add(_mode, BorderLayout.CENTER);
		pane.add(button, BorderLayout.PAGE_END);

		NotificationMode.getContentPane().add(pane);
		NotificationMode.setLocationRelativeTo(f);
		NotificationMode.setSize(400, 100);
		NotificationMode.setVisible(true);
		NotificationMode.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		NotificationMode.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		GameFrame.pauseGame();
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if(checkbeTrue == false)
			GameFrame.resumeGame();
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code = _mode.getText();

		if(GameFrame.trueMode(code)) {
			GameFrame.changeLevelByCode(code);
			checkbeTrue = true;
			NotificationMode.dispose();
		} else {
			JOptionPane.showMessageDialog(GameFrame,
				    "Wrong!!!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
