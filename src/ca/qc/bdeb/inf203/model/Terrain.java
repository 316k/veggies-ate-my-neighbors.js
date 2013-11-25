package ca.qc.bdeb.inf203.model;

import java.util.ArrayList;

/**
 * Représentation du terrain de jeux.
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Terrain {
    /**
     * Représentation en objets cases de la totalité du terrain.
     */
    private ArrayList<Combatant> gentils;
    private ArrayList<Combatant> mechants;
    /**
     * Niveau qui est en train de se dérouler.
     */
    private Vague vagueEnCours;
    
    public void tic(){
        // faire la logique d'un tic de jeu ...
        combatantsLogique();
        
    }
    private void combatantsLogique(){
        
        for (Combatant combatant : mechants) {
            if(combatant.getHitbox().x == 0){
                System.out.println("Haha tes nul, ta perdu");
                System.exit(9/* 9 étant l'erreur "tes trop nul"*/);
            }
            for (Combatant antagoniste : gentils) {
                if(combatant.getHitbox().intersects(antagoniste.getHitbox())){
                    combatant.attaquer(antagoniste);
                }else{
                    combatant.Deplacer();
                }
            }
        }
        
        for (Combatant combatant : gentils) {
            
        }
        
    }
    private void spawnLogique(){
        if(vagueEnCours.spawnReady()){
            //faut faire un traitement avec ça.
            vagueEnCours.spawn();
        }
    }
    
}
