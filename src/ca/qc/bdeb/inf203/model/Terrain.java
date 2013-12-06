package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.VeggiesAteMyNeighbors;
import ca.qc.bdeb.inf203.controller.FenetreControlleur;
import ca.qc.bdeb.inf203.model.combatants.Projectile;
import ca.qc.bdeb.inf203.model.powerups.Soleil;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.text.StyledEditorKit;

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
    private ArrayList<Combattant> combattants;

    public ArrayList<Combattant> getCombattants() {
        return combattants;
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
     * Delais entre la création de soleils en secondes
     */
    private long delaisSoleil = 30; // 30
    private long dernierTimestampSoleil;
    private int vague = 1;
    private Random rdm = new Random();

    public Terrain() {
        combattants = new ArrayList<>();
        powerUps = new ArrayList<>();
        this.vagueEnCours = Vague.generateVague(vague);
        FenetreControlleur.nouvelleVague(1);
    }

    public TerrainEvent tic() {
        synchronized (VeggiesAteMyNeighbors.ticVerrou) {
            TerrainEvent evenement = TerrainEvent.NULL;

            combattantsAction();
            evenement = ajouterVeggie();
            ajouterSoleil();

            // Tic des power-ups
            for (PowerUp powerUp : powerUps) {
                powerUp.tic();
            }

            if (isGameOver()) {
                // Override tout autre message
                evenement = TerrainEvent.GAME_OVER;
            }

            return evenement;
        }
    }

    public int getVague() {
        return vague;
    }

    private void combattantsAction() {
        ArrayList<Entite> nouvellesEntites = new ArrayList<>();
        ArrayList<Combattant> morts = new ArrayList<>();
        for (Combattant combattant : combattants) {
            // On élimine les combattants morts et ceux qui sont trop loins du terrain (par exemple, les projectiles mal lancés)
            if (combattant.getVie() <= 0 || !combattant.getHitbox().intersects(new Rectangle(-TAILLE_CASE_X, -TAILLE_CASE_Y, (CASES_X + 2) * TAILLE_CASE_X, (CASES_Y + 2) * TAILLE_CASE_Y))) {
                morts.add(combattant);
            } else {
                // Certains combatants créent des nouveaux items lors des tic
                Entite nouvelleEntite = combattant.tic();
                nouvellesEntites.add(nouvelleEntite);

                Rectangle zoneCollision = null;
                if (combattant.getEtat() == Etat.DEPLACEMENT) {
                    // Si le combattant entre en collision, il se met à attaquer ses adversaires

                    zoneCollision = combattant.getHitbox();

                } else if (combattant.getEtat() == Etat.ATTENTE) {

                    zoneCollision = combattant.getLineOfSight();
                }

                if (zoneCollision != null) {
                    ArrayList<Combattant> cibles = getCollisions(zoneCollision, combattant);
                    if (!cibles.isEmpty()) {
                        combattant.setEtat(Etat.ATTAQUE);
                        combattant.setCibles(cibles);
                    }
                }
            }
        }

        this.combattants.removeAll(morts);

        for (Entite entite : nouvellesEntites) {

            if (entite != null) {
                System.out.println("ADDD");
                if (entite instanceof Combattant) {
                    combattants.add((Combattant) entite);
                } else if (entite instanceof PowerUp) {
                    powerUps.add((PowerUp) entite);
                }
            }
        }
    }

    private TerrainEvent ajouterVeggie() {
        if (!vagueEnCours.isSpawnReady()) {
            // On attend le que le prochain spawn soit prêt
            return TerrainEvent.NULL;
        }
        //faut faire un traitement avec ça.
        Combattant combattant = vagueEnCours.spawn();

        // S'il n'y a plus de combattants, on incrémente la vague
        if (combattant == null) {
            vagueEnCours = Vague.generateVague(++vague);
            return TerrainEvent.NOUVELLE_VAGUE;
        }

        combattant.hitbox.x = CASES_X * TAILLE_CASE_X;

        //Met au hasard dans une rangée.
        combattant.hitbox.y = (1 + rdm.nextInt(CASES_Y + 1)) * TAILLE_CASE_Y;
        this.combattants.add(combattant);

        return vagueEnCours.isMassiveAttack() ? TerrainEvent.MASSIVE_ATTACK : TerrainEvent.NULL;
    }

    /**
     * Génération de soleils à des endroits aléatoires
     */
    private void ajouterSoleil() {
        long ts = System.currentTimeMillis();

        if (ts - dernierTimestampSoleil >= delaisSoleil * 1000) {
            int x = rdm.nextInt((CASES_X - 1) * TAILLE_CASE_X);
            int y = rdm.nextInt((CASES_Y - 2) * TAILLE_CASE_Y) + 1 * TAILLE_CASE_Y;
            this.powerUps.add(new Soleil(25, new Point(x, -TAILLE_CASE_Y), new Point(x, y)));
            dernierTimestampSoleil = ts;
        }

        this.dernierTimestampSoleil = ts;
    }

    public void clic(Point point) {
        synchronized (VeggiesAteMyNeighbors.ticVerrou) {
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
            for (Combattant combattant : combattants) {
                if (combattant.hitbox.intersects(caseClic) && !(combattant instanceof Projectile)) {
                    return;
                }
            }

            // On assume que l'item est sélectionné <=> il est complètement rechargé
            if (Joueur.instance().getItem() != null) {
                combattants.add(Joueur.instance().useCurrentItem(caseClic.getLocation()));
            }
        }
    }

    /**
     * Donne un ArrayList de combattants en collision avec une zone définie (un
     * combatant est exclus de la zone)
     *
     * @param zone       Zone dans laquelle vérifier les collisions
     * @param combattant Combatant avec qui on vérifie la collision
     * @return Les combatants dans la zone
     */
    private ArrayList<Combattant> getCollisions(Rectangle zone, Combattant combattantExclus) {
        ArrayList<Combattant> cibles = new ArrayList<>();

        for (Combattant combattant : combattants) {
            if (!combattant.equals(combattantExclus)) {
                // On ignore les collisions entre deux combattants alliés
                if (zone.intersects(combattant.getHitbox()) && combattant.isEnnemi(combattantExclus)) {
                    cibles.add(combattant);
                }
            }
        }
        return cibles;
    }

    /**
     * Indique si l'humanité est perdue (si la partie est terminée) ou non.
     */
    public boolean isGameOver() {
        for (Combattant combattant : combattants) {

            // Test la fin du jeu
            if (combattant.getHitbox().x <= 0 && !combattant.isGentil) {
                return true;
            }

        }

        return false;
    }
}
