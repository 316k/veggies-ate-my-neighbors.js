package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JTerrain extends JPanel {

    private final int WIDTH = 800;  // (10 * 80px)
    private final int HEIGHT = 480; // (6 * 80px)

    public JTerrain() {
        this.setLayout(null);

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] colorisation = {0, 0, 0};
        int animation = (int) (System.currentTimeMillis() / 1000 % 2);
        String[] path = {"grass", "ogm", "burned"};

        RepresentationImage ri = new RepresentationImage(colorisation, path);

        g.drawImage(SpriteManager.getSprite(ri, animation), 10, 10, this);

        repaint();

        try {
            Thread.sleep(1000);
            this.invalidate();
        }
        catch (InterruptedException ex) {
            Logger.getLogger(JTerrain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
