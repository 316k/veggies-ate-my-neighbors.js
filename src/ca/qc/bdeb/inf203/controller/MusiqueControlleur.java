/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Guillaume Riou
 */
public class MusiqueControlleur{
    /**
     * Fait jouer la musique.
     */
     public static void jouer() { 
        AudioInputStream auis = null;
        try {
            File musique = new File("assets/music/caverne.wav");
            auis = AudioSystem.getAudioInputStream(musique);
            Clip clip = AudioSystem.getClip();
            clip.open(auis);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            System.out.println("Il y a un problème avec l'audio.");
        } finally {
            try {
                auis.close();
            } catch (IOException ex) {
                System.out.println("devrait jamais arriver.");
            }
        }
 
 
    } 
    
}
