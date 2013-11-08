package ca.qc.bdeb.inf203.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Classe abstraite qui représente une entitée en jeu.
 * 
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Entite {
    private BufferedImage[] sprites;
    private Point position;
    
    public BufferedImage getSprite() {
        return sprites[(int)(System.currentTimeMillis()/1000) % sprites.length];
    }    
    
}
