package ca.qc.bdeb.inf203.model;

import java.awt.Point;
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
    protected long dernierTicTimestamp = System.currentTimeMillis();
    protected int vitesse;
    protected float pendingDeplacementX;
    protected float pendingDeplacementY;
    protected Point destination = null;

    public PowerUp(String path[]) {
        this.etat = Etat.ATTENTE;
        this.sprites.put(Etat.ATTENTE, new RepresentationImage(path));
    }

    public PowerUp(String path) {
        this.etat = Etat.ATTENTE;
        this.hitbox = new Rectangle();
        this.sprites.put(Etat.ATTENTE, new RepresentationImage(new String[]{"powerups", path}));
    }

    @Override
    public Etat getEtat() {
        return Etat.ATTENTE;
    }

    public int getVitesse() {
        return vitesse;
    }

    public Point getDestination() {
        return destination;
    }

    public void tic() {
        long temps = System.currentTimeMillis();

        if (destination == null || hitbox.getLocation().equals(destination)) {
            // Si le power-up est déjà arrivé à sa destination, rien à faire
            return;
        }

        long deltaTemps = temps - this.dernierTicTimestamp;

        // v = dx/dT => dx = v*dt
        float deltaX = (Math.abs(vitesse) * Math.signum(destination.x - hitbox.getLocation().x) * (deltaTemps / 1000.0f));
        float deltaY = (Math.abs(vitesse) * Math.signum(destination.y - hitbox.getLocation().y) * (deltaTemps / 1000.0f));

        pendingDeplacementX += deltaX;
        pendingDeplacementY += deltaY;

        int deplacementX = (int) pendingDeplacementX;
        int deplacementY = (int) pendingDeplacementY;

        this.hitbox.x += deplacementX;
        this.hitbox.y += deplacementY;

        pendingDeplacementX -= deplacementX;
        pendingDeplacementY -= deplacementY;

        this.dernierTicTimestamp = temps;
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

    @Override
    protected PowerUp clone() throws CloneNotSupportedException {
        PowerUp clone = (PowerUp) super.clone();
        clone.destination = (Point) this.destination.clone();

        return clone;
    }
}
