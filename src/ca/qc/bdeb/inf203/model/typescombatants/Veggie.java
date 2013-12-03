package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.RepresentationImage;

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
    }

    
    @Override
    public Combattant action() {
        return null;
    }


    
}
