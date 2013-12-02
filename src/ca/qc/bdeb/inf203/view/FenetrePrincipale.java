package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.controller.JoueurControlleur;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas Hurtubise
 */
public class FenetrePrincipale extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 500;
    private JTerrain board;
    private String cheatCode = "";

    public FenetrePrincipale() {
        this.setTitle("Veggies Ate My Neighbors");
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        board = new JTerrain();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                cheatCode += e.getKeyChar();
                if (cheatCode.endsWith("galarneau")) {
                    JoueurControlleur.addSoleils(100);
                } else if (cheatCode.endsWith("Monsanté")) {
                    System.out.println("Ce code secret est protégé par les conventions internationnales sur la propriété intélectuelle. Tout contrevenant sera poursuivit en justice.");
                }

                // Les cheat codes font au plus 10 caractères
                if (cheatCode.length() == 10) {
                    cheatCode = cheatCode.substring(1, 10);
                }
            }
        });

        this.add(board);
        this.setVisible(true);
    }
}