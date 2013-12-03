package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Etats;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.util.HashMap;

/**
 * Pois (lancé par un tire-pois)
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Pois extends Combattant implements Cloneable {

    public Pois() {
        super();
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise(); //To change body of generated methods, choose Tools | Templates.
        this.attaqueRate = 0;
        this.vitesse = 70;
        this.attaque = 30;
        /**
         * @TODO Mettre les vrais width et height.
         */
        this.hitbox.width = 15;
        this.hitbox.height = 15;
        String[] path = {"plants", "pea"};
        this.nbrImagesParActions = new HashMap<>();
        this.nbrImagesParActions.put(Etats.ATTAQUE, 5);
        this.nbrImagesParActions.put(Etats.ATTENTELIGNEDEVUE, 5);
        this.nbrImagesParActions.put(Etats.DEPLACEMENT, 5);
        this.sprite = new RepresentationImage(path);
    }

    @Override
    protected void attaquer() {
        super.attaquer();
        this.setVie(0);
    }

    @Override
    public Combattant action() {
        return null;
    }
}
