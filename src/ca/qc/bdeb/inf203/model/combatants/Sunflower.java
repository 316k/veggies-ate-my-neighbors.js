package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etats;
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
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();
        this.isGentil = true;
        this.attaqueRate = 1;
        this.vitesse = 0;
        this.attaque = 0;
        this.hitbox.width = 80;
        this.hitbox.height = 80;
        this.lineOfSight = new Rectangle();
        this.animationFrameRate = 6;
        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etats.ATTENTE, 4);
        this.etat = Etats.ATTENTE;
        this.sprite = new RepresentationImage(new String[]{"plants", "sunflower"});
    }

    @Override
    public Entite tic() {
        Entite soleil = null;
        switch (etat) {
            case ATTENTE:
                soleil = action();
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
    public Entite action() {

        long ts = System.currentTimeMillis();
        Soleil soleil = null;

        if (this.soleil != null && !this.soleil.isUsed()) {
            dernierSoleilTimestamp = ts;
        } else if (ts - dernierSoleilTimestamp >= tempsGenerationSoleil * 1000.0) {
            soleil = new Soleil(25, hitbox.x, hitbox.y);
            dernierSoleilTimestamp = ts;
            this.soleil = soleil;
        }

        return soleil;
    }
}
