/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model.typespowerups;

import ca.qc.bdeb.inf203.model.Item;
import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;

/**
 *
 * @author 1029172
 */
public class PlanteUnlock extends PowerUp {
    private Item item;
    private int largeur = 65;

    public PlanteUnlock(Item item) {
        super(item.getNom());
        this.item = item;
        this.hitbox = new Rectangle(x,y,largeur,hauteur);
        this.animation = 0;
    }

    @Override
    public void action() {
        Joueur.instance().debloquerItem(item);
    }
    
}
