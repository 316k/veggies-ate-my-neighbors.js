package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 * Swastika (lancé par un Hitler végétarien).
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Swastika extends Combattant implements Projectile, Cloneable {

    public Swastika() {
        super();
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();
        this.gentil = false;
        this.vitesseAction.put(Action.DEPLACEMENT, -190f);
        this.vitesseAction.put(Action.ATTAQUE, 70f);
        this.hitbox.width = 12;
        this.hitbox.height = 12;
        this.animationFrameRate = 19;
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 6);
        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 6);

        this.attaque = 5;

        this.etat = Etat.DEPLACEMENT;

        this.sprites.put(Etat.DEPLACEMENT, new RepresentationImage(new String[]{"swastika"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"swastika"}));
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
