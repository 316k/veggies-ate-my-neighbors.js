package ca.qc.bdeb.inf203.model.typescombatants;


import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 *
 * @author guillaume
 */
public class Pois extends Combattant implements Cloneable{
    
    
    
    public Pois() {
        super();
        this.attaqueRate = 0;
        this.vitesse = 70;
        this.attaque = 30;
        /**
         * @TODO Mettre les vrais width et height.
         */
        this.hitbox.width = 15;
        this.hitbox.height = 15;
        String[] path = {"plants","pea"};
        this.sprite = new RepresentationImage(path);

    }
    public Pois(Combattant c) {
        super(c);
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
