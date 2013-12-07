package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.*;
import java.util.HashMap;

/**
 * Rose nucléaire qui tue tout sur le terrain.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class AtomicRose extends Combattant {

    public AtomicRose() {
        super();
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();
        this.vie = 30;
        this.gentil = true;
        this.hitbox.width = 80;
        this.hitbox.height = 80;
        this.lineOfSight.x = 0;
        this.lineOfSight.y = 0;
        this.lineOfSight.width = Terrain.TAILLE_CASE_X * Terrain.CASES_X;
        this.lineOfSight.height = Terrain.TAILLE_CASE_Y * Terrain.CASES_Y;

        this.animationFrameRate = 6;

        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etat.ATTENTE, 1);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 11);
        this.etat = Etat.ATTENTE;

        this.vitesseAction.put(Action.ATTAQUE, 1 / 3f);
        this.vitesseAction.put(Action.ACTION, 1 / 3f);
        

        this.sprites.put(Etat.ATTENTE, new RepresentationImage(new String[]{"plants", "atomic-rose"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"explosion"}));
    }

    @Override
    public Entite tic() {
        Entite entite = null;
        switch (etat) {
            case ATTAQUE:
                entite = action(getNbrActions(Action.ATTAQUE));
                break;
        }

        return entite;
    }

    /**
     * 
     * @param nbFois si c'est 0, pas d'explosion, sinon, explose.
     * @return une explosion.
     */
    @Override
    public Entite action(int nbFois) {

        if (nbFois > 0) {
            return new ExplosionNucleaire();
        }

        return null;
    }
}
