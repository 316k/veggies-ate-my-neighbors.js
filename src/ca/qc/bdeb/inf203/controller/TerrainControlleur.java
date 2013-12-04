package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import ca.qc.bdeb.inf203.model.Terrain;
import ca.qc.bdeb.inf203.view.PositionnedSpriteContainer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Hurtubise
 */
public class TerrainControlleur {

    private static Terrain terrain = new Terrain();
    /**
     * Thread contrôllant les tics du terrain et les refresh de la vue.
     */
    public static Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                terrain.tic();
                Joueur.instance().tic();
                FenetreControlleur.refresh();

                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(TerrainControlleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

    /**
     * Donne un SpriteContainer contenant l'information relative aux images et
     * positions des combatants en jeu
     *
     * @return Les informations relatives aux images des combatants en jeu
     */
    public static PositionnedSpriteContainer[] getImages() {
        try {
            ArrayList<Combattant> combatants = terrain.getEntites();
            ArrayList<PowerUp> powerups = terrain.getPowerUps();

            ArrayList<PositionnedSpriteContainer> images = new ArrayList<>();

            // TODO : Trier par valeur de x croissante pour faire un truc plus beau
            for (Combattant combatant : combatants) {
                images.add(new PositionnedSpriteContainer(combatant.getHitbox().getLocation(), combatant.getSprite(), combatant.getAnimationCompteur()));
            }

            for (PowerUp powerUp : powerups) {
                images.add(new PositionnedSpriteContainer(powerUp.getHitbox().getLocation(), powerUp.getSprite(), powerUp.getAnimationCompteur()));
            }

            return images.toArray(new PositionnedSpriteContainer[combatants.size() + powerups.size()]);
        }
        catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException dans TerrainControlleur.");
            // Autre tentative
            return new PositionnedSpriteContainer[0];
        }
    }

    public static void clic(Point point) {
        terrain.clic(point);
    }

    public static int getVague() {
        return terrain.getVague();
    }
}
