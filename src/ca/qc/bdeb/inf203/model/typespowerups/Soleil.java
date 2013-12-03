package ca.qc.bdeb.inf203.model.typespowerups;

import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import java.awt.Rectangle;

/**
 *
 * @author 1029172
 */
public class Soleil extends PowerUp {

    private int valeur;
    /**
     * Faut synchroniser avec les sprites.
     */
    private int hauteur = 30;
    private int largeur = 30;
    public Soleil(int valeur,int x, int y) {
        super("sun");
        this.valeur = valeur;
        this.hitbox = new Rectangle(x,y,largeur,hauteur);
        this.animation = 0;
    }

    @Override
    public void action() {
        Joueur.instance().addSoleils(valeur);
    }
}
