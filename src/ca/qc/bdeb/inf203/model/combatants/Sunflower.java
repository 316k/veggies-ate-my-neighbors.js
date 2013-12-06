package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import ca.qc.bdeb.inf203.model.powerups.Soleil;
import java.awt.Rectangle;
import java.util.HashMap;

/**
 * Plante Tire-pois.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Sunflower extends Combattant {

    private long dernierSoleilTimestamp = System.currentTimeMillis();
    /**
     * Nombre de secondes minimal avant la génération d'un soleil
     */
    private int tempsGenerationSoleil = 30; // 30
    private Soleil soleil = null;

    public Sunflower() {
        super();
        this.vie = 34;
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();
        this.isGentil = true;
        this.hitbox.width = 80;
        this.hitbox.height = 80;
        this.lineOfSight = new Rectangle();
        this.animationFrameRate = 6;

        this.nbrImagesAnimation.put(Etat.ATTENTE, 4);
        this.nbrImagesAnimation.put(Etat.DEPLACEMENT, 5);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 1);

        this.vitesseAction.put(Action.ACTION, 1 / 30f);

        this.sprites.put(Etat.ATTENTE, new RepresentationImage(new String[]{"plants", "sunflower"}));

        this.etat = Etat.ATTENTE;
    }

    @Override
    public Entite tic() {
        Entite soleil = null;
        switch (etat) {
            case ATTENTE:
                soleil = action(getNbrActions(Action.ACTION));
                break;
        }

        return soleil;
    }

    /**
     * L'action à distance d'un peashooter est de retourner un pois
     *
     * @return
     */
    @Override
    public Entite action(int nbFois) {

//        for (int i = 0; i < nbFois; i++) {
//
//            long ts = System.currentTimeMillis();
//            Soleil soleil = null;
//
//            if (this.soleil != null && !this.soleil.isUsed()) {
//                dernierSoleilTimestamp = ts;
//            } else if (ts - dernierSoleilTimestamp >= tempsGenerationSoleil * 1000.0) {
//                soleil = new Soleil(25, hitbox.x, hitbox.y);
//                dernierSoleilTimestamp = ts;
//                this.soleil = soleil;
//            }
//        }
        if (nbFois > 0) {
            return new Soleil(25, hitbox.x, hitbox.y);
        } else {
            return null;
        }
    }
}
