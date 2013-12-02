package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class PowerUp {
    protected RepresentationImage img;
    protected Rectangle position;
    
    public PowerUp(String path[]) {
        this.img = new RepresentationImage(path);
    }
    
    public PowerUp(String path) {
        this.position = new Rectangle();
        String[] pathArray = {"powerups", path};
        this.img = new RepresentationImage(pathArray);
    }
    
    public abstract void action();
}
