package ca.qc.bdeb.inf203.model;

/**
 * Contient les données relatives au joueur
 * (plantes disponibles, nombre de soleils, high-score, ...)
 * @author Nicolas Hurtubise
 */
public class Joueur {
    private int nbreSoleils;
    /**
     * Plantes et autres choses dans le genre débloquées.
     */
    private boolean debloques[] = {true,false,false,false,false,false};
}
