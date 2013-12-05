package ca.qc.bdeb.inf203.model;

/**
 * États possibles pour les combatants
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public enum Etat {

    ATTAQUE("attaque"),
    DEPLACEMENT("deplacement"),
    ATTENTE("jesaispasquoimettre");
    public String nom;

    private Etat(String nom) {
        this.nom = nom;
    }
}
