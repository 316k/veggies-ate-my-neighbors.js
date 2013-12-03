package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Etats;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import ca.qc.bdeb.inf203.model.Terrain;
import java.util.HashMap;

/**
 * Plante Tire-pois.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Peashooter extends Combattant {

    public Peashooter() {
        super();
        initialise();
    }
    
    @Override
    protected final void initialise() {
        super.initialise();
        this.attaqueRate = 1;
        this.vitesse = 0;
        this.attaque = 30;
        this.hitbox.width = 80;
        this.hitbox.height = 80;
        this.lineOfSight.height = 80;
        this.lineOfSight.width = Terrain.TAILLE_CASE_X * Terrain.CASES_X;
        this.nbrImagesParActions = new HashMap<>();
        this.nbrImagesParActions.put(Etats.DEPLACEMENT, 4);
        this.nbrImagesParActions.put(Etats.ATTENTELIGNEDEVUE, 11);
        this.nbrImagesParActions.put(Etats.ATTAQUE, 3);
        this.etat = Etats.ATTENTELIGNEDEVUE;
        this.sprite = new RepresentationImage(new String[]{"plants", "pea-shooter", "normal"});
    }

    @Override
    public Combattant tic() {
        Combattant pois = null;
        switch (etat) {
            case ATTAQUE:
                pois = action();
                break;
            case DEPLACEMENT:
                deplacer();
                break;

        }
        return pois;
    }

    /**
     * L'action à distance d'un peashooter est de retourner un pois
     *
     * @return
     */
    @Override
    public Combattant action() {
        boolean tousMorts = true;
        for (Combattant combattant : cibles) {
            if (combattant.getVie() > 0) {
                tousMorts = false;
            }
        }
        if (tousMorts) {
            this.etat = Etats.ATTENTELIGNEDEVUE;
        }
        Combattant pois = new Pois();
        pois.getHitbox().setLocation(this.hitbox.x + 190, this.hitbox.y);

        return pois;
    }
}
