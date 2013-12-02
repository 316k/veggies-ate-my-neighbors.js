/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model.typespowerups;

import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 *
 * @author 1029172
 */
public class Soleil extends PowerUp {
    private int valeur;
    

    public Soleil(int valeur) {
        super("soleil");
        this.valeur = valeur;       
    }
    
    @Override
    public void action() {
        Joueur.instance().addSoleils(valeur);
    }
    
}
