/**
 * Classe abstraite qui représente un combatant en jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function Combattant() {
    Entite.apply(this);

    this.vie = 6;
    // Array of Combattants
    this.cibles = [];
    /**
     * Nombre de frames par seconde dans l'animation
     */
    this.animationFrameRate = 1;
    /**
     * Ligne de vue du combatant pour les actions spéciales.
     */
    this.lineOfSight = new Rectangle();
    /**
     * Représente le nombre d'image par animation en fonction de l'état du combattant.
     * HashMap<Etat, Integer>
     */
    this.nbrImagesAnimation = {};
    /**
     * Quantité de vie enlevée par une attaque.
     */
    this.attaque = 2;
    /**
     * Vitesse en action/sec par rapport à une action.
     * HashMap<Action, Float>
     */
    this.vitesseAction = {};
    /**
     * Dernier timestamp calculé de l'action.
     * HashMap<Action, Long>
     */
    this.derniereActionTimestamp = {};
    /**
     * Accumulateur de milisecondes de l'action.
     * HashMap<Action, Long>
     */
    this.accumulateurAction = {};
    /**
     * Dernier timestamp calculé pour l'animation.
     */
    this.derniereAnimationTimestamp;
    /**
     * Dans quel camps le combattant combat.
     */
    this.gentil = true;
    /**
     * Tells wether the combattant is a projectile or not
     */
    this.projectile = false;

    this.initialise();
}

Combattant.prototype = new Entite();

/**
 * Initialise plusieurs propriétés essentielles.
 */
Combattant.prototype.initialise = function() {
    this.derniereAnimationTimestamp = window.performance.now();

    this.accumulateurAction[navigator.Action.ACTION] = 0;
    this.accumulateurAction[navigator.Action.DEPLACEMENT] = 0;
    this.accumulateurAction[navigator.Action.ATTAQUE] = 0;

    this.derniereActionTimestamp[navigator.Action.ACTION] = window.performance.now();
    this.derniereActionTimestamp[navigator.Action.DEPLACEMENT] = window.performance.now();
    this.derniereActionTimestamp[navigator.Action.ATTAQUE] = window.performance.now();

    this.hitbox = new Rectangle();
    this.lineOfSight = new Rectangle();
    this.etat = navigator.Etat.DEPLACEMENT;
};

Combattant.prototype.isEnnemi = function(combattant) {
    return combattant.gentil != this.gentil;
};

/**
 * Overloaded getter
 * @return Etat
 */
Combattant.prototype.getEtat = function() {
    return this.etat;
};

/**
 * Reset les accumulateurs et les derniers timestamps pour éviter des bugs.
 * @param Etat etat
 */
Combattant.prototype.setEtat = function(etat) {
    this.animationCompteur = 0;
    this.derniereAnimationTimestamp = window.performance.now();
    this.resetActions();
    this.etat = etat;
};

Combattant.prototype.getAnimationCompteur = function() {
    var ts = window.performance.now();

    if (ts - this.derniereAnimationTimestamp >= (1000.0 / this.animationFrameRate)) {
        // On incrémente l'animation
        this.animationCompteur++;
        this.derniereAnimationTimestamp = ts;
    }

    return this.animationCompteur % this.nbrImagesAnimation[this.etat];
};

/**
 * Multiplie les stats de gameplay et les vitesses d'action par une nombre en float.
 * @param float multiplicateur
 */
Combattant.prototype.multiplyStats = function(multiplicateur) {
    this.vie *= multiplicateur;
    this.attaque *= multiplicateur;
    for (var action in this.vitesseAction) {
        this.vitesseAction[action] = this.vitesseAction[action] * multiplicateur;
    }
};

/**
 * @return int combien de fois faire une action selon l'action et sa vitesse.
 */
Combattant.prototype.getNbrActions = function(action) {
    var temps = window.performance.now();
    var accumulateur = this.accumulateurAction[action] || 0;

    accumulateur += temps - this.derniereActionTimestamp[action];

    var tempsPourAction = 1000 / this.vitesseAction[action];
    var nbrActions = parseInt(accumulateur / tempsPourAction);
    accumulateur -= nbrActions * tempsPourAction;
    this.accumulateurAction[action] = accumulateur;
    this.derniereActionTimestamp[action] = temps;

    return nbrActions;
};

/**
 * Remet les accumulateurs et les timestamps des actions à 0.
 */
Combattant.prototype.resetActions = function() {
    this.accumulateurAction[Action.ACTION] = 0;
    this.accumulateurAction[Action.DEPLACEMENT] = 0;
    this.accumulateurAction[Action.ATTAQUE] = 0;

    this.derniereActionTimestamp[Action.ACTION] = window.performance.now();
    this.derniereActionTimestamp[Action.DEPLACEMENT] = window.performance.now();
    this.derniereActionTimestamp[Action.ATTAQUE] = window.performance.now();
};

/**
 * Fonction qui déplace un combattant.
 * @param int deltaX nb de pixels .
 */
Combattant.prototype.deplacer = function(deltaX) {
    if(deltaX && !isNaN(deltaX)) {
        this.hitbox.x += deltaX;
        this.lineOfSight.x += deltaX;
    }
};

/**
 * Effectue une action par rapport à l'état.
 * @return Entite
 */
Combattant.prototype.tic = function() {
    // effectuer l'action par rapport à l'état.
    switch (this.etat) {
        case navigator.Etat.ATTAQUE:
            this.attaquer(this.getNbrActions(navigator.Action.ATTAQUE));
            break;
        case navigator.Etat.DEPLACEMENT:
            this.deplacer(this.getNbrActions(navigator.Action.DEPLACEMENT));
            break;
    }

    return null;
};

Combattant.prototype.attaquer = function(nbFois) {
    if(this.isProjectile && nbFois > 0) {
        this.vie = 0;
        nbFois = 1;
    }

    // Entite[]
    var aEnlever = [];
    for (var j = 0; j < nbFois; j++) {

        for (var index in this.cibles) {
            var cible = this.cibles[index];

            if (this.isEnnemi(cible)) {
                cible.vie -= this.attaque;
            } else {
                aEnlever.push(cible);
            }

        }
    }

    for (var index in this.cibles) {
        var cible = this.cibles[index];
        if (cible.vie <= 0) {
            aEnlever.push(cible);
        }
    }

    this.cibles = array_remove_elements(this.cibles, aEnlever);

    if (this.cibles.length == 0) {
        this.setEtat(navigator.Etat.DEPLACEMENT);
    }
};

/**
 * Action à faire lorsque quelque chose est dans la ligne de vue.
 *
 * @return Entite
 */
Combattant.prototype.action = function(nbrFois) {
    return null;
};

/**
 * Clone le combatant
 * @return Combattant
 */
Combattant.prototype.clone = function() {
    var clone = clone_json(this);
    clone.initialise();
    return clone;
};
