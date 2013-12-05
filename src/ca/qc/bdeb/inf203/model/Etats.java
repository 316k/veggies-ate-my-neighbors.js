package ca.qc.bdeb.inf203.model;

/**
 * États possibles pour les combatants
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public enum Etats {

    ATTAQUE("attaque"),
    DEPLACEMENT("deplacement"),
    ATTENTE("jesaispasquoimettre");
    public String nom;

    private Etats(String nom) {
        this.nom = nom;
    }
}
