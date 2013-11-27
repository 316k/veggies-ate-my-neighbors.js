package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;

/**
 * Classe abstraite qui représente une entitée en jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Combatant {

    private int etat;
    private int vie;
    private Combatant[] equipements;
    private Rectangle hitbox;
    private int animation;
    /**
     * Quantité de vie enlevée par une attaque.
     */
    private int attaque;
    /**
     * Nb de pix/sec 16 est la vitesse de base d'un veggie
     */
    private float vitesse = -16;
    private RepresentationImage sprite;
    private long tempsImmobile;
    private long tempsPourAvancer;
    private int sensDeplacement;
    /**
     * Dernier timestamp calculé.
     */
    private long dernierTimestamp;

    public Combatant() {
        this.dernierTimestamp = System.currentTimeMillis();
        this.hitbox.height = 40;
        this.hitbox.width = 40;
        this.tempsPourAvancer = (long) (1 / vitesse * 1000);
        if (this.vitesse > 0) {
            sensDeplacement = 1;
        } else {
            sensDeplacement = -1;
        }


    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getVie() {
        return vie;
    }

    public void incrementVie(int qutee) {
        this.vie += qutee;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public Combatant[] getEquipements() {
        return equipements;
    }

    public void setEquipements(Combatant[] equipements) {
        this.equipements = equipements;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getAnimationCompteur() {
        return animation;
    }

    public void setAnimationCompteur(int animationCompteur) {
        this.animation = animationCompteur;
    }

    public RepresentationImage getImgRep() {
        return sprite;
    }

    public void setImgRep(RepresentationImage imgRep) {
        this.sprite = imgRep;
    }

    public void Deplacer() {
        long temps = System.currentTimeMillis();
        this.tempsImmobile += temps - this.dernierTimestamp;
        int nbIncrementPos = (int) (this.tempsImmobile / this.tempsPourAvancer);
        for (int i = 0; i < nbIncrementPos; i++) {
            this.hitbox.x += sensDeplacement;
            this.tempsImmobile -= this.tempsPourAvancer;
        }
    }

    public Combatant attaquer(Combatant subit) {
        subit.incrementVie(this.attaque);
        return null;
    }
}
