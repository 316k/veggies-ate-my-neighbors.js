package ca.qc.bdeb.inf203;

import ca.qc.bdeb.inf203.controller.TerrainControlleur;
import ca.qc.bdeb.inf203.controller.FenetreControlleur;

/**
 *
 * @author Nicolas Hurtubise
 */
public class VeggiesAteMyNeighbors {
    public static final Object ticVerrou = new Object();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // À déplacer dans un contrôlleur
        
        FenetreControlleur.init();
        TerrainControlleur.t.start();
    }
}
