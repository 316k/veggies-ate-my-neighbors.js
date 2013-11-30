/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.inf203.model;

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
    }

    private void setDelais() {
        int variation = (int)(this.delaisMoyen*0.5f);
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
        this.depuisDernierSpawn += temps - this.lastTimestamp;
        if (this.depuisDernierSpawn > this.delais) {
            return true;
        }
        return false;
    }

    public Combattant spawn() {
        int quelCombatant;
        do {
            quelCombatant = Vague.rdm.nextInt(this.archetypes.length);
        } while (this.nbParArchetype[quelCombatant] == 0);
        this.nbParArchetype[quelCombatant] --;
        
        if(getRemainingVeggies() == 0){
            this.delaisMoyen = 400;
        }
        this.setDelais();
        this.depuisDernierSpawn -= delais;
        return this.archetypes[quelCombatant];
    }

    /**
     * Génère une vague dont la difficulté dépend du numéro de la vague.
     *
     * @param numeroDeVague
     * @return
     */
    public static Vague generateVague(int numeroDeVague) {
        return null;
    }
}