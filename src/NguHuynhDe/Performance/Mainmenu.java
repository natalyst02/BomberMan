package NguHuynhDe.Performance;

import NguHuynhDe.exceptions.BombermanException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainmenu extends JFrame  {
    private JButton button3;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    public  Frame rame ;

    public Mainmenu() {
        rame = new Frame();
       this.setContentPane(this.panel1);
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.pack();

        button3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(2);



               System.out.println(1);
                dispose();

                rame.GamePlaypane.getGame().start();
                System.out.println(0);
            }
        });
    }


    public static void main(String[] args) throws BombermanException {
        Mainmenu mainwindow = new Mainmenu();
        mainwindow.setVisible(true);

    }
}
