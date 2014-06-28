/**
 * Classe représentant une vague de veggies, gère les fins de vagues, les
 * moments apropriés pour spawner les zombies et le nombre de zombies par types.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */


/**
 * Créé une nouvelle vague
 * @param Combattant[] archetypes Types de veggies présents dans la vague.
 * @param int[] nbParArchetype nombres de veggies par types.
 * @param int delaisMoyen délais moyen entre deux spawns.
 */
function Vague(archetypes, nbParArchetype, delaisMoyen) {
    // int[] Colorisation en cours. Constant durant plusieurs vagues.
    this.colorisationEnCours = null;
    // Pourcentage d'augmentation du nombre de veggies
    this.pourcentageAugmentationVeggies = 1.2;
    // Combatants à générer pour cette vague.
    this.archetypes = archetypes;
    // int[] Nombre de combatants à générer par index de type.
    this.nbParArchetype = nbParArchetype;
    // int Nombre initial de veggies dans la vague.
    this.nbInitial = this.getRemainingVeggies();
    // Boolean dénotant si la vague est à son dernier 50 %
    this.massiveAttack = false;
    // Delais moyen entre les spawns en milisecondes.
    this.delaisMoyen = delaisMoyen;
    // Delais efficace, est changé après chaque spawn.
    this.delais = delaisMoyen;
    /**
     * temps en milis qui s'est passé depuis le dernier spawn.
     */
    this.depuisDernierSpawn;
    this.lastTimestamp = window.performance.now();

    this.setDelais();
}

Vague.archetypesPossibles = [new Veggie(), new Voirie(), new VeggieKamikaz(), new VeggieHitler()];

Vague.prototype.setPourcentageAugmentationVeggies = function(pourcentageAugmentationVeggies) {
    this.pourcentageAugmentationVeggies = pourcentageAugmentationVeggies;
};

/**
 * Set le délais entre deux spawn à plus ou moins 50% du délais moyen de la vague.
 */
Vague.prototype.setDelais = function() {
    var variation = parseInt(this.delaisMoyen * 0.5);
    this.delais = this.delaisMoyen + rand(0, variation) - variation;
};

/**
 * @return Le nombre de veggies restant dans la vague.
 */
Vague.prototype.getRemainingVeggies = function() {
    var total_veggies = 0;
    for (var number in this.nbParArchetype) {
        total_veggies += number;
    }
    return total_veggies;
};

/**
 * Valide si le délais de spawn est écoulé.
 * @return true si le temps nécessaire pour spawner est passé
 * false sinon.
 */
Vague.prototype.isSpawnReady = function() {
    var temps = window.performance.now();
    this.depuisDernierSpawn += (temps - this.lastTimestamp);
    if (this.depuisDernierSpawn > this.delais) {
        return true;
    }
    this.lastTimestamp = temps;
    return false;
};

/**
 * Choisis aléatoirement un veggie à retourner et en enlève un des quantités.
 * @return Combattant null si la vague est vide, la veggie sélectionné sinon 
 */
Vague.prototype.spawn = function() {

    if (this.getRemainingVeggies() == 0) {
        return null;
    }

    var combattantIndex;

    // On choisit aléatoirement un type de combattant dans ceux qui restent
    do {
        combattantIndex = rand(0, this.archetypes.length);
    } while (this.nbParArchetype[combattantIndex] == 0);

    this.nbParArchetype[combattantIndex]--;

    // Panique de fin de vague
    if (this.getRemainingVeggies() < (this.nbInitial / 2)) {
        this.delaisMoyen = 400;
        this.massiveAttack = true;
    }

    this.depuisDernierSpawn = 0;
    this.setDelais();

    return this.archetypes[combattantIndex].clone();
};

/**
 * Le nombre de veggies dans une vague donnée
 *
 * @ViveLesMéthodesInutilementRécursives
 *
 * @param numeroVague Le numéro de vague à utiliser
 * @return int Le nombre de veggies dans une vague donnée
 */
Vague.getNombreVeggie = function(numeroVague) {
    if (numeroVague == 1) {
        // Start number
        return 5;
    }

    return parseInt(Vague.getNombreVeggie(numeroVague - 1) * this.pourcentageAugmentationVeggies);
};

/**
 * Génère une vague dont la difficulté dépend du numéro de la vague.
 *
 * @param int numeroVague numéro de la vague voulue
 * @return Vague La vague générée
 */
Vague.generateVague = function(numeroVague) {
    var multiplicateur = 1 + ((numeroVague/Vague.archetypesPossibles.length) - 1) * 0.2;

    var combattants = [];

    for (var i = 0; i <= ((numeroVague - 1) % Vague.archetypesPossibles.length); i++) {
        // Combatant
        var ajout = clone_json(Vague.archetypesPossibles[i]);
        ajout.multiplyStats(multiplicateur);

        // Change la colorisation quand le cycle recommence
        if (numeroVague != 1 && (numeroVague - 1) % Vague.archetypesPossibles.length == 0) {
            this.colorisationEnCours = [
                rand(0, 255 - 56) + 56,
                rand(0, 255 - 56) + 56,
                rand(0, 255 - 56) + 56
            ];
        }

        // Stock colorisation
        for (var etat in ajout.sprites) {
            ajout.sprites[etat].colorisation = this.colorisationEnCours;
        }

        combattants.push(ajout);
    }

    var nbrCombattantsParType = [];
    for (var index in combattants) {
        nbrCombattantsParType[index] = Vague.getNombreVeggie(numeroVague) / combattants.length;
    }

    return new Vague(combattants, nbrCombattantsParType, parseInt(15000/multiplicateur));
}
