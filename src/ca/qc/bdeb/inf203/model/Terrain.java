package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Représentation du terrain de jeux.
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Terrain {
    /**
     * Représentation en objets cases de la totalité du terrain.
     */
    private ArrayList<Combatant> entites;
    
    /**
     * Niveau qui est en train de se dérouler.
     */
    private Vague vagueEnCours;
    
    public void tic(){
        // faire la logique d'un tic de jeu ...
        combatantsLogique();
        
    }
    private void combatantsLogique(){
        ArrayList<Combatant> morts = new ArrayList<>();
        for (Combatant combatant : entites) {
            if (combatant.getVie() >=0){
                morts.add(combatant);
            }else{
                combatant.tic();
                Rectangle aTester;
                if(combatant.getEtat() == Etats.DEPLACEMENT){
                    aTester = combatant.getHitbox();
                }else if(combatant.getEtat() == Etats.ATTENTELIGNEDEVUE){
                    aTester = combatant.getLineOfSight();
                }
            }
        }
        
    }
    private void spawnLogique(){
        if(vagueEnCours.spawnReady()){
            //faut faire un traitement avec ça.
            vagueEnCours.spawn();
        }
    }
    
}
