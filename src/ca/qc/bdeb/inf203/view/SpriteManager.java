package ca.qc.bdeb.inf203.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * Gestionnaire d'images, de colorisation et de cache.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class SpriteManager {

    /**
     * Hashmap : JSON du SpriteContainer, Sprite de l'animation.
     */
    private static HashMap<String, BufferedImage> sprites = new HashMap<>();
    private static boolean easterEgg = false;

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
            } catch (IOException e) {
                System.out.println("Error 404 - Sprite not found : " + sprite.getPath());
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

        // Colorisation
        if (sprite.getRepresentationImage().getColorisation() != null) {
            /* On utilise un masque, soit une image ayant la même forme que l'original (ex. en forme de veggie)
             * mais ayant une couleur uniforme une une transparence de 50% que l'on va blitter par dessus
             * l'image originale pour lui donner une couleur.
             */
            BufferedImage mask = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = (Graphics2D) mask.getGraphics();
            g.setColor(new Color(sprite.getRepresentationImage().getColorisation()[0], sprite.getRepresentationImage().getColorisation()[1], sprite.getRepresentationImage().getColorisation()[2]));
            g.fillRect(0, 0, image.getWidth(), image.getHeight());

            AlphaComposite ac;
            ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 0.5f);

            g.setComposite(ac);
            g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);

            BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            g = (Graphics2D) img.getGraphics();

            g.drawImage(image, null, null);
            g.drawImage(mask, null, null);

            image = img;
//            BufferedImage imageColorisee = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = imageColorisee.createGraphics();
//            g.drawImage(image, 0, 0, null);
//            
//            
//            for (int i = 0; i < image.getWidth(); i++) {
//                for (int j = 0; j < image.getHeight(); j++) {
//                    int couleur = imageColorisee.getRGB(i, j);
//                    
//                    //les bitshifts et les And je les ai copiés sur stack
//                    //overflow mais au moins je sais ce qu'ils font.
//                    int[] couleurs = {(couleur >> 16) & 0x000000FF,
//                        (couleur >> 8) & 0x000000FF, (couleur) & 0x000000FF, (couleur << 24) & 0x000000FF};
//                    for (int k = 0; k < couleurs.length; k++) {
//                        if (k < 3) {
//                            couleurs[k] += sprite.getRepresentationImage().getColorisation()[k];
//                            if(couleurs[k]>255){
//                                couleurs[k] = 255;
//                            }else if(couleurs[k] < 0){
//                                couleurs[k] = 0;
//                            }
//                        }else{
//                            couleurs[k] = 255;
//                           
//                        }
//                    }
//                    
//                    Color c = new Color(couleurs[0],couleurs[1],couleurs[2],couleurs[3]);
//                    
//                    System.out.println(((c.getRGB()>>8) & 0x000000FF) +""+ "AAA");
//                    imageColorisee.setRGB(i, j, c.getRGB());
//                    
//                }
//            }
//            image = imageColorisee;
        }

        // Flip
        if (sprite.isFlipped() || easterEgg) {
            BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = (Graphics2D) img.getGraphics();

            g.scale(-1, 1);
            g.drawImage(image, AffineTransform.getTranslateInstance(-image.getWidth(), 0.0), null);

            image = img;
        }

        sprites.put(sprite.toString(), image);

        return image;
    }

    /**
     * Secret.
     */
    public static void easterEgg() {
        sprites.clear();
        easterEgg = !easterEgg;
    }
}
