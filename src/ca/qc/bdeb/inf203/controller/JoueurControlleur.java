package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Item;
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
    
    public static Item[] getItems() {
        return joueur.getItems();
    }
    
    public static void setSelection(Integer selection) {
        joueur.setSelection(selection);
    }
    
    public static int getKills() {
        return joueur.getKills();
    }

    public static Integer getSelection() {
        return joueur.getSelection();
    }
}
