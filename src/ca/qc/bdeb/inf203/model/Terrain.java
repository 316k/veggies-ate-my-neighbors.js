package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.VeggiesAteMyNeighbors;
import ca.qc.bdeb.inf203.model.powerups.Soleil;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

/**
 * Représentation du terrain de jeu.
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
        synchronized (VeggiesAteMyNeighbors.ticVerrou) {
            combattantsLogique();
            prochainVeggieLogique();
            ajouterSoleil();
        }
    }

    public int getVague() {
        return vague;
    }

    private void combattantsLogique() {
        try {
            ArrayList<Combattant> morts = new ArrayList<>();
            ArrayList<Combattant> nouveaux = new ArrayList<>();
            for (Combattant combattant : entites) {
                if (combattant.getVie() <= 0) {
                    morts.add(combattant);
                } else {
                    // Certains combatants créent des nouveaux items lors des tic
                    Combattant nouveauCombattant = combattant.tic();

                    if (nouveauCombattant != null) {
                        nouveaux.add(nouveauCombattant);
                    }

                    Rectangle zoneCollision = null;
                    if (combattant.getEtat() == Etats.DEPLACEMENT) {
                        zoneCollision = combattant.getHitbox();
                    } else if (combattant.getEtat() == Etats.ATTENTE) {
                        zoneCollision = combattant.getLineOfSight();
                    }
                    if (zoneCollision != null) {
                        ArrayList<Combattant> cibles = getCollisions(zoneCollision, combattant);

                        if (!cibles.isEmpty()) {
                            combattant.setEtat(Etats.ATTAQUE);
                        }
                    }
                }
            }
            this.entites.removeAll(morts);
            this.entites.addAll(nouveaux);
        }
        catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException dans Terrain");
        }
    }

    private void prochainVeggieLogique() {
        if (!vagueEnCours.isSpawnReady()) {
            // On attend le que le prochain spawn soit prêt
            return;
        }
        //faut faire un traitement avec ça.
        Combattant combattant = vagueEnCours.spawn();

        if (combattant == null) {
            vagueEnCours = Vague.generateVague(++vague);
            return;
        }

        combattant.hitbox.x = CASES_X * TAILLE_CASE_X;

        //Met au hasard dans une rangée.
        combattant.hitbox.y = (1 + rdm.nextInt(CASES_Y + 1)) * TAILLE_CASE_Y;
        this.entites.add(combattant);
    }

    private void ajouterSoleil() {
        long temps = System.currentTimeMillis();
        this.dernierTempsSoleil += (temps - dernierTimeStampSoleil);
        if (dernierTempsSoleil >= delaisSoleil) {
            //Le 34 est arbitraire, comprendre la largeur du terrain.
            this.powerUps.add(new Soleil(25, rdm.nextInt(CASES_X * TAILLE_CASE_X), 0));
            this.dernierTempsSoleil = 0;
        }
        this.dernierTimeStampSoleil = temps;
        for (PowerUp pu : powerUps) {
            if (pu.hitbox.y < 400) {
                pu.hitbox.y++;
            }
        }
    }

    public void clic(Point point) {
        Rectangle clic = new Rectangle(point, new Dimension(1, 1));
        Rectangle caseClic = new Rectangle(point.x / TAILLE_CASE_X * TAILLE_CASE_X, point.y / TAILLE_CASE_Y * TAILLE_CASE_Y, TAILLE_CASE_X, TAILLE_CASE_Y);

        // Test la collision avec les power-ups
        for (int i = 0; i < powerUps.size(); i++) {
            if (clic.intersects(powerUps.get(i).hitbox)) {
                powerUps.get(i).action();
                powerUps.remove(i);
                // Stop au premier power-up trouvé
                return;
            }
        }

        // Test la collision avec une case contenant un combatant
        for (Combattant combattant : entites) {
            if (combattant.hitbox.intersects(caseClic)) {
                return;
            }
        }

        // On assume que l'item est sélectionné <=> il est complètement rechargé
        if (Joueur.instance().getItem() != null) {
            entites.add(Joueur.instance().useCurrentItem(caseClic.getLocation()));
        }
    }

    /**
     * Donne un ArrayList de combattants en collision avec une zone définie (un
     * combatant est exclus de la zone)
     *
     * @param zone Zone dans laquelle vérifier les collisions
     * @param combattant Combatant à exclure
     * @return Les combatants dans la zone
     */
    private ArrayList<Combattant> getCollisions(Rectangle zone, Combattant combattantExclus) {
        ArrayList<Combattant> cibles = new ArrayList<>();

        for (Combattant combattant : entites) {
            if (!combattant.equals(combattantExclus)) {
                if (zone.intersects(combattant.getHitbox())) {
                    cibles.add(combattant);
                }
            }
        }
        return cibles;
    }
}
