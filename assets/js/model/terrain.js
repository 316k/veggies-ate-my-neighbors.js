/**
 * Représentation du terrain de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function Terrain() {
    /**
     * Array de combattants présents dans le terrain.
     */
    this.combattants = null;
    /**
     * Liste de powerups présents sur le terrain.
     */
    this.powerUps = null;
    /**
     * Niveau qui est en train de se dérouler.
     * Vague
     */
    this.vagueEnCours = null;
    /**
     * Delais entre la création de soleils en secondes.
     */
    this.delaisSoleil = 30;
    this.dernierTimestampSoleil = window.performance.now();
    /**
     * Liste de plantes à débloquer au fil des niveaux.
     * PlanteUnlock[]
     */
    this.unlocks = [
        new PlanteUnlock(new Item("body-snatcher", 0.0002, 200, new BodySnatcher()), new Point()),
        new PlanteUnlock(new Item("atomic-rose", 0.00001, 500, new AtomicRose()), new Point())
    ];
    /**
     * Numéro de la vague en cours.
     */
    this.vague = 1;
    this.vagueEnCours = Vague.generateVague(this.vague);
}

/**
 * Constantes de taille du terrain.
 */
Terrain.CASES_X = 9;
Terrain.CASES_Y = 5;
Terrain.TAILLE_CASE_X = 80;
Terrain.TAILLE_CASE_Y = 80;
Terrain.ITEM_WIDTH = 75;
Terrain.OFFSET_ITEMS = parseInt(1.2 * Terrain.TAILLE_CASE_X);
Terrain.MARGIN_ITEMS = parseInt(0.2 * Terrain.ITEM_WIDTH);

/**
 * Calls all the required tic() methods
 * @return TerrainEvent
 */
Terrain.prototype.tic = function() {
    var evenement = navigator.TerrainEvent.NULL;
    this.combattantsAction();
    evenement = this.ajouterVeggie();
    this.ajouterSoleil();

    // Tic des power-ups
    for (var index in this.powerUps) {
        this.powerUps[index].tic();
    }

    if (this.isGameOver()) {
        // Override other messages
        evenement = navigator.TerrainEvent.GAME_OVER;
    }

    return evenement;
};

/**
 * Gère les actions des combattants, s'occupe des collisions, de savoir si des combattants
 * sont morts et d'ajouter les entités créés par les actions des combattants.
 */
Terrain.prototype.combattantsAction = function() {
    var nouvellesEntites = [];
    var morts = [];

    for (var index in this.combattants) {
        var combattant = this.combattants[index];

        // On élimine les combattants morts et ceux qui sont trop loin du terrain (par exemple, les projectiles mal lancés)
        if (combattant.vie <= 0 || !combattant.hitbox.intersects(new Rectangle(-Terrain.TAILLE_CASE_X, -Terrain.TAILLE_CASE_Y, (Terrain.CASES_X + 2) * Terrain.TAILLE_CASE_X, (Terrain.CASES_Y + 2) * Terrain.TAILLE_CASE_Y))) {
            morts.push(combattant);
            if (!combattant.gentil && !combattant.isProjectile) {
                navigator.Joueur.kills++;
            }
        } else {
            // Certains combattants créent des nouveaux items lors des tics
            var nouvelleEntite = combattant.tic();

            if(nouvelleEntite) {
                nouvellesEntites.push(nouvelleEntite);

                // Nuclear explosions kill every combattant
                if (nouvelleEntite instanceof ExplosionNucleaire) {
                    for (var index in this.combattants) {
                        var combattantMort = this.combattants[index];

                        if (!combattantMort.gentil && !combattantMort.isProjectile) {
                            navigator.Joueur.kills++;
                        }
                    }
                    this.combattants = [];
                    return;
                }
            }


            // Rectangle
            var zoneCollision = null;
            if (combattant.getEtat() == navigator.Etat.DEPLACEMENT) {
                // Si le combattant entre en collision, il se met à attaquer ses adversaires
                zoneCollision = combattant.hitbox;
            } else if (combattant.getEtat() == navigator.Etat.ATTENTE) {
                zoneCollision = combattant.lineOfSight;
            }

            if (zoneCollision) {
                var cibles = this.getCollisions(zoneCollision, combattant);

                if (cibles.length) {
                    combattant.setEtat(navigator.Etat.ATTAQUE);
                    combattant.cibles = cibles;
                }
            }
        }
    }

    if(this.combattants) {
        this.combattants = array_remove_elements(this.combattants, morts);
    }

    for (var index in nouvellesEntites) {
        var entite = nouvellesEntites[index];

        if (entite != null) {
            if (entite instanceof Combattant) {
                this.combattants = this.combattants || [];
                this.combattants.push(entite);
            } else if (entite instanceof PowerUp) {
                this.powerUps = this.powerUps || []
                this.powerUps.push(entite);
            }
        }
    }
};

/**
 * Met les combattants dans le terrain grâce à la vague en cours.
 * Gère la création de nouvelles vagues et le débloquage de nouveaux
 * objets par le joueur. Lance aussi les événements du terrain.
 *
 * @return TerrainEvent L'événement en cours dans le terrain.
 */
