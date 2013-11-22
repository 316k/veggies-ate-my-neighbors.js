package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import ca.qc.bdeb.inf203.model.Type;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JTerrain extends JPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 450;

    public JTerrain() {
        this.setLayout(null);

        this.setBackground(Color.red);
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] colorisation = {0, 0, 0};
        String[] path = {"grass", "ogm", "burned"};

        RepresentationImage ri = new RepresentationImage(colorisation, path);

        SpriteManager.getSprite(ri, 0);
    }
}
