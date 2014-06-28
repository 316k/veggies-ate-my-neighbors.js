/**
 * Power-up que le joueur peut rammasser dans la grille de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function PowerUp() {
    Entite.apply(this);
    /**
     * Vitesse de l'animation
     */
    this.animationFrameRate = null;
    /**
     * dernier temps calculé pour l'animation.
     */
    this.derniereAnimationTimestamp = null;
    /**
     * Nombre d'images par animation (franchement, faut vraiment faire de la
     * doc pour des trucs comme ça  ? ...)
     */
    this.nbrImagesAnimation = null;
    /**
     * Dernier temps calculé pour une action.
     */
    this.dernierTicTimestamp = window.performance.now();
    /**
     * Vitesse de déplacement.
     */
    this.vitesse = 0;
    /**
     * déplacements fractionnaire en attente.
     */
    this.pendingDeplacementX = 0;
    this.pendingDeplacementY = 0;
    /**
     * Point où le powerUp arrête de tomber.
     */
    this.destination = null;

    this.etat = navigator.Etat.ATTENTE;
    this.hitbox = new Rectangle();
}

PowerUp.prototype = new Entite();

PowerUp.prototype.getEtat = function() {
    return navigator.Etat.ATTENTE;
};

/**
 * Gère les actions des powerups.
 */
PowerUp.prototype.tic = function() {
    var temps = window.performance.now();

    if (this.destination == null || (this.hitbox.x == this.destination.x && this.hitbox.y == this.destination.y)) {
        // Si le power-up est déjà arrivé à sa destination, rien à faire
        return;
    }

    var deltaTemps = temps - this.dernierTicTimestamp;

    // v = dx/dT => dx = v*dt
    var deltaX = (Math.abs(vitesse) * Math.sign(this.destination.x - this.hitbox.x) * (deltaTemps / 1000.0));
    var deltaY = (Math.abs(vitesse) * Math.sign(this.destination.y - this.hitbox.y) * (deltaTemps / 1000.0));

    this.pendingDeplacementX += deltaX;
    this.pendingDeplacementY += deltaY;

    var deplacementX = parseInt(pendingDeplacementX);
    var deplacementY = parseInt(pendingDeplacementY);

    this.hitbox.x += deplacementX;
    this.hitbox.y += deplacementY;

    pendingDeplacementX -= deplacementX;
    pendingDeplacementY -= deplacementY;

    this.dernierTicTimestamp = temps;
};

PowerUp.prototype.action = function() {};

PowerUp.prototype.getAnimationCompteur = function() {
    var ts = window.performance.now();

    if (ts - this.derniereAnimationTimestamp >= (1000 / this.animationFrameRate)) {
        // On incrémente l'animation
        this.animationCompteur++;
        this.derniereAnimationTimestamp = ts;
    }

    return this.animationCompteur % this.nbrImagesAnimation;
};

PowerUp.prototype.clone = function() {
    return clone_json(this);
};
