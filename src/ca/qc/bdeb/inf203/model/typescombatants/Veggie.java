package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Etats;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.util.HashMap;

/**
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Veggie extends Combattant {

    public Veggie(Combattant c) {
        super(c);
    }

    public Veggie() {
        this.sprite = new RepresentationImage(new String[]{"veggies", "normal"});
        this.nbImagesParActions = new HashMap<>();
        this.nbImagesParActions.put(Etats.DEPLACEMENT, 5);
        this.nbImagesParActions.put(Etats.ATTAQUE, 5);
    }

    
    @Override
    public Combattant action() {
        return null;
    }


    
}
