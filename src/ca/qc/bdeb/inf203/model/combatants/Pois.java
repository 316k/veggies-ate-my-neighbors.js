package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
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
        super.initialise();
        this.isGentil = true;
        this.vitesse = 70;
        this.attaque = 30;
        this.vitesseAction.put(Action.DEPLACEMENT, 70f);
        this.vitesseAction.put(Action.ATTAQUE, 70f);
        this.derniereActionTS.put(Action.ATTAQUE, System.currentTimeMillis());
        this.derniereActionTS.put(Action.DEPLACEMENT, System.currentTimeMillis());
        /**
         * @TODO Mettre les vrais width et height.
         */
        this.hitbox.width = 12;
        this.hitbox.height = 12;
        String[] path = {"plants", "pea"};
        this.animationFrameRate = 5;
        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 1);
        this.nbrImagesAnimation.put(Etat.ATTENTE, 1);
        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 9);

        this.etat = Etat.DEPLACEMENT;
        this.sprite = new RepresentationImage(path);
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
