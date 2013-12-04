package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
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
        this.isGentil = true;
        this.attaqueRate = 0;
        this.vitesse = 70;
        this.attaque = 30;
        /**
         * @TODO Mettre les vrais width et height.
         */
        this.hitbox.width = 12;
        this.hitbox.height = 12;
        String[] path = {"plants", "pea"};
        this.animationFrameRate = 5;
        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etats.ATTAQUE, 1);
        this.nbrImagesAnimation.put(Etats.ATTENTE, 1);
        this.nbrImagesAnimation.put(Etats.DEPLACEMENT, 9);
        this.etat = Etats.DEPLACEMENT;
        this.sprite = new RepresentationImage(path);
    }

    @Override
    protected void attaquer() {
        super.attaquer();
        this.setVie(0);
    }

    @Override
    public Entite action() {
        return null;
    }

    @Override
    public void deplacer() {
        super.deplacer();
    }
}
