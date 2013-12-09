package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.combatants.Veggie;
import ca.qc.bdeb.inf203.model.combatants.VeggieHitler;
import ca.qc.bdeb.inf203.model.combatants.VeggieKamikaz;
import ca.qc.bdeb.inf203.model.combatants.Voirie;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe représentant une vague de veggies, gère les fins de vagues, les
 * moments apropriés pour spawner les zombies et le nombre de zombies par types.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class Vague {
    /**
     * Colorisation en cours. Constant durant plusieurs vagues.
     */
    private static int[] colorisationEnCours = null;
    /**
     * Archétypes possibles dans une vague.
     */
    private static Combattant[] archetypesPossibles = {new Veggie(), new Voirie(), new VeggieKamikaz(), new VeggieHitler()};
    /**
     * Random
     */
    private static Random rdm = new Random();
    /**
     * Pourcentage d'augmentation du nombre de veggies
     */
    private static double pourcentageAugmentationVeggies = 1.2;
    /**
     * Combatants à générer pour cette vague.
     */
    private final Combattant[] archetypes;
    /**
     * Nombre de combatants à générer par index de type.
     */
    private int[] nbParArchetype;
    /**
     * Nombre initial de veggies dans la vague.
     */
    private final int nbInitial;
    /**
     * Delais moyen entre les spawns en milisecondes.
     */
    private long delaisMoyen;
    /**
     * Boolean dénotant si la vague est à son dernier 50 %
     */
    private boolean massiveAttack = false;

    public boolean isMassiveAttack() {
        return massiveAttack;
    }
    /**
     * Delais efficace, est changé après chaque spawn.
     */
    private long delais;
    /**
     * temps en milis qui s'est passé depuis le dernier spawn.
     */
    private long depuisDernierSpawn;
    private long lastTimestamp;

    /**
     * Créé une nouvelle vague
     * @param archetypes
     * Types de veggies présents dans la vague.
     * @param nbParArchetype
     * nombres de veggies par types.
     * @param delaisMoyen 
     * délais moyen entre deux spawns.
     */
    public Vague(Combattant[] archetypes, int[] nbParArchetype, int delaisMoyen) {
        this.archetypes = archetypes;
        this.nbParArchetype = nbParArchetype;
        this.nbInitial = getRemainingVeggies();
        this.delaisMoyen = delaisMoyen;
        this.delais = delaisMoyen;
        this.lastTimestamp = System.currentTimeMillis();
        setDelais();
    }

    public long getDelaisMoyen() {
        return delaisMoyen;
    }

    public static void setPourcentageAugmentationVeggies(double pourcentageAugmentationVeggies) {
        Vague.pourcentageAugmentationVeggies = pourcentageAugmentationVeggies;
    }
    /**
     * Set le délais entre deux spawn à plus ou moins 50% du délais moyen de la 
     * vague.
     */
    private void setDelais() {
        int variation = (int) (this.delaisMoyen * 0.5);
        this.delais = delaisMoyen + Vague.rdm.nextInt(variation) - variation;
    }
    /**
     * 
     * @return Le nombre de veggies restant dans la vague.
     */
    public int getRemainingVeggies() {
        int totalRestant = 0;
        for (int i : nbParArchetype) {
            totalRestant += i;
        }
        return totalRestant;
    }
    /**
     * Valide si le délais de spawn est écoulé.
     * @return true si le temps nécessaire pour spawner est passé
     * false sinon.
     */
    public boolean isSpawnReady() {
        long temps = System.currentTimeMillis();
        this.depuisDernierSpawn += (temps - this.lastTimestamp);
        if (this.depuisDernierSpawn > this.delais) {
            return true;
        }
        this.lastTimestamp = temps;
        return false;
    }
    /**
     * Choisis aléatoirement un veggie à retourner et en enlève un des quantités.
     * @return null si la vague est vide, la veggie sélectionné sinon 
     */
    public Combattant spawn() {

        if (this.getRemainingVeggies() == 0) {
            return null;
        }

        int combattantIndex;

        // On choisit aléatoirement un type de combattant dans ceux qui restent
        do {
            combattantIndex = Vague.rdm.nextInt(this.archetypes.length);
           
        } while (this.nbParArchetype[combattantIndex] == 0);

        this.nbParArchetype[combattantIndex]--;

        // Panique de fin de vague
        if (getRemainingVeggies() < (this.nbInitial / 2)) {
            this.delaisMoyen = 400;
            this.massiveAttack = true;
        }

        this.depuisDernierSpawn = 0;
        this.setDelais();

        return archetypes[combattantIndex].clone();
    }

    /**
     * Le nombre de veggies dans une vague donnée
     *
     * @ViveLesMéthodesInutilementRécursives
     *
     * @param numeroVague Le numéro de vague à utiliser
     * @return Le nombre de veggies dans une vague donnée
     */
    public static int getNombreVeggie(int numeroVague) {
        if (numeroVague == 1) {
            return 5;
        }

        return (int) (Vague.getNombreVeggie(numeroVague - 1) * pourcentageAugmentationVeggies);
    }

    /**
     * Génère une vague dont la difficulté dépend du numéro de la vague.
     *
     * @param numeroVague numéro de la vague voulue
     * @return La vague générée
     */
    public static Vague generateVague(int numeroVague) {
        float multiplicateur = 1 + ((numeroVague / archetypesPossibles.length) - 1) * 0.2f;

        ArrayList<Combattant> combattantsAL = new ArrayList<>();

        for (int i = 0; i <= ((numeroVague - 1) % archetypesPossibles.length); i++) {
            System.out.println(i);
            Combattant ajout = archetypesPossibles[i].clone();
            ajout.multiplyStats(multiplicateur);
            //Changes la colorisation quand le cicle recommence
            if (numeroVague != 1 && (numeroVague - 1) % archetypesPossibles.length == 0) {
                colorisationEnCours = new int[]{rdm.nextInt(255 - 56) + 56, rdm.nextInt(255 - 56) + 56, rdm.nextInt(255 - 56) + 56};
            }
            for (Etat etat : ajout.getSprites().keySet()) {
                RepresentationImage rep = ajout.getSprites().get(etat);
                rep.setColorisation(colorisationEnCours);
                ajout.getSprites().put(etat, rep);
            }
            combattantsAL.add(ajout);
        }

        Combattant[] combattants = combattantsAL.toArray(new Combattant[combattantsAL.size()]);
        int[] nbrCombattantsParType = new int[combattants.length];
        for (int i = 0; i < combattants.length; i++) {
            nbrCombattantsParType[i] = getNombreVeggie(numeroVague) / combattants.length;
        }

        Vague vague = new Vague(combattants, nbrCombattantsParType, (int) (15000 / multiplicateur));
        return vague;
    }
}
