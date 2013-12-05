package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.controller.CheatCodeControlleur;
import ca.qc.bdeb.inf203.controller.JoueurControlleur;
import ca.qc.bdeb.inf203.controller.TerrainControlleur;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Fenêtre de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class FenetrePrincipale extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 503;
    private JTerrain board;

    public FenetrePrincipale() {
        this.setTitle("Veggies Ate My Neighbors");
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        board = new JTerrain();

        // Codes secrets pour tricher (Chhhhuuuut !)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                CheatCodeControlleur.keyTyped(e);
            }
        });

        this.add(board);

        this.setVisible(true);
    }

    public void setMessage(String message) {
        board.setMessage(message, null);
    }

    public void setMessage(String message, Integer delais) {
        board.setMessage(message, delais);
    }

    public void setMessageBlink(boolean blink) {
        board.setMessageBlink(blink);
    }
}