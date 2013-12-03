package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.typescombatants.Veggie;
import java.util.Random;

/**
 * Vague en 2 phases, aléatoire dispersé et masse de trucs.
 *
 * @author guillaume
 */
public class Vague {

    private static Random rdm = new Random();
    /**
     * Combatants à générer pour cette vague.
     */
    private Combattant[] combattants;
    /**
     * Nombre de combatants à générer par index de type.
     */
    private int[] nbParArchetype;
    private int nbInitial;
    /**
     * Delais moyen entre les spawns en milisecondes.
     */
    private long delaisMoyen;
    /**
     * Delais efficace, est changé après chaque spawn.
     */
    private long delais;
    /**
     * temps en milis qui s'est passé depuis le dernier spawn.
     */
    private long depuisDernierSpawn;
    private long lastTimestamp;

    public Vague(Combattant[] archetypes, int[] nbParArchetype, int delaisMoyen) {
        this.combattants = archetypes;
        this.nbParArchetype = nbParArchetype;
        this.nbInitial = getRemainingVeggies();
        this.delaisMoyen = delaisMoyen;
        this.delais = delaisMoyen;
        this.lastTimestamp = System.currentTimeMillis();
        setDelais();
    }

    private void setDelais() {
        int variation = (int) (this.delaisMoyen * 0.5);
        this.delais = delaisMoyen + Vague.rdm.nextInt(variation) - variation;
    }

    public int getRemainingVeggies() {
        int totalRestant = 0;
        for (int i : nbParArchetype) {
            totalRestant += i;
        }
        return totalRestant;
    }

    public boolean isSpawnReady() {
        long temps = System.currentTimeMillis();
        this.depuisDernierSpawn += (temps - this.lastTimestamp);
        if (this.depuisDernierSpawn > this.delais) {
            return true;
        }
        this.lastTimestamp = temps;
        return false;
    }

    public Combattant spawn() {

        if (this.getRemainingVeggies() == 0) {
            return null;
        }
        int combatantIndex;
        do {
            combatantIndex = Vague.rdm.nextInt(this.combattants.length);
        } while (this.nbParArchetype[combatantIndex] == 0);
        this.nbParArchetype[combatantIndex]--;

        if (getRemainingVeggies() < (this.nbInitial / 2)) {
            this.delaisMoyen = 400;
        }

        this.depuisDernierSpawn = 0;
        this.setDelais();
        //Gardées pour la postérité, j'aimais vraiment ces lignes de code :(
        //Deep magic starts here.
            /*try {


         return archetypes[quelCombatant].getClass().getConstructor(new Class[]{Combattant.class}).newInstance(archetypes[quelCombatant]);

         } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
         //Ça devrait jamais arriver.
         ex.printStackTrace();
         }
         return null;*/
        return new Combattant(combattants[combatantIndex]);
    }

    /**
     * Génère une vague dont la difficulté dépend du numéro de la vague.
     *
     * @param numeroDeVague numéro de la vague voulue
     * @return
     */
    public static Vague generateVague(int numeroDeVague) {
        Combattant[] combattants = {new Veggie()};
        int[] nbrCombattantsParType = {5};
        Vague retour = new Vague(combattants, nbrCombattantsParType, 5000);
        return retour;
    }
}
