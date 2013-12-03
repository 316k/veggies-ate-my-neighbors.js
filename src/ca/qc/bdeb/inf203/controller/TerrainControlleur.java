package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Combattant;
import ca.qc.bdeb.inf203.model.Joueur;
import ca.qc.bdeb.inf203.model.PowerUp;
import ca.qc.bdeb.inf203.model.Terrain;
import ca.qc.bdeb.inf203.view.PositionnedSpriteContainer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Hurtubise
 */
public class TerrainControlleur {

    private static Terrain terrain = new Terrain();
    public static Thread t = new Thread(new Runnable(){

        @Override
        public void run() {
            while(true){
                terrain.tic();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
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
        ArrayList<Combattant> combatants = terrain.getEntites();
        ArrayList<PowerUp> powerups = terrain.getPowerUps();
        
        ArrayList<PositionnedSpriteContainer> images = new ArrayList<>();
        
        for (Combattant combatant : combatants) {
            images.add(new PositionnedSpriteContainer(combatant.getHitbox().getLocation(), combatant.getImg(), combatant.getAnimation()));
        }
        
        for (PowerUp powerUp : powerups) {
            images.add(new PositionnedSpriteContainer(powerUp.getHitbox().getLocation(), powerUp.getImg(), powerUp.getAnimation()));
        }
        
        return images.toArray(new PositionnedSpriteContainer[combatants.size() + powerups.size()]);
    }

    public static void clic(Point point) {
        // Si un combatant est créé, on enlève la sélection du joueur
        // (on ne le fait pas si on clique sur un power-up)
        Joueur.instance().setSelection(null);
    }
    
    public static int getVague() {
        return terrain.getVague();
    }
}
