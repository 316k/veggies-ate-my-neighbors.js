package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Point;

/**
 *
 * @author Nicolas Hurtubise
 */
public class SpriteContainer {

    protected RepresentationImage image;
    protected int animation;
    protected int[] colorisation = null;

    public SpriteContainer(RepresentationImage image, int animation) {
        this.image = image;
        this.animation = animation;
    }

    public SpriteContainer(RepresentationImage image, int animation, int[] colorisation) {
        this.image = image;
        this.animation = animation;
        this.colorisation = colorisation;
    }

    public RepresentationImage getRepresentationImage() {
        return image;
    }

    public int getAnimation() {
        return animation;
    }

    public String getPath() {
        String path = "";
        for (int i = 0; i < image.getPath().length; i++) {
            path += image.getPath()[i] + "/";
        }

        path += animation + ".png";

        return path;
    }
}
