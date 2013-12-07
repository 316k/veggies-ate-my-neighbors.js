package ca.qc.bdeb.inf203.controller;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 * Contrôlleur pour les codes de triche. (Chhhhuuuut !)
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class CheatCodeControlleur {
    /**
     * Code en cours d'écriture
     */
    private static String cheatCode = "";
    /**
     * Nombre de char max par cheat.
     */
    private static final int maxChars = 12;

    public static void keyTyped(KeyEvent e) {

        cheatCode += e.getKeyChar();
        if (cheatCode.endsWith("galarneau")) {
            JoueurControlleur.addSoleils(100);
        } else if (cheatCode.endsWith("Monsanté")) {
            TerrainControlleur.setPourcentageAugmentationVeggies(1.5);
        } else if (cheatCode.matches("^.*r[3|e][g|6][3|e]x+$")) {
            FenetreControlleur.easterEgg("/You know RegEx \\?!/");
            JoueurControlleur.addSoleils(9999999);
        } else if (cheatCode.endsWith("esrever")) {
            FenetreControlleur.easterEgg();
        } else if (cheatCode.endsWith("It's alive !")) {
            JoueurControlleur.easterEgg();
        }

        // Les cheat codes font au plus `maxChars` caractères
        if (cheatCode.length() == maxChars) {
            cheatCode = cheatCode.substring(1, maxChars);
        }
    }
}