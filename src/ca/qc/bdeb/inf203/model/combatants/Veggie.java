package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.util.HashMap;

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
    protected final void initialise() {
        super.initialise();
        this.isGentil = false;
        this.sprite = new RepresentationImage(new String[]{"veggies", "normal"});
        this.hitbox.height = 80;
        this.hitbox.width = 60;
        this.attaque = 1;
        this.vitesse = -16; // -16
        this.animationFrameRate = 5;
        this.nbrImagesAnimation = new HashMap<>();
        
        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 5);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 1);
        
        this.vitesseAction.put(Action.DEPLACEMENT, -16f);
        this.vitesseAction.put(Action.ATTAQUE, 0.6f);
        this.derniereActionTS.put(Action.DEPLACEMENT,System.currentTimeMillis());
        this.derniereActionTS.put(Action.ATTAQUE,System.currentTimeMillis());
    }

    @Override
    public Entite action(int nbFois) {
        return null;
    }
}
