/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.Terrain;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guillaume
 */
public class TicControleur implements Runnable{
    private Terrain modele;
    @Override
    public void run() {
        while(true){
            //Tic all the things !!!!!
            modele.tic();
            try {
                Thread.sleep(234);
            } catch (InterruptedException ex) {
                Logger.getLogger(TicControleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
