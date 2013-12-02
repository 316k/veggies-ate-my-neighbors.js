/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.typescombatants.Normaux;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Combattant[] archetypes;
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
        this.archetypes = archetypes;
        this.nbParArchetype = nbParArchetype;
        this.nbInitial = getRemainingVeggies();
        this.delaisMoyen = delaisMoyen;
        this.delais = delaisMoyen;
        this.lastTimestamp = System.currentTimeMillis();
        setDelais();
    }

    private void setDelais() {
        int variation = (int) (this.delaisMoyen * 0.5f);
        this.delais = delaisMoyen + Vague.rdm.nextInt(variation) - variation;
    }

    public int getRemainingVeggies() {
        int totalRestant = 0;
        for (int i : nbParArchetype) {
            totalRestant += i;
        }
        return totalRestant;
    }

    public boolean spawnReady() {
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
        int quelCombatant;
        do {
            quelCombatant = Vague.rdm.nextInt(this.archetypes.length);
        } while (this.nbParArchetype[quelCombatant] == 0);
        this.nbParArchetype[quelCombatant]--;

        if (getRemainingVeggies() < (this.nbInitial / 2)) {
            this.delaisMoyen = 400;
        }
        this.depuisDernierSpawn = 0;
        this.setDelais();
        //Deep magic starts here.
        return archetypes[quelCombatant].getClass().cast(new Combattant(this.archetypes[quelCombatant]));
    }

    /**
     * Génère une vague dont la difficulté dépend du numéro de la vague.
     *
     * @param numeroDeVague
     * @return
     */
    public static Vague generateVague(int numeroDeVague) {
        Combattant[] cbt = {new Normaux()};
        int[] nbpa = {5};
        Vague retour = new Vague(cbt, nbpa, 5000);
        return retour;
    }
}