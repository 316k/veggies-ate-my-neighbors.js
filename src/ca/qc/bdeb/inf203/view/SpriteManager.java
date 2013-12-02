package ca.qc.bdeb.inf203.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
     * Hashmap : JSON du SpriteContainer, Sprite de l'animation.
     */
    private static HashMap<String, BufferedImage> sprites = new HashMap<>();

    /**
     * Donne le sprite d'une image voulue
     *
     * @todo Implémenter la colorisation
     * @param ri Représentation de l'image
     * @param animation numéro d'animation
     * @return le sprite à blitter
     */
    public static Image getImage(SpriteContainer sprite) {
        BufferedImage image;
        if (sprites.containsKey(sprite.toString())) {
            image = sprites.get(sprite.toString());
        } else {
            try {
                image = loadSprite(sprite);
            }
            catch (IOException e) {
                System.out.println("Error 404 - Sprite not found : ");
                return null;
            }
        }

        return image;
    }

    /**
     * Charge en cache une image.
     *
     * @param path Chemin à utiliser pour le blit de l'image.
     * @param animation numéro d'animation à utiliser
     * @throws IOException En cas de fichier introuvable
     * @return Le sprite loadé
     */
    private static BufferedImage loadSprite(SpriteContainer sprite) throws IOException {
        String fichier = "assets/graphics";
        System.out.println("Loading " + sprite);
        fichier += "/" + sprite.getPath();

        BufferedImage image = ImageIO.read(new File(fichier));


        if (sprite.getRepresentationImage().getColorisation() != null) {
            BufferedImage coloredImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = (Graphics2D) coloredImage.getGraphics();
            g.setColor(new Color(sprite.getRepresentationImage().getColorisation()[0], sprite.getRepresentationImage().getColorisation()[1], sprite.getRepresentationImage().getColorisation()[2]));
            g.fillRect(0, 0, image.getWidth(), image.getHeight());

            AlphaComposite ac;
            ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f);

            // paint original with composite
            g.setComposite(ac);
            g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);

            image = coloredImage;
        }

        sprites.put(sprite.toString(), image);

        return image;
    }
}