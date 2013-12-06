package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.view.FenetrePrincipale;
import ca.qc.bdeb.inf203.view.SpriteManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlleur de fenêtre de jeu
 *
 * @author Nicolas Hurtubise
 */
public class FenetreControlleur {

    private static FenetrePrincipale fenetre;
    /**
     * Thread contrôllant les tics du terrain.
     */
    private static Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                fenetre.repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TerrainControlleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

    public static void init() {
        fenetre = new FenetrePrincipale();
        TerrainControlleur.jouer();
        t.start();
    }

    public static void menuJeu() {
        TerrainControlleur.pause();
    }

    public static void massiveAttack() {
        fenetre.setMessageBlink(true);
        fenetre.setMessage("Massive Attack !", 3);
    }

    public static void nouvelleVague(int vague) {
        fenetre.setMessageBlink(false);
        fenetre.setMessage("Vague " + vague + " !", 3);
    }

    public static void gameOver() {
        fenetre.setMessageBlink(true);
        fenetre.setMessage("You lost THE GAME");
        TerrainControlleur.pause();
    }

    /**
     * Secret.
     *
     * @param egg secret
     */
    public static void easterEgg(String egg) {
        fenetre.setMessage(egg);
    }

    /**
     * Secret.
     */
    public static void easterEgg() {
        SpriteManager.easterEgg();
    }

    public static void refresh() {
        fenetre.repaint();
    }
}
