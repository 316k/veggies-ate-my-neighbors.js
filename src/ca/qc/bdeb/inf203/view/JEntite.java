package ca.qc.bdeb.inf203.view;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * Partie graphique des entités.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JEntite extends JComponent {

    /**
     * array à deux dim : [Etat de l'entitée][Sprite de l'animation]
     */
    private BufferedImage sprites[][];
    private int compteur;
    private int etat;
    
    public JEntite(Type type) {
        // Load les sprites
        sprites = new BufferedImage[type.type][type];
    }
    
    public BufferedImage getSprite() {
        BufferedImage resultat = sprites[etat][compteur];

        if (compteur >= sprites[etat].length) {
            compteur = 0;
        } else {
            compteur++;
        }
        return resultat;
    }
}
