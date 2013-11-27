package ca.qc.bdeb.inf203.model;

/**
 * Contient les données relatives au joueur
 * (plantes disponibles, nombre de soleils, high-score, ...)
 * @author Nicolas Hurtubise
 */
public class Joueur {
    private int nbrSoleils;
    /**
     * Plantes et autres choses dans le genre débloquées.
     */
    private boolean debloques[] = {true,false,false,false,false,false};

    public int getSoleils() {
        return nbrSoleils;
    }

    public void setSoleils(int nbrSoleils) {
        this.nbrSoleils = nbrSoleils;
    }
    
    public void addSoleils(int nbrSoleils){
        this.nbrSoleils += nbrSoleils;
    }

    public boolean[] getDebloques() {
        return debloques;
    }
    
    public void debloquer(int debloqIndex){
        debloques[debloqIndex] = true;
    }

    public void setDebloques(boolean[] debloques) {
        this.debloques = debloques;
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
