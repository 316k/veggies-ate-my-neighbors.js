package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Point;

/**
 *
 * @author Nicolas Hurtubise
 */
public class SpriteContainer {

    private Point coordonnee;
    private RepresentationImage image;
    private int animation;

    public SpriteContainer(Point coordonnee, RepresentationImage image, int animation) {
        this.coordonnee = coordonnee;
        this.image = image;
        this.animation = animation;
    }

    public Point getCoordonnee() {
        return coordonnee;
    }

    public RepresentationImage getRepresentationImage() {
        return image;
    }

    public int getAnimation() {
        return animation;
    }
}
