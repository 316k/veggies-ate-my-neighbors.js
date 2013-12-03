package ca.qc.bdeb.inf203.model.typescombatants;

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
        this.sprite = new RepresentationImage(new String[]{"veggies", "normal"});
        this.hitbox.height = 80;
        this.hitbox.width = 60;
        this.nbrImagesParActions = new HashMap<>();
        this.nbrImagesParActions.put(Etats.DEPLACEMENT, 5);
        this.nbrImagesParActions.put(Etats.ATTAQUE, 5);
    }
    
    

    @Override
    public Combattant action() {
        return null;
    }
}
