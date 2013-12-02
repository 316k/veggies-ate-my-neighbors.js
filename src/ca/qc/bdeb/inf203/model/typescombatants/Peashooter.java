
package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Etats;

/**
 *
 * @author Guillaume Riou
 */
public class Peashooter extends Combattant{

    @Override
    public void tic() {
        switch (etat) {
            case ATTAQUE:
                action();
                break;
            case DEPLACEMENT:
                deplacer();
                break;
        }
    }   
    /**
     * l'action à distance d'un peashooter est de retourner un pois
     * @return 
     */
    @Override
    public Combattant action() {
        
        boolean tousMorts = true;
        for (Combattant combattant : cibles) {
            if(combattant.getVie() > 0){
                tousMorts = false;
            }
        }
        if(tousMorts){
            this.etat = Etats.ATTENTELIGNEDEVUE;
        }
        return new Pois();
    }
    
}
