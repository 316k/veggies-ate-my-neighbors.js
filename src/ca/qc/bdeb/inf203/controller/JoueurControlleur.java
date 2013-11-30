package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Joueur;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JoueurControlleur {
    
    
    public static int getSoleils() {
        return Joueur.getInstance().getSoleils();
    }
    
    public static void addSoleils(int soleils) {
        Joueur.getInstance().addSoleils(soleils);
    }

}
