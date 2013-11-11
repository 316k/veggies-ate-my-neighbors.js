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
     * [0] : type, [1] sous-type, [2] etat
     */
    private String[] path;

    public RepresentationImage(int[] colorisation, String[] path) {
        this.colorisation = colorisation;
        this.path = path;
    }

    public RepresentationImage() {
    }
    
    
    
}
