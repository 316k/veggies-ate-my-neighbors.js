
package ca.qc.bdeb.inf203.model;

/**
 * À faire après la remise.
 * 
 * Équipement sur un combatant ...  Mais non.
 * @author Guillaume Riou
 */
public class Equipement {
    /**
     * De 0 à 1 : réduction de dégats en %.
     */
    private float defense;
    /**
     * Vies de l'équipement.
     */
    private int vies;
    /**
     * Augmentation des dégats en %.
     */
    private float attaque;
    /**
     * Si on peut le détruire.
     */
    private boolean endommageable;
    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public int getVies() {
        return vies;
    }

    public void incrementVies(int vies) {
        this.vies += vies;
    }

    public float getAttaque() {
        return attaque;
    }

    public void setAttaque(float attaque) {
        this.attaque = attaque;
    }

    public boolean isEndommageable() {
        return endommageable;
    }

    public void setEndommageable(boolean endommageable) {
        this.endommageable = endommageable;
    }
    
    
}
