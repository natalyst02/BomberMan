package NguHuynhDe.Performance;

import NguHuynhDe.Game;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private JButton redButton;
    private JButton blueButton;
    public JButton resetButton;
    private JPanel buttonPanel;
    public MainPanel(Game game){
        setLayout(new GridLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10, 80);
        buttonPanel.setSize(250, 70);
        this.add(buttonPanel);
        JButton redButton = new JButton("Red Score!");
        redButton.setLocation(0, 0);
        redButton.setSize(100, 30);
        buttonPanel.add(redButton);

        JButton blueButton = new JButton("Blue Score!");
        blueButton.setLocation(120, 0);
        blueButton.setSize(100, 30);
        buttonPanel.add(blueButton);

        JButton resetButton = new JButton("Reset Score");
        resetButton.setLocation(0, 40);
        resetButton.setSize(220, 30);
        buttonPanel.add(resetButton);
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));
        setBackground(Color.black);

    }



}
