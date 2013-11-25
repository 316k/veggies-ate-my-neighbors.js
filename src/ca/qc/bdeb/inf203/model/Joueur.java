package ca.qc.bdeb.inf203.model;

/**
 * Contient les données relatives au joueur
 * (plantes disponibles, nombre de soleils, high-score, ...)
 * @author Nicolas Hurtubise
 */
public class Joueur {
    static private int nbSoleils;
    /**
     * Plantes et autres choses dans le genre débloquées.
     */
    private static boolean debloques[] = {true,false,false,false,false,false};

    public static int getNbreSoleils() {
        return nbSoleils;
    }

    public static void setNbreSoleils(int nbreSoleils) {
        Joueur.nbSoleils = nbreSoleils;
    }
    
    public static void addSoleil(int nbSoleils){
        Joueur.nbSoleils += nbSoleils;
    }

    public static boolean[] getDebloques() {
        return debloques;
    }
    
    public static void debloquer(int debloqIndex){
        debloques[debloqIndex] = true;
    }

    public static void setDebloques(boolean[] debloques) {
        Joueur.debloques = debloques;
    }
    
    /**
     * Load la dernière save
     */
    public void loadSave(){
        
    }
    /**
     * Save dans le fichier de save (lol)
     */
    public void Save(){
        
    }
    
}
