package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 * Élément de jeu ayant une position, une taille et une animation
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Entite {

    protected RepresentationImage sprite;
    protected Rectangle hitbox;
    protected int animationCompteur = 0;

    public RepresentationImage getSprite() {
        return sprite;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public abstract int getAnimationCompteur();
}
