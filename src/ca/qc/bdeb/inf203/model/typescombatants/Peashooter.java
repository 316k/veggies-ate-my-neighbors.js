
package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combatant;

/**
 *
 * @author Guillaume Riou
 */
public class Peashooter extends Combatant{
    
    
    /**
     * l'action à distance d'un peashooter est de retourner un pois
     * @return 
     */
    @Override
    public Combatant action() {
        return new Pois();
    }
    
}
