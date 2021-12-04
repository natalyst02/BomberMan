package NguHuynhDe.Performance.menu;

import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel {

        private Image img;

        public ImagePanel(String img) {
                this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) {
                this.img = img;
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
                setPreferredSize(size);
                setMinimumSize(size);

                setLayout( null);
        }

        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(img, 0, 0, null);
        }
}
