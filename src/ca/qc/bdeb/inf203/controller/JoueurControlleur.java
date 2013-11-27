package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Joueur;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JoueurControlleur {
    
    private static Joueur joueur = new Joueur();
    
    public static int getSoleils() {
        return joueur.getSoleils();
    }
    
    public static void addSoleils(int soleils) {
        joueur.setSoleils(joueur.getSoleils() + soleils);
    }

}
