package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.controller.CheatCodeControlleur;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 * Fenêtre de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class FenetrePrincipale extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 503;
    /**
     * Composant d'affichage du terrain.
     */
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
    /**
     * Affiche le message passé en paramètre.
     * @param message le message
     */
    public void setMessage(String message) {
        board.setMessage(message, null);
    }
    /**
     * Affiche le message durant un temps défini par le 
     * paramètre délais.
     * @param message
     * @param delais 
     */
    public void setMessage(String message, Integer delais) {
        board.setMessage(message, delais);
    }
    
    /**
     * Fais clignotter ou non le message en cours d'affichage.
     * @param blink true : clignotte, false : ne clignotte pas.
     */
    public void setMessageBlink(boolean blink) {
        board.setMessageBlink(blink);
    }
}