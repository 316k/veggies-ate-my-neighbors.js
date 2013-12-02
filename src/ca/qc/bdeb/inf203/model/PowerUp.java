package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class PowerUp {
    private RepresentationImage img;
    private Rectangle position;
    
    public PowerUp(String path[]) {
        this.img = new RepresentationImage(path);
    }
    
    public PowerUp(String path) {
        String[] pathArray = {path};
        this.img = new RepresentationImage(pathArray);
    }
    
    
    public abstract void action();
}
