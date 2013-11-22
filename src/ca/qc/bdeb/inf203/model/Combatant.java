package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;


/**
 * Classe abstraite qui représente une entitée en jeu.
 * 
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Combatant {
    
    private boolean mechant;
    private int etat;
    private int vie;
    private Combatant[] equipements;
    private Rectangle hitbox;
    private int animationCompteur;    
    /**
     * Quantité de vie enlevée par une attaque.
     */
    private int attaque;
    /**
     * Nb de pix/sec
     * 16 est la vitesse de base d'un veggie
     */
    private float vitesse = -16;
    private RepresentationImage imgRep;
    /**
     * Temps passé dans une case en milisecondes...
     */
    private long tempsDansCase;
    /**
     * Dernier timestamp calculé.
     */
    private long dernierTimestamp;

    public Combatant() {
        this.dernierTimestamp = System.currentTimeMillis();
        this.hitbox.height = 40;
        this.hitbox.width = 40;
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
    
    public void incrementVie(int qutee){
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
        return animationCompteur;
    }

    public void setAnimationCompteur(int animationCompteur) {
        this.animationCompteur = animationCompteur;
    }

    public RepresentationImage getImgRep() {
        return imgRep;
    }

    public void setImgRep(RepresentationImage imgRep) {
        this.imgRep = imgRep;
    }

    public void Deplacer(){
        
    }
    
    public Combatant attaquer(Combatant subit){
        subit.incrementVie(this.attaque);
        return null;
    }
    
}
