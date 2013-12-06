package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;
import java.util.HashMap;

/**
 * Élément de jeu ayant une position, une taille et une animation
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Entite implements Cloneable {
    /**
     * Représentation des images selon les états d'une entité.
     */
    protected HashMap<Etat, RepresentationImage> sprites = new HashMap<>();
    /**
     * État d'une entité.
     */
    protected Etat etat;
    /**
     * Hitbox d'une entité. Utilisée pour les collisions.
     */
    protected Rectangle hitbox;
    /**
     * Compteur servant à animer l'entité.
     */
    protected int animationCompteur = 0;

    public RepresentationImage getSprite() {
        return sprites.get(getEtat());
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public HashMap<Etat, RepresentationImage> getSprites() {
        return sprites;
    }
    
    public abstract int getAnimationCompteur();
    public abstract Etat getEtat();

    @Override
    protected Entite clone() throws CloneNotSupportedException {
        Entite clone = (Entite) super.clone();
        
        clone.hitbox = (Rectangle) hitbox.clone();
        clone.sprites = (HashMap<Etat, RepresentationImage>) sprites.clone();
        
        return clone;
    }
}
