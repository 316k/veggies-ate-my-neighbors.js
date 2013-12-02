package ca.qc.bdeb.inf203.model;

import java.util.ArrayList;

/**
 * Contient les données relatives au joueur (plantes disponibles, nombre de
 * soleils, high-score, ...)
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Joueur {
    
    private static Joueur instance = new Joueur();

    private int nbrSoleils;
    private ArrayList<Item> items = new ArrayList<>();
    private Integer selection = null;
    private int kills = 0;

    public int getKills() {
        return kills;
    }

    public void setScore(int score) {
        this.kills = score;
    }
    
    public static Joueur instance() {
        return instance;
    }

    private Joueur() {
        items.add(new Item("pea-shooter", 10, 100));
        items.add(new Item("sunflower", 10, 50));
        items.add(new Item("unknown", 0, 250));
        items.add(new Item("unknown", 0, 400));
        items.add(new Item("unknown", 0, 600));
        items.add(new Item("unknown", 0, 999));
        nbrSoleils = 9999;
    }
    
    public int getSoleils() {
        return nbrSoleils;
    }

    public void setSelection(Integer selection) {
        this.selection = selection;
    }

    public Integer getSelection() {
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
    
    public void debloquerItem(Item item) {
        this.items.add(item);
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
