package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.typescombatants.Peashooter;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
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
        items.add(new Item("pea-shooter", 10, 100, new Peashooter()));
        items.add(new Item("sunflower", 10, 50, new Peashooter()));
        nbrSoleils = 0;
    }

    public int getSoleils() {
        return nbrSoleils;
    }

    public void setSelection(Integer selection) {
        if (selection != null && items.get(selection).getRecharge() == 1) {
            this.selection = selection;
        } else {
            this.selection = null;
        }
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

    public Item getItem() {

        if (selection == null) {
            return null;
        }

        return items.get(selection);
    }

    public void debloquerItem(Item item) {
        this.items.add(item);
    }

    public Combattant useCurrentItem(Point position) {

        Combattant combattant = getItem().getCombattant().clone();
        combattant.hitbox.setLocation(position);
        combattant.lineOfSight.setLocation(position);
        getItem().setRecharge(0);
        selection = null;
        return combattant;

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
