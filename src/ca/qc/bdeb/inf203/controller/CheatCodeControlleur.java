package ca.qc.bdeb.inf203.controller;

import java.awt.event.KeyEvent;

/**
 * Contrôlleur pour les codes de triche.
 *
 * @author Nicolas Hurtubise
 */
public class CheatCodeControlleur {

    private static String cheatCode = "";
    private static final int maxChars = 9;

    public static void keyTyped(KeyEvent e) {

        cheatCode += e.getKeyChar();

        if (cheatCode.endsWith("galarneau")) {
            JoueurControlleur.addSoleils(100);
        } else if (cheatCode.endsWith("Monsanté")) {
            TerrainControlleur.setPourcentageAugmentationVeggies(1.5);
        } else if (cheatCode.matches("^.*r[3|e][g|6][3|e]x+$")) {
            FenetreControlleur.easterEgg("/You know RegEx \\?!/");
            JoueurControlleur.addSoleils(9999999);
        }

        // Les cheat codes font au plus `maxChars` caractères
        if (cheatCode.length() == maxChars) {
            cheatCode = cheatCode.substring(1, maxChars);
        }
    }
}
