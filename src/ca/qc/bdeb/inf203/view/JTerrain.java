package ca.qc.bdeb.inf203.view;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JTerrain extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 450;
    
    public JTerrain() {
        this.setBackground(Color.red);
        this.setVisible(true);
    }
}
