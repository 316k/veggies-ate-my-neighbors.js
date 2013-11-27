package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * Partie graphique des entités.
 *
 * @author Nicolas Hurtubise
 */
public class SpriteManager {

    /**
     * Hashmap : Etat(s) de l'entité, Sprite de l'animation. Oui, c'est un peu
     * violent.
     */
    private static HashMap< String[], HashMap<Integer, Image>> sprites = new HashMap<>();

    /**
     * Donne le sprite d'une image voulue
     *
     * @todo Implémenter la colorisation
     * @param ri Représentation de l'image
     * @param animation numéro d'animation
     * @return le sprite à blitter
     */
    public static Image getSprite(RepresentationImage ri, int animation) {
        if (sprites.containsKey(ri.getPath())) {
            return sprites.get(ri.getPath()).get(animation);
        } else {
            try {
                return loadSprite(ri.getPath(), animation);
            }
            catch (IOException e) {
                System.out.println("Error 404 - Sprite not found : " + ".../" + ri.getPath()[ri.getPath().length-1] + "/" + animation + ".png");
                return null;
            }
        }
    }

    /**
     * Charge en cache une image.
     *
     * @param path Chemin à utiliser pour le blit de l'image.
     * @param animation numéro d'animation à utiliser
     * @throws IOException En cas de fichier introuvable
     * @return Le sprite loadé
     */
    private static Image loadSprite(String[] path, int animation) throws IOException {
        String fichier = "graphics";
        for (int i = 0; i < path.length; i++) {
            fichier += "/" + path[i];
        }

        fichier += "/" + animation + ".png";

        Image sprite = ImageIO.read(new File(fichier));

        if (!sprites.containsKey(path)) {
            sprites.put(path, new HashMap<Integer, Image>());
        }

        sprites.get(path).put(animation, sprite);

        return sprite;
    }
}