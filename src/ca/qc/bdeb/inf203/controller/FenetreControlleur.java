/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.view.FenetrePrincipale;

/**
 *
 * @author Nicolas Hurtubise
 */
public class FenetreControlleur {

    private static FenetrePrincipale fenetre;

    public static void init() {
        fenetre = new FenetrePrincipale();
    }

    public static void refresh() {
        fenetre.repaint();
    }
}
