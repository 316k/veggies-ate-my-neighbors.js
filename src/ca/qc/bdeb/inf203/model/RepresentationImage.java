/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

/**
 * Représentation abstraite du sprite d'un model.
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class RepresentationImage {
    /**
     * RGB
     */
    private int[] colorisation;
    /**
     * Chemin à utiliser pour le blit de l'image.
     * Chaque case est un step dans la hiérarchie de fichier.
     */
    private String[] path;

    /**
     * Constructeur
     * @param colorisation
     * @param path 
     */
    public RepresentationImage(int[] colorisation, String[] path) {
        this.colorisation = colorisation;
        this.path = path;
    }

    public int[] getColorisation() {
        return colorisation;
    }

    public String[] getPath() {
        return path;
    }
    
    
}
