package ca.qc.bdeb.inf203.view;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas Hurtubise
 */
public class FenetrePrincipale extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 450;
    private JTerrain board;

    public FenetrePrincipale() {
        this.setTitle("Veggies Ate My Neighbors");
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board = new JTerrain();
        
        this.add(board);
        this.setVisible(true);
    }
    
}