package ca.qc.bdeb.inf203.view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.imageio.ImageIO;

/**
 * Partie graphique des entités.
 *
 * @author guillaume
 */
public class SpriteManager {

    /**
     * Hashmap : Etat de l'entité, Sprite de l'animation.
     */
    private HashMap<String, Image[]> sprites;
    private String etat;

    public SpriteManager() {
        try {
            //loadSprites("graphics/" + type.getType() + "/" + type.getSousType());
        }
        catch (IOException ex) {
            System.out.println("[Error 27] : Chargement des images impossible");
        }
    }

    public Image getSprite() {
        /*
         BufferedImage resultat = sprites[etat][compteur];

         if (compteur >= sprites[etat].length) {
         compteur = 0;
         } else {
         compteur++;
         }
         return resultat;*/
        return null;
    }

    private void loadSprites(String path) throws IOException {
        sprites = new HashMap<>();

        File root = new File(path);
        File[] ls = root.listFiles();
        if (ls == null) {
            throw new IOException();
        }
        for (File directory : ls) {
            if (directory.isDirectory()) {
                File[] subDirectory = directory.listFiles();
                Queue<Image> queueImages = new LinkedBlockingQueue<>();

                for (File file : subDirectory) {
                    if (file.isFile() && file.getName().matches(".*\\.png$")) {
                        queueImages.add(ImageIO.read(file));
                    }
                }
                Image[] images = new Image[queueImages.size()];

                Image image;
                while ((image = queueImages.poll()) != null) {
                    images[images.length - queueImages.size() - 1] = image;
                }

                sprites.put(directory.getName(), images);
            }
        }
    }
}