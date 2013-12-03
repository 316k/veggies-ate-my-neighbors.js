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
public class Normaux extends Combattant implements Cloneable{

    public Normaux(Combattant c) {
        super(c);
        
    }

    public Normaux() {
    }

    
    
    @Override
    public Combattant action() {
        return null;
    }

    
}
