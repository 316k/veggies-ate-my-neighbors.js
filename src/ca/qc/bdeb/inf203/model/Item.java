package ca.qc.bdeb.inf203.model;

/**
 * Item dans l'inventaire du joueur.
 *
 * @author Nicolas Hurtubise
 */
public class Item {
    private Combattant combattant;
    private String nom;
    /**
     * Rechargement de l'item (où [0,1[ est en rechargement, et 1 est
     * utilisable).
     */
    private double recharge;
    /**
     * Vitesse à laquelle la recharge se fait (en charge/seconde)
     */
    private double vitesseRechargement;
    /**
     * Nombre de soleils nécessaires à la création de l'item
     */
    private int cout;

    public Item(String nom, double vitesseRechargement, int cout, Combattant combattant) {
        this.nom = nom;
        this.vitesseRechargement = vitesseRechargement;
        this.cout = cout;
        this.recharge = 1;
        this.combattant = combattant;
    }

    
    
    public int getCout() {
        return cout;
    }

    public double getRecharge() {
        return recharge;
    }

    public void setRecharge(double recharge) {
        this.recharge = recharge;
    }

    public String getNom() {
        return nom;
    }

    public Combattant getCombattant() {
        return combattant;
    }

    public double getVitesseRechargement() {
        return vitesseRechargement;
    }
    
    
}
