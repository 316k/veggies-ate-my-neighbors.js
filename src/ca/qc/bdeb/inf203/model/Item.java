package ca.qc.bdeb.inf203.model;

/**
 * Item dans l'inventaire du joueur (graines de plantes, ...).
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
     * Timestamp du dernier appel de la méthode tic
     */
    private long dernierTicTimestamp = System.currentTimeMillis();
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

    public boolean isUtilisable() {
        return recharge == 1 && Joueur.instance().getSoleils() >= cout;
    }

    public void tic() {
        long ts = System.currentTimeMillis();

        if (recharge < 1) {

            long deltaTemps = ts - dernierTicTimestamp;

            // v = dRecharge/dT => dRecharge = v*dT
            recharge += vitesseRechargement * deltaTemps;

            if (recharge > 1) {
                recharge = 1;
            }
        }

        dernierTicTimestamp = ts;
    }
}
