
package ca.qc.bdeb.inf203.model.combatants;

import ca.qc.bdeb.inf203.model.*;
import java.util.HashMap;

/**
 *
 * @author Guillaume Riou
 */
public class BodySnatcher extends Combattant{
    public BodySnatcher() {
        super();
        initialise();
    }

    @Override
    protected final void initialise() {
        super.initialise();
        this.vie = 100;
        this.gentil = true;
        this.hitbox.width = 80;
        this.hitbox.height = 80;
        this.lineOfSight.width = 80;
        this.lineOfSight.height = 80;
        this.animationFrameRate = 6;
        this.nbrImagesAnimation = new HashMap<>();
        this.nbrImagesAnimation.put(Etat.ATTENTE, 1);
        this.nbrImagesAnimation.put(Etat.ATTAQUE, 2);
        this.etat = Etat.ATTENTE;

        this.vitesseAction.put(Action.ATTAQUE, 1f);

        this.sprites.put(Etat.ATTENTE, new RepresentationImage(new String[]{"plants", "body-snatcher", "waiting"}));
        this.sprites.put(Etat.ATTAQUE, new RepresentationImage(new String[]{"plants", "body-snatcher", "attack"}));
    }

    @Override
    public Entite tic() {
        
        switch (etat) {
            case ATTAQUE:
                attaquer(getNbrActions(Action.ATTAQUE));
                break;
              
        }

        return null;
    }

    /**
     * L'action à distance d'un peashooter est de retourner un pois
     *
     * @return
     */
    @Override
    public void attaquer(int nbFois) {

        if (nbFois > 0) {
            this.cibles.get(0).setGentil(true);
            this.cibles.get(0).getVitesseAction().put(Action.DEPLACEMENT,(- this.cibles.get(0).getVitesseAction().get(Action.DEPLACEMENT)));
            int[] colorisation = {123,255,123};
            for(Etat etat : this.cibles.get(0).getSprites().keySet()){
                this.cibles.get(0).getSprites().get(etat).setColorisation(colorisation);
                this.cibles.get(0).getSprites().get(etat).setFlipped(true);
            }
            this.cibles.get(0).setEtat(Etat.DEPLACEMENT);
            this.cibles.get(0).setCibles(null);
            this.cibles.remove(0);
            this.vie -= 100/2;
        }
        if (this.cibles.isEmpty()) {
            this.setEtat(Etat.ATTENTE);
            return;
        }
        return;
    }

    @Override
    public Entite action(int nbrFois) {
        
        return null;
    }
}
