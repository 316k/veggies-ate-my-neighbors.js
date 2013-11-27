/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

/**
 *
 * @author Guillaume
 */
public enum Etats {
    
    ATTAQUE("attaque"),
    DEPLACEMENT("deplacement"),
    ATTENTELIGNEDEVUE("jesaispasquoimettre");
    
    
    public String nom;
    private Etats(String nom){
        this.nom = nom;
    }
}
