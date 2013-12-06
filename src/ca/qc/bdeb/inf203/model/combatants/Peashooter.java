package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Action;
import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import ca.qc.bdeb.inf203.model.Terrain;
import java.util.ArrayList;
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
        this.gentil = true;
        this.hitbox.width = 80;
        this.hitbox.height = 80;
        this.lineOfSight.height = 80;
        this.lineOfSight.width = Terrain.TAILLE_CASE_X * Terrain.CASES_X;
        this.animationFrameRate = 6;
        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etat.ATTENTE, 4);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 11);
        this.etat = Etat.ATTENTE;

        this.vitesseAction.put(Action.ACTION, 1 / 4f);

        this.sprites.put(Etat.ATTENTE, new RepresentationImage(new String[]{"plants", "pea-shooter", "waiting"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"plants", "pea-shooter", "shooting"}));
    }

    @Override
    public Entite tic() {
        Entite pois = null;
        switch (etat) {
            case ATTAQUE:
                pois = action(getNbrActions(Action.ACTION));
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
    public Entite action(int nbFois) {
        ArrayList<Combattant> morts = new ArrayList<>();
        for (Combattant combattant : cibles) {
            if (combattant.getVie() <= 0) {
                morts.add(combattant);
            }
        }
        this.cibles.removeAll(morts);
        if (this.cibles.isEmpty()) {
            this.setEtat(Etat.ATTENTE);
            return null;

        }
        for (int i = 0; i < nbFois; i++) {

            Pois pois = new Pois();
            pois.getHitbox().setLocation(this.hitbox.x + 30, this.hitbox.y + 30);

            return pois;
        }
        return null;
    }
}
