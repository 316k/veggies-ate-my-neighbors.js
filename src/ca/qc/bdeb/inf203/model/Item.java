/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

/**
 * Item dans l'inventaire du joueur.
 *
 * @author Nicolas Hurtubise
 */
public class Item {

    private String nom;
    /**
     * Rechargement de l'item (où [0,1[ est en rechargement, et 1 est
     * utilisable).
     */
    private double recharge;
    private double vitesseRechargement;

    public Item(String nom, double vitesseRechargement) {
        this.nom = nom;
        this.vitesseRechargement = vitesseRechargement;
        this.recharge = 1;
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
}
