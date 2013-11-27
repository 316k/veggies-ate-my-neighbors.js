package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class PowerUp {
    private RepresentationImage img;
    private Rectangle position;
    public PowerUp(RepresentationImage img) {
        this.img = img;
    }
    
    
    public abstract void action();
}
