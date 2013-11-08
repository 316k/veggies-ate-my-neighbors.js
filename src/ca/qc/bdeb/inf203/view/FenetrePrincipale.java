package ca.qc.bdeb.inf203.view;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas Hurtubise
 */
public class FenetrePrincipale extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 450;
    private GameBoard board;

    public FenetrePrincipale() {
        this.setTitle("Veggies Ate My Neighbors");
        this.setSize(new Dimension(WIDTH, HEIGHT));
        
        board = new GameBoard();
        
        this.add(board);
        this.setVisible(true);
    }
    
}