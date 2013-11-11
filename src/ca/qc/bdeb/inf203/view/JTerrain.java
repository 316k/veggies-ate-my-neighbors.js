package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.Type;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JTerrain extends JPanel {
    private final int WIDTH = 800;
    private final int HEIGHT = 450;
    private ArrayList<SpriteManager> jEntites = new ArrayList<>();
    
    public JTerrain() {
        this.setLayout(null);
        this.setBackground(Color.red);
        this.add(new SpriteManager(new Type("grass", "ogm")));
        this.setVisible(true);
    }
}
