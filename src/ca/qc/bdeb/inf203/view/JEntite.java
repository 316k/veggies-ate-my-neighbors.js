package ca.qc.bdeb.inf203.view;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * Partie graphique des entités.
 *
 * @author guillaume
 */
public class JEntite extends JComponent {

    /**
     * array à deux dim : [Etat de l'entitée][Sprite de l'animation]
     */
    private BufferedImage sprite[][];
    private int compteur;
    private int etat;

    public BufferedImage getSprite() {
        BufferedImage resultat = sprite[etat][compteur];

        if (compteur >= sprite[etat].length) {
            compteur = 0;
        } else {
            compteur++;
        }
        return resultat;
    }
}
