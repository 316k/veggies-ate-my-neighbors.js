package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import ca.qc.bdeb.inf203.model.Terrain;
import ca.qc.bdeb.inf203.model.TerrainEvent;
import ca.qc.bdeb.inf203.model.Vague;
import ca.qc.bdeb.inf203.view.PositionnedSpriteContainer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Hurtubise
 */
public class TerrainControlleur {

    private static Terrain terrain = new Terrain();
    private static boolean continuerThread;
    /**
     * Thread contrôllant les tics du terrain.
     */
    private static Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            while (continuerThread) {
                TerrainEvent evenement = terrain.tic();
                Joueur.instance().tic();

                if (evenement == TerrainEvent.GAME_OVER) {
                    FenetreControlleur.gameOver();
                } else if (evenement == TerrainEvent.NOUVELLE_VAGUE) {
                    FenetreControlleur.nouvelleVague(terrain.getVague());
                } else if (evenement == TerrainEvent.MASSIVE_ATTACK) {
                    FenetreControlleur.massiveAttack();
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TerrainControlleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });

    public static void jouer() {
        continuerThread = true;
        if (!t.isAlive()) {
            t.start();
        }
    }

    public static void pause() {
        continuerThread = false;
    }

    /**
     * Donne un SpriteContainer contenant l'information relative aux images et
     * positions des combatants en jeu
     *
     * @return Les informations relatives aux images des combatants en jeu
     */
    public static PositionnedSpriteContainer[] getImages() {
        try {
            ArrayList<Combattant> combatants = terrain.getCombattants();
            ArrayList<PowerUp> powerups = terrain.getPowerUps();

            ArrayList<PositionnedSpriteContainer> images = new ArrayList<>();

            // TODO : Trier par valeur de y croissante pour faire un truc plus beau
            for (Combattant combatant : combatants) {
                images.add(new PositionnedSpriteContainer(combatant.getHitbox().getLocation(), combatant.getSprite(), combatant.getAnimationCompteur()));
            }

            PositionnedSpriteContainer sortedImage[] = images.toArray(new PositionnedSpriteContainer[images.size()]);
            for (int i = 0; i < images.size(); i++) {
                for (int j = i; j > 1 && sortedImage[j].getCoordonnee().y < sortedImage[j - 1].getCoordonnee().y; j--) {
                    PositionnedSpriteContainer tmp;
                    tmp = sortedImage[j];
                    sortedImage[j] = sortedImage[j - 1];
                    sortedImage[j - 1] = tmp;
                }
            }

            images = new ArrayList<PositionnedSpriteContainer>(Arrays.asList(sortedImage));

            for (PowerUp powerUp : powerups) {
                images.add(new PositionnedSpriteContainer(powerUp.getHitbox().getLocation(), powerUp.getSprite(), powerUp.getAnimationCompteur()));
            }

            return images.toArray(new PositionnedSpriteContainer[combatants.size() + powerups.size()]);
        } catch (ConcurrentModificationException e) {
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

    public static void setPourcentageAugmentationVeggies(double pourcentageAugmentationVeggies) {
        Vague.setPourcentageAugmentationVeggies(pourcentageAugmentationVeggies);
    }
}