Terrain.prototype.ajouterVeggie = function() {
    if (!this.vagueEnCours.isSpawnReady()) {
        // On attend le que le prochain spawn soit prêt
        return navigator.TerrainEvent.NULL;
    }

    // faut faire un traitement avec ça.
    var nouveauCombattant = this.vagueEnCours.spawn();

    /* S'il n'y a plus de combattants à ajouter, on attend que tous les
     * veggies de la vague en cours meurent pour lancer la prochaine vague
     */
    if (nouveauCombattant == null) {
        // On ne fait rien s'il reste encore des méchants
        for (var index in this.combattants) {
            var combattant = this.combattants[index];

            if (!combattant.gentil) {
                return navigator.TerrainEvent.NULL;
            }
        }

        this.vagueEnCours = Vague.generateVague(++this.vague);

        // On débloque un item aux trois vagues, jusqu'à ce qu'il n'y ait plus d'items
        if (this.vague % 3 == 0 && this.vague/3 <= this.unlocks.length) {
            var x = rand(0, (Terrain.CASES_X - 1) * Terrain.TAILLE_CASE_X);
            var y = rand(0, (Terrain.CASES_Y - 2) * Terrain.TAILLE_CASE_Y) + 1 * Terrain.TAILLE_CASE_Y;

            // PlanteUnlock
            var unlocked = this.unlocks[Math.round(this.vague/3) - 1];

            unlocked.hitbox.x = x;
            unlocked.hitbox.y = y;

            if(!this.powerUps) {
                this.powerUps = [];
            }

            this.powerUps.push(unlocked);
        }

        return navigator.TerrainEvent.NOUVELLE_VAGUE;
    }


    nouveauCombattant.hitbox.x = parseInt(Terrain.CASES_X * Terrain.TAILLE_CASE_X);

    // Met au hasard dans une rangée.
    nouveauCombattant.hitbox.y = (1 + parseInt(rand(0, Terrain.CASES_Y + 1))) * Terrain.TAILLE_CASE_Y;

    this.combattants = this.combattants || [];
    this.combattants.push(nouveauCombattant);

    return this.vagueEnCours.massiveAttack ? navigator.TerrainEvent.MASSIVE_ATTACK : navigator.TerrainEvent.NULL;
};

/**
 * Génération de soleils à des endroits aléatoires
 */
Terrain.prototype.ajouterSoleil = function() {
    var ts = window.performance.now();

    if (ts - this.dernierTimestampSoleil >= this.delaisSoleil * 1000) {
        var x = parseInt(rand(0, (Terrain.CASES_X - 1) * Terrain.TAILLE_CASE_X));
        var y = parseInt(rand(0, (Terrain.CASES_Y - 2) * Terrain.TAILLE_CASE_Y) + 1 * Terrain.TAILLE_CASE_Y);

        this.powerUps = this.powerUps || [];
        this.powerUps.push(new Soleil(25, new Point(x, -Terrain.TAILLE_CASE_Y), new Point(x, y)));
        this.dernierTimestampSoleil = ts;
    }
};

/**
 * Gère une action de l'utilisateur selon l'endroit.
 * @param Point point
 */
Terrain.prototype.action = function(point) {
    var clic = new Rectangle(point.x, point.y, 1, 1);
    var caseClic = new Rectangle(
        parseInt(point.x/Terrain.TAILLE_CASE_X) * Terrain.TAILLE_CASE_X,
        parseInt(point.y/Terrain.TAILLE_CASE_Y) * Terrain.TAILLE_CASE_Y,
        Terrain.TAILLE_CASE_X,
        Terrain.TAILLE_CASE_Y
    );

    // Test la collision avec les power-ups
    if(this.powerUps) {
        for (var index in this.powerUps) {
            if (clic.intersects(this.powerUps[index].hitbox)) {
                this.powerUps[index].action();
                this.powerUps = array_remove_elements(this.powerUps, [this.powerUps[index]]);
                // Stop au premier power-up trouvé
                return;
            }
        }
    }

    // Test la collision avec une case contenant un combatant
    for (var index in this.combattants) {
        var combattant = this.combattants[index];

        if (combattant.hitbox.intersects(caseClic) && !combattant.isProjectile) {
            return;
        }
    }

    if (navigator.Joueur.getItem() != null) {
        this.combattants = this.combattants || [];
        this.combattants.push(navigator.Joueur.useCurrentItem(caseClic.getLocation()));
    }
};

/**
 * Donne un tableau de combattants en collision avec une zone définie (un
 * combatant est exclus de la zone)
 *
 * @param Rectangle zone Zone dans laquelle vérifier les collisions
 * @param Combattant combattant Combatant avec qui on vérifie la collision
 * @return ArrayList<Combattant> Les combatants dans la zone
 */
Terrain.prototype.getCollisions = function(zone, combattantExclus) {
    var cibles = [];

    for (var index in this.combattants) {
        var combattant = this.combattants[index];

        if (combattant != combattantExclus) {
            // On ignore les collisions entre deux combattants alliés
            if (zone.intersects(combattant.hitbox) && combattant.isEnnemi(combattantExclus)) {
                cibles.push(combattant);
            }
        }
    }
    return cibles;
};

/**
 * Indique si l'humanité est perdue (si la partie est terminée) ou non.
 */
Terrain.prototype.isGameOver = function() {
    for (var index in this.combattants) {
        var combattant = this.combattants[index];

        // Test la fin du jeu
        if (combattant.hitbox.x <= 0 && !combattant.gentil && !combattant.projectile) {
            return true;
        }
    }

    return false;
};
