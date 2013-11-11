package ca.qc.bdeb.inf203.model;

import java.awt.Color;


/**
 * Classe abstraite qui représente une entitée en jeu.
 * 
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Entite {
    
    private float facteurTaille;
    /**
     * Information sur la recolorisation à appliquer, pas nécessairement un
     * objet color, j'avais juste rien de mieux à mettre.
     */
    private Color colorisation;
    private int etat;
    private int vie;
    private Armure armure;
    /**
     * Quantité de vie enlevée par une attaque.
     */
    private int attaque;
    /**
     * Nb de secondes / cases.
     */
    private int vitesse;
    
    

    public float getFacteurTaille() {
        return facteurTaille;
    }

    public void setFacteurTaille(float facteurTaille) {
        this.facteurTaille = facteurTaille;
    }

    public Color getColorisation() {
        return colorisation;
    }

    public void setColorisation(Color colorisation) {
        this.colorisation = colorisation;
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

    public Armure getArmure() {
        return armure;
    }

    public void setArmure(Armure armure) {
        this.armure = armure;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    
    
    public void attaquer(Entite subit){
        subit.incrementVie(this.attaque);
    }
    
}
