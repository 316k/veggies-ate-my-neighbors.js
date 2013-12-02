package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Item;
import ca.qc.bdeb.inf203.model.Joueur;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JoueurControlleur {

    public static int getSoleils() {
        return Joueur.instance().getSoleils();
    }

    public static void addSoleils(int soleils) {
        Joueur.instance().addSoleils(soleils);
    }

    public static Item[] getItems() {
        return Joueur.instance().getItems();
    }

    public static void setSelection(Integer selection) {
        Joueur.instance().setSelection(selection);
    }

    public static int getKills() {
        return Joueur.instance().getKills();
    }

    public static Integer getSelection() {
        return Joueur.instance().getSelection();
    }
}
