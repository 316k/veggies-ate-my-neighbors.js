package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.Entite;
import ca.qc.bdeb.inf203.model.Etat;

/**
 * Explosion nucléaire qui tue tout.
 *
 * @author Nicolas Hurtubise
 */
public class ExplosionNucleaire extends Entite {

    @Override
    public int getAnimationCompteur() {
        return 0;
    }

    @Override
    public Etat getEtat() {
        return Etat.ATTAQUE;
    }
}
