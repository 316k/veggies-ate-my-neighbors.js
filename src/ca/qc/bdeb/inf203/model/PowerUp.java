package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 * Power-up que le joueur peut rammasser dans la grille de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class PowerUp extends Entite implements Cloneable {

    protected int animationFrameRate;
    protected long derniereAnimationTimestamp;
    protected int nbrImagesAnimation;

    public PowerUp(String path[]) {
        this.sprite = new RepresentationImage(path);
    }

    public PowerUp(String path) {
        this.hitbox = new Rectangle();
        this.sprite = new RepresentationImage(new String[]{"powerups", path});
    }

    public abstract void action();

    @Override
    public int getAnimationCompteur() {
        long ts = System.currentTimeMillis();

        if (ts - this.derniereAnimationTimestamp >= (1000.0 / (float) animationFrameRate)) {
            // On incrémente l'animation
            animationCompteur++;
            derniereAnimationTimestamp = ts;
        }
        
        return animationCompteur % nbrImagesAnimation;
    }
}
