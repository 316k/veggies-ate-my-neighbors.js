package ca.qc.bdeb.inf203.model;

import java.util.ArrayList;

/**
 * Contient les données relatives au joueur (plantes disponibles, nombre de
 * soleils, high-score, ...)
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Joueur {

    private int nbrSoleils;
    private ArrayList<Item> items = new ArrayList<>();
    private Integer selection = null;

    public Joueur() {
        items.add(new Item("pea-shooter", 10));
        items.add(new Item("sunflower", 10));
        items.add(new Item("unknown", 10));
        items.add(new Item("unknown", 0));
        items.add(new Item("unknown", 0));
        nbrSoleils = 99999;
    }

    public int getSoleils() {
        return nbrSoleils;
    }

    public void setSelection(Integer selection) {
        this.selection = selection;
    }

    public int getSelection() {
        return selection;
    }

    public void setSoleils(int nbrSoleils) {
        this.nbrSoleils = nbrSoleils;
    }

    public void addSoleils(int nbrSoleils) {
        this.nbrSoleils += nbrSoleils;
    }

    public Item[] getItems() {
        return items.toArray(new Item[items.size()]);
    }

    public void debloquerItem(String nom, double vitesseRechargement) {
        items.add(new Item(nom, vitesseRechargement));
    }

    /**
     * Load la dernière save
     */
    public void loadSave() {
    }

    /**
     * Save dans le fichier de save (lol)
     */
    public void Save() {
    }
}
