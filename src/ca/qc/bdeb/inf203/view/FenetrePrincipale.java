package ca.qc.bdeb.inf203.view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas Hurtubise
 */
public class FenetrePrincipale extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 450;

    public FenetrePrincipale() {
        this.setTitle("Veggies Ate My Neighbors");
        this.setSize(new Dimension(WIDTH, HEIGHT));
        
        this.setVisible(true);
    }
    
}