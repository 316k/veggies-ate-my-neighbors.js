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
     * Nb de secondes / cases.
     */
    private int vitesse;
    
    
}
