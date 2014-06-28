/**
 * Élément de jeu ayant une position, une taille et une animation
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function Entite() {
    /**
     * Représentation des images selon les états d'une entité.
     * HashMap<Etat, RepresentationImage>
     */
    this.sprites = {};
    /**
     * État d'une entité.
     */
    this.etat = null;
    /**
     * Hitbox d'une entité. Utilisée pour les collisions.
     */
    this.hitbox = new Rectangle();
    /**
     * Compteur servant à animer l'entité.
     * int
     */
    this.animationCompteur = 0;
}

/**
 * Returns the sprite for the current state
 * @return RepresentationImage
 */
Entite.prototype.getSprite = function() {
    return this.sprites[this.getEtat()];
};

/**
 * @return int
 */
Entite.prototype.getAnimationCompteur = function() {};

Entite.prototype.getEtat = function() {};

Entite.prototype.clone = function() {
    var clone = clone_json(this);
    return clone;
}
