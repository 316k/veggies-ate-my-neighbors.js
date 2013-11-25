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
public class PlanteUnlock extends PowerUp {
    int indexADebloquer;

    public PlanteUnlock(RepresentationImage img ,int indexADebloquer) {
        super(img);
        this.indexADebloquer = indexADebloquer;
    }

    @Override
    public void action() {
        Joueur.debloquer(indexADebloquer);
    }
    
}
