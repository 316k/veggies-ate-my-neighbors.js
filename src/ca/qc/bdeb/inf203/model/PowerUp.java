package ca.qc.bdeb.inf203.model;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class PowerUp {
    private RepresentationImage img;

    public PowerUp(RepresentationImage img) {
        this.img = img;
    }
    
    
    public abstract void action();
}
