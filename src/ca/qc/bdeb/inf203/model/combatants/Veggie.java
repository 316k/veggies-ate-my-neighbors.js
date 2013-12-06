package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 * Veggie
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Veggie extends Combattant {

    public Veggie() {
        super();
        initialise();
    }

    @Override
    protected void initialise() {
        super.initialise();

        this.attaque = 1;
        this.gentil = false;
        this.hitbox.height = 80;
        this.hitbox.width = 60;
        this.animationFrameRate = 5;

        this.vie = 20;

        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 5);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 7);

        this.vitesseAction.put(Action.DEPLACEMENT, -16f);
        this.vitesseAction.put(Action.ATTAQUE, 0.6f);

        this.sprites.put(Etat.DEPLACEMENT, new RepresentationImage(new String[]{"veggies", "normal", "walking"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"veggies", "normal", "attack"}));
    }

    @Override
    public Entite action(int nbFois) {
        return null;
    }
}
