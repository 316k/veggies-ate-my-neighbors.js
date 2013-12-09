package ca.qc.bdeb.inf203;

import ca.qc.bdeb.inf203.controller.FenetreControlleur;
import ca.qc.bdeb.inf203.controller.MusiqueControlleur;

/**
 * Main
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class VeggiesAteMyNeighbors {

    public static final Object ticVerrou = new Object();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FenetreControlleur.init();
        MusiqueControlleur.jouer();
    }
}
