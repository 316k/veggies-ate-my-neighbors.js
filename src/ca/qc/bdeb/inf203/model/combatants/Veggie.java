package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Etats;
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
        this.vitesse = -16;
        this.animationFrameRate = 5;
        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etats.DEPLACEMENT, 5);
        this.nbrImagesAnimation.put(Etats.ATTAQUE, 1);
    }
    
    

    @Override
    public Combattant action() {
        return null;
    }
}
