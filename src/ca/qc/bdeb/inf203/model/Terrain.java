package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Représentation du terrain de jeux.
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Terrain {
    /**
     * Les chiffres sont purement symboliques.
     */
    private static int RANG_UN = 24;
    private static int RANG_DEUX = 56;
    private static int RANG_TROIS = 78;
    private static int RANG_QUATRE = 90;
    private static int RANG_CINQ = 120;
    
    /**
     * Représentation en objets cases de la totalité du terrain.
     */
    private ArrayList<Combattant> entites;
    
    /**
     * Niveau qui est en train de se dérouler.
     */
    private Vague vagueEnCours;
    
    public void tic(){
        // faire la logique d'un tic de jeu ...
        combattantsLogique();
        
    }
    private void combattantsLogique(){
        ArrayList<Combattant> morts = new ArrayList<>();
        for (Combattant combattant : entites) {
            if (combattant.getVie() >=0){
                morts.add(combattant);
            }else{
                combattant.tic();
                Rectangle aTester = null;
                if(combattant.getEtat() == Etats.DEPLACEMENT){
                    aTester = combattant.getHitbox();
                }else if(combattant.getEtat() == Etats.ATTENTELIGNEDEVUE){
                    aTester = combattant.getLineOfSight();
                }
                if(aTester != null){
                    ArrayList<Combattant> cibles = verifierCollision(aTester, combattant);
                    if(!cibles.isEmpty()){
                        combattant.setEtat(Etats.ATTAQUE);
                    }
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
    private ArrayList<Combattant> verifierCollision(Rectangle aVerifer, Combattant celuiQuonTeste){
        ArrayList<Combattant> cibles = new ArrayList<>();
        
        for (Combattant combattant : entites) {
            if(!combattant.equals(celuiQuonTeste)){
                if(aVerifer.intersects(combattant.getHitbox())){
                    cibles.add(combattant);
                }
            }
        }
        return cibles;
    }
}
