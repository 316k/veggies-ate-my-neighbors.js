package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Item;
import ca.qc.bdeb.inf203.model.Joueur;

/**
 *
 * @author Nicolas Hurtubise
 */
public class JoueurControlleur {
    /**
     * 
     * @return le nombre de soleil du joueur
     */
    public static int getSoleils() {
        return Joueur.instance().getSoleils();
    }
    /**
     * Ajoute un nombre de soleil au joueur.
     * @param soleils nb à ajouter 
     */
    public static void addSoleils(int soleils) {
        Joueur.instance().addSoleils(soleils);
    }
    /**
     * Va chercher les items du joueur.
     * @return 
     */
    public static Item[] getItems() {
        return Joueur.instance().getItems();
    }
    /**
     * Met la sélection du joueur à l'index spécifié par le paramètre.
     * @param selection sélection.
     */
    public static void setSelection(Integer selection) {
        Joueur.instance().setSelection(selection);
    }
    /**
     * 
     * @return le nombre de veggies tués.
     */
    public static int getKills() {
        return Joueur.instance().getKills();
    }
    /**
     * 
     * @return l'index de la sélection du joueur.
     */
    public static Integer getSelection() {
        return Joueur.instance().getSelection();
    }

    /**
     * Secret.
     */
    static void easterEgg() {
        Item items[] = Joueur.instance().getItems();
        for (Item item : items) {
            item.setVitesseRechargement(100000);
        }
    }
}
