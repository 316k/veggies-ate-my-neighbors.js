/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

/**
 *
 * @author guillaume
 */
public class RepresentationImage {
    /**
     * RGB
     */
    private int[] colorisation;
    /**
     * Chaque case est un step dans la hiérarchie de fichier.
     */
    private String[] path;

    public RepresentationImage(int[] colorisation, String[] path) {
        this.colorisation = colorisation;
        this.path = path;
    }

    public RepresentationImage() {
    }
    
    
    
}
