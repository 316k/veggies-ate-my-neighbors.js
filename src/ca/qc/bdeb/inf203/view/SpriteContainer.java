package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;

/**
 * Conteneur pour les sprites (RepresentationImage + animation)
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class SpriteContainer {
    /**
     * représentation de l'image (chemin, flippé, colorisation)
     */
    protected RepresentationImage image;
    /**
     * numéro du frame de l'animatio.
     */
    protected int animation;
    /**
     * Si on doit flipper l'image.
     */
    protected boolean flip = false;

    public SpriteContainer(RepresentationImage image, int animation) {
        this.image = image;
        this.animation = animation;
        this.flip = image.isFlipped();
    }

    public SpriteContainer(RepresentationImage image, int animation, boolean flip) {
        this.image = image;
        this.animation = animation;
        this.flip = image.isFlipped();
    }

    public RepresentationImage getRepresentationImage() {
        return image;
    }

    public int getAnimation() {
        return animation;
    }
    
    public boolean isFlipped() {
        return flip;
    }
    /**
     * Reconstruit la string de l'adresse de l'image.
     * @return 
     */
    public String getPath() {
        String path = "";
        for (int i = 0; i < image.getPath().length; i++) {
            path += image.getPath()[i] + "/";
        }

        path += animation + ".png";

        return path;
    }

    @Override
    public String toString() {
        String color = "null";
        if (image.getColorisation() != null) {
            color = image.getColorisation()[0] + ";" + image.getColorisation()[1] + ";" + image.getColorisation()[2];
        }
        return "{'animation':'" + this.animation + "','path':'" + this.getPath() + "','colorisation':'" + color + "','flip':'" + flip + "'}";
    }
}