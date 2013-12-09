package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import static ca.qc.bdeb.inf203.model.Etat.ATTAQUE;
import static ca.qc.bdeb.inf203.model.Etat.DEPLACEMENT;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Rectangle;
import java.util.HashMap;
import javax.swing.AbstractAction;

/**
 * Veggie
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class VeggieHitler extends Combattant {

    public VeggieHitler() {
        super();
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();

        this.attaque = 1;
        this.gentil = false;
        this.hitbox.height = 80;
        this.hitbox.width = 60;
        this.animationFrameRate = 5;

        this.lineOfSight = new Rectangle();
        this.lineOfSight = new Rectangle();

        this.vie = 1;

        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 6);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 1);

        this.vitesseAction.put(Action.DEPLACEMENT, -70f);
        this.vitesseAction.put(Action.ATTAQUE, 0.6f);
        this.vitesseAction.put(Action.ACTION, 0.7f);

        this.sprites.put(Etat.DEPLACEMENT, new RepresentationImage(new String[]{"veggies", "hitler", "walking"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"veggies", "hitler", "walking"}));
    }

    @Override
    public Entite tic() {
        // effectuer l'action par rapport à l'état.
        Entite swas = null;
        switch (etat) {
            case DEPLACEMENT:
                deplacer(getNbrActions(Action.DEPLACEMENT));
            case ATTAQUE:
                swas = action(getNbrActions(Action.ACTION));
                break;
        }

        return swas;
    }

    /**
     * 
     *
     * @return une nouvelle Swastika.
     */
    @Override
    public Entite action(int nbrFois) {

        if (nbrFois > 0) {

            Swastika swastika = new Swastika();
            swastika.getHitbox().setLocation(this.hitbox.x + 30, this.hitbox.y + 30);

            swastika.setGentil(this.isGentil());

            HashMap<Action, Float> vitesses = new HashMap<>();

            vitesses.put(Action.DEPLACEMENT, (this.gentil ? 1 : -1) * Math.abs(swastika.getVitesseAction().get(Action.DEPLACEMENT)));
            vitesses.put(Action.ATTAQUE, swastika.getVitesseAction().get(Action.ATTAQUE));

            swastika.setVitesseAction(vitesses);

            return swastika;
        }

        return null;
    }
}
