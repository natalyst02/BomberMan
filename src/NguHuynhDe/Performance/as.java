package NguHuynhDe.Performance;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class as extends JFrame {

    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public as(){
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                Frame rame =new Frame();
                rame.setVisible(true);

            }
        });
    }
}
