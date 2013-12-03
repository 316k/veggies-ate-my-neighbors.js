package ca.qc.bdeb.inf203.model.typespowerups;

import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import java.awt.Rectangle;

/**
 * Power-up conférant des unités solaires au joueur qui les rammasse.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Soleil extends PowerUp {

    /**
     * Nombre de soleils conférés par le clic sur le power-up
     */
    private int valeur;
    private int hauteur = 30;
    private int largeur = 30;

    public Soleil(int valeur, int x, int y) {
        super("sun");
        this.valeur = valeur;
        this.hitbox = new Rectangle(x, y, largeur, hauteur);
        this.animation = 0;
    }

    @Override
    public void action() {
        Joueur.instance().addSoleils(valeur);
    }
}
