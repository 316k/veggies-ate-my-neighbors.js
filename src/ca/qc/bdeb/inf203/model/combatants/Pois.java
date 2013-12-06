package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 * Pois (lancé par un tire-pois)
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Pois extends Combattant implements Projectile, Cloneable {

    public Pois() {
        super();
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();
        this.gentil = true;
        this.vitesseAction.put(Action.DEPLACEMENT, 70f);
        this.vitesseAction.put(Action.ATTAQUE, 70f);
        this.hitbox.width = 12;
        this.hitbox.height = 12;
        this.animationFrameRate = 5;
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 1);
        this.nbrImagesAnimation.put(Etat.ATTENTE, 1);
        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 9);
        
        this.attaque = 20;
        
        this.etat = Etat.DEPLACEMENT;
        this.sprites.put(Etat.DEPLACEMENT, new RepresentationImage(new String[]{"plants", "pea"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"plants", "pea"}));
    }

    @Override
    protected void attaquer(int nbFois) {
        super.attaquer(nbFois);
        if (nbFois > 0) {
            this.setVie(0);
        }
    }

    @Override
    public Entite action(int nbFois) {
        return null;
    }
}
