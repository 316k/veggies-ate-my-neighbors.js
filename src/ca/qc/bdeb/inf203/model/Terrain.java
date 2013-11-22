package ca.qc.bdeb.inf203.model;

/**
 * Représentation du terrain de jeux.
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Terrain {
    /**
     * Représentation en objets cases de la totalité du terrain.
     */
    private Case[][] casesDeJeux;
    /**
     * Niveau qui est en train de se dérouler.
     */
    private Niveau niveauEnCours;
    
    public void tic(){
        // faire la logique d'un tic de jeu ...
        for (int i = 0; i < casesDeJeux.length; i++) {
            for (int j = 0; j < casesDeJeux[i].length; j++) {
                if(casesDeJeux[i][j].getEntites().size() > 1){
                    //Logique de combat.
                }else if (casesDeJeux[i][j].getEntites().size() == 1){
                    //Logique de déplacement.
                    casesDeJeux[i][j].getEntites().get(0).Deplacer();
                }
            }
        }
    }
    private void deplacementLogique(){
    
    }
    private void combatLogique(){
        
    }
}
