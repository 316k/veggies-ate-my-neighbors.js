package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.VeggiesAteMyNeighbors;
import ca.qc.bdeb.inf203.model.combatants.AtomicRose;
import ca.qc.bdeb.inf203.model.combatants.Peashooter;
import ca.qc.bdeb.inf203.model.combatants.Sunflower;
import ca.qc.bdeb.inf203.model.combatants.VeggieHitler;
import java.awt.Point;
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
    
    public void incrementKills() {
        kills++;
    }
    
    public void setScore(int score) {
        this.kills = score;
    }
    
    public static Joueur instance() {
        return instance;
    }
    
    public void tic() {
        synchronized (VeggiesAteMyNeighbors.ticVerrou) {
            // Recharge des items
            for (Item item : items) {
                item.tic();
            }
        }
    }
    
    private Joueur() {
        items.add(new Item("pea-shooter", 0.001, 100, new Peashooter()));
        items.add(new Item("sunflower", 0.001, 50, new Sunflower()));
        nbrSoleils = 250;
    }
    
    public int getSoleils() {
        return nbrSoleils;
    }
    
    public void setSelection(Integer selection) {
        if (selection != null && items.get(selection).getRecharge() == 1 && nbrSoleils >= items.get(selection).getCout()) {
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
        // Génère le combatant conféré par l'item
        Combattant combattant = getItem().getCombattant().clone();
        combattant.hitbox.setLocation(position);
        combattant.lineOfSight.setLocation(position);
        
        getItem().setRecharge(0);
        this.nbrSoleils -= getItem().getCout();
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
