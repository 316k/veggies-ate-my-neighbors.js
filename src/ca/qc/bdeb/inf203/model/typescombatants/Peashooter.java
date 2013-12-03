
package ca.qc.bdeb.inf203.model.typescombatants;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Etats;
import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 *
 * @author Guillaume Riou
 */
public class Peashooter extends Combattant implements Cloneable{

    public Peashooter() {
        super();
        this.attaqueRate = 0;
        this.vitesse = 70;
        this.attaque = 30;
        /**
         * @TODO Mettre les vrais width et height.
         */
        this.hitbox.width = 15;
        this.hitbox.height = 15;
        String[] path = {"plants","pea-shooter"};
        this.sprite = new RepresentationImage(path);
    }

    public Peashooter(Combattant c) {
        super(c);
    }
    
    
    @Override
    public Combattant tic() {
        Combattant retour = null;
        switch (etat) {
            case ATTAQUE:
                retour = action();
                break;
            case DEPLACEMENT:
                deplacer();
                break;
            
        }
        return retour;
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
