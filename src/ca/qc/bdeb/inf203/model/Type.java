/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

/**
 * Type d'un combatant
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Type {
    private String type;
    private String sousType;
    
    public Type(String type, String sousType){
        this.type = type;
        this.sousType = sousType;
    }

    public String getType() {
        return type;
    }

    public String getSousType() {
        return sousType;
    }
    
    
}
