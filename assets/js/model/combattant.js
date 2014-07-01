/**
 * Classe abstraite qui représente un combatant en jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function Combattant() {
    Entite.apply(this);

    console.log('init combattant');
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
 * TODO : Split into combattant_init() and init() ~ No parent.stuff() in JavaScript... sorry
 */
Combattant.prototype.initialise = function() {
    this.derniereAnimationTimestamp = window.performance.now();

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
 * Resets timestamps and animations to avoid bugs/glitches
 * @param Etat etat
 */
Combattant.prototype.setEtat = function(etat) {
    this.animationCompteur = 0;
    this.derniereAnimationTimestamp = window.performance.now();
    this.derniereActionTimestamp[navigator.Action.ACTION] = window.performance.now();
    this.derniereActionTimestamp[navigator.Action.DEPLACEMENT] = window.performance.now();
    this.derniereActionTimestamp[navigator.Action.ATTAQUE] = window.performance.now();
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
 * @return float combien de fois faire une action selon l'action et sa vitesse.
 */
Combattant.prototype.getNbrActions = function(action) {
    var time = window.performance.now();

    // Time elapsed since the last action
    var deltaTime = time - this.derniereActionTimestamp[action];

    var tempsPourAction = 1000 / this.vitesseAction[action];
    var times = deltaTime / tempsPourAction;
    this.derniereActionTimestamp[action] = time;

    if(action == navigator.Action.DEPLACEMENT) {
        console.log(deltaTime + ' ' + tempsPourAction + ' ' + times);
    }

    return times;
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

/**
 * @return a array containing only opponent fighters (fighters can
 * become allies/ennemies)
 */
Combattant.prototype.ennemies = function() {
    var opponents = [];

    for(var index in this.cibles) {
        if(this.isEnnemi(this.cibles[index])) {
            opponents.push(this.cibles[index]);
        }
    }

    return opponents.length ? opponents : null;
}

Combattant.prototype.attaquer = function(times) {
    if(this.isProjectile && nbFois > 0) {
        this.vie = 0;
        nbFois = 1;
    }

    // Ensures to iterate on ennemies only
    this.cibles = this.ennemies();

    for(var index in this.cibles) {
        this.cibles[index].vie -= times * this.attaque;
    }

    var deads = [];
    for (var index in this.cibles) {
        if (this.cibles[index].vie <= 0) {
            deads.push(this.cibles[index]);

            // Vibrate devices when fighters are killed
            if(!this.cibles[index].isProjectile) {
                navigator.vibrate(90);
            }
        }
    }

    this.cibles = array_remove_elements(this.cibles, deads);

    if (this.cibles.length == 0) {
        this.setEtat(navigator.Etat.DEPLACEMENT);
    }
};

/**
 * Action à faire lorsque quelque chose est dans la ligne de vue.
 *
 * @return Entite
 */
Combattant.prototype.action = function(times) {
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
