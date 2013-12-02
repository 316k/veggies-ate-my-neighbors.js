package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.typespowerups.PlanteUnlock;
import ca.qc.bdeb.inf203.model.typespowerups.Soleil;
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
    private long delaisSoleil = 2000;
    private long dernierTimeStampSoleil;
    private long dernierTempsSoleil;
    private Random rdm = new Random();

    public Terrain() {
        entites = new ArrayList<>();
        powerUps = new ArrayList<>();
        this.vagueEnCours = Vague.generateVague(1);
    }

    public void tic() {
        // faire la logique d'un tic de jeu ...
        combattantsLogique();
        prochainVeggieLogique();
        ajouterSoleil();
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
                this.entites.add(combattant);
            }
        }
    }

    private void ajouterSoleil() {
        long temps = System.currentTimeMillis();
        this.dernierTempsSoleil += (temps - dernierTimeStampSoleil);
        if (dernierTempsSoleil >= delaisSoleil) {
            //Le 34 est arbitraire, comprendre la largeur du terrain.
            this.powerUps.add(new Soleil(25, rdm.nextInt(314), 0));
            System.out.println("PLUS DE SOLEIL");
            this.dernierTempsSoleil = 0;
        }
        this.dernierTimeStampSoleil = temps;
    }

    private ArrayList<Combattant> verifierCollision(Rectangle aVerifer, Combattant celuiQuonTeste) {
        ArrayList<Combattant> cibles = new ArrayList<>();

        for (Combattant combattant : entites) {
            if (!combattant.equals(celuiQuonTeste)) {
                if (aVerifer.intersects(combattant.getHitbox())) {
                    cibles.add(combattant);
                }
            }
        }
        return cibles;
    }
}
