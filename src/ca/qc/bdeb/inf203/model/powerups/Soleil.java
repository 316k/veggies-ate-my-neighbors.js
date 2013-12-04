package ca.qc.bdeb.inf203.model.powerups;

import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import java.awt.Dimension;
import java.awt.Point;
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
        this.animationFrameRate = 12;
        this.nbrImagesAnimation = 4;
        this.vitesse = 60;
        this.destination = null;
    }

    public Soleil(int valeur, Point position, Point destination) {
        super("sun");
        this.valeur = valeur;
        this.hitbox.setLocation(position);
        this.hitbox.setSize(new Dimension(largeur, hauteur));
        this.vitesse = 60;
        this.destination = destination;

        this.animationFrameRate = 12;
        this.nbrImagesAnimation = 4;
    }

    @Override
    public void action() {
        Joueur.instance().addSoleils(valeur);
    }
}
