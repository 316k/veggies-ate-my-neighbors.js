package ca.qc.bdeb.inf203.model.typespowerups;

import ca.qc.bdeb.inf203.model.Item;
import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Power-up débloquant un type de plante.
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class PlanteUnlock extends PowerUp {

    private Item item;
    private int largeur = 65;
    private int hauteur = 62;

    public PlanteUnlock(Item item, Point position) {
        super(item.getNom());
        this.item = item;
        this.hitbox = new Rectangle(position.x, position.y, largeur, hauteur);
        this.animation = 0;
    }

    @Override
    public void action() {
        Joueur.instance().debloquerItem(item);
    }
}
