package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.typespowerups.Soleil;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * Représentation du terrain de jeux.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Terrain {

    /**
     * Constantes de taille du terrain
     */
    public static final int CASES_X = 9;
    public static final int CASES_Y = 5;
    public static final int TAILLE_CASE_X = 80;
    public static final int TAILLE_CASE_Y = 80;
    /**
     * Représentation en objets cases de la totalité du terrain.
     */
    private ArrayList<Combattant> entites;

    public ArrayList<Combattant> getEntites() {
        return entites;
    }

    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }
    private ArrayList<PowerUp> powerUps;
    /**
     * Niveau qui est en train de se dérouler.
     */
    private Vague vagueEnCours;
    /**
     * Delais en milis.
     */
    private long delaisSoleil = 40000;
    private long dernierTimeStampSoleil;
    private long dernierTempsSoleil;
    private int vague = 1;

    private Random rdm = new Random();

    public Terrain() {
        entites = new ArrayList<>();
        powerUps = new ArrayList<>();
        this.vagueEnCours = Vague.generateVague(vague);
    }

    public void tic() {
        // faire la logique d'un tic de jeu ...
        combattantsLogique();
        prochainVeggieLogique();
        ajouterSoleil();
    }
    
    public int getVague() {
        return vague;
    }
    
    private void combattantsLogique() {
        ArrayList<Combattant> morts = new ArrayList<>();
        for (Combattant combattant : entites) {
            if (combattant.getVie() <= 0) {
                morts.add(combattant);
            } else {
                combattant.tic();

                Rectangle aTester = null;
                if (combattant.getEtat() == Etats.DEPLACEMENT) {
                    aTester = combattant.getHitbox();
                } else if (combattant.getEtat() == Etats.ATTENTELIGNEDEVUE) {
                    aTester = combattant.getLineOfSight();
                }
                if (aTester != null) {
                    ArrayList<Combattant> cibles = verifierCollision(aTester, combattant);
                    if (!cibles.isEmpty()) {
                        combattant.setEtat(Etats.ATTAQUE);
                    }
                }
            }
        }
        this.entites.removeAll(morts);
    }

    private void prochainVeggieLogique() {
        if (vagueEnCours.spawnReady()) {
            //faut faire un traitement avec ça.
            Combattant combattant = vagueEnCours.spawn();
            if (combattant == null) {

                this.vagueEnCours = Vague.generateVague(0);
                
            } else {
                combattant.hitbox.x = CASES_X*TAILLE_CASE_X;
                //Met au hasard dans une rangée.
                combattant.hitbox.y = rdm.nextInt(CASES_Y)*TAILLE_CASE_Y;
                this.entites.add(combattant);
            }
        }
    }

    private void ajouterSoleil() {
        long temps = System.currentTimeMillis();
        this.dernierTempsSoleil += (temps - dernierTimeStampSoleil);
        if (dernierTempsSoleil >= delaisSoleil) {
            //Le 34 est arbitraire, comprendre la largeur du terrain.
            this.powerUps.add(new Soleil(25, rdm.nextInt(CASES_X*TAILLE_CASE_X), 0));
            System.out.println("PLUS DE SOLEIL");
            this.dernierTempsSoleil = 0;
        }
        this.dernierTimeStampSoleil = temps;
        for (PowerUp pu : powerUps) {
            if(pu.hitbox.y < 400){
                pu.hitbox.y++;
            }
        }
    }
    
    public void clic(Point point){
        Rectangle clic = new Rectangle(point, new Dimension(1,1));
        
        
        for (int i = 0; i < powerUps.size(); i++) {
            if(clic.intersects(powerUps.get(i).hitbox)){
                powerUps.get(i).action();
                powerUps.remove(i);
                return;
            }
        }
        
        for (Combattant combattant : entites) {
            if(combattant.hitbox.intersects(clic)){
                return;
            }
        }
        Combattant aAjouter = Joueur.instance().getItems()[Joueur.instance().getSelection()].getCombattant().clone();
        aAjouter.hitbox.setLocation(clic.x, clic.y/* Logique chiante de mettre centrée dans les cases. Trop saoul.*/);
        entites.add(aAjouter);
        
    }
    
    private ArrayList<Combattant> verifierCollision(Rectangle zone, Combattant combattant) {
        ArrayList<Combattant> cibles = new ArrayList<>();

        for (Combattant combattannt : entites) {
            if (!combattannt.equals(combattannt)) {
                if (zone.intersects(combattannt.getHitbox())) {
                    
                    cibles.add(combattannt);
                }
            }
        }
        return cibles;
    }
}
