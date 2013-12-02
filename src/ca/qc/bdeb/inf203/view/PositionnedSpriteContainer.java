/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Point;

/**
 *
 * @author Nicolas Hurtubise
 */
public class PositionnedSpriteContainer extends SpriteContainer {

    private Point position;

    public PositionnedSpriteContainer(Point position, RepresentationImage image, int animation) {
        super(image, animation);
        this.position = position;
    }

    public Point getCoordonnee() {
        return position;
    }
}
