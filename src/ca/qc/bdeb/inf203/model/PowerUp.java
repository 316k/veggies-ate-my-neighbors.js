package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 * Power-up que le joueur peut rammasser dans la grille de jeu.
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class PowerUp {

    protected RepresentationImage img;
    protected Rectangle hitbox;
    protected int animation;

    public PowerUp(String path[]) {
        this.img = new RepresentationImage(path);
    }

    public PowerUp(String path) {
        this.hitbox = new Rectangle();
        this.img = new RepresentationImage(new String[]{"powerups", path});
    }

    public abstract void action();

    public RepresentationImage getImg() {
        return img;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getAnimation() {
        return animation;
    }
}
