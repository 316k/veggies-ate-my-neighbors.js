/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;

/**
 *
 * @author guillaume
 */
public class Pois extends Combattant{

    public Pois() {
        super();
        this.attaqueRate = 0;
        this.vitesse = 70;
        this.attaque = 30;
        this.hitbox.width = 15;
        this.hitbox.height = 15;
    }
    
    @Override
    protected void attaquer() {
        super.attaquer();
        this.setVie(0);
    }

    @Override
    public Combattant action() {
        return null;
    }
    
    

    
   
}
