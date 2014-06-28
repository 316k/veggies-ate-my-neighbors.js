/**
 * Conteneur pour les sprites (RepresentationImage + animation)
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function SpriteContainer(image, animation) {
    /**
     * RepresentationImage représentation de l'image (chemin, flippé, colorisation)
     */
    this.image = image;
    /**
     * numéro de frame actuel de l'animation.
     */
    this.animation = animation;
    /**
     * Si on doit flipper l'image.
     */
    this.flip = image ? image.flipped : false;
}

/**
 * Reconstruit la string de l'adresse de l'image.
 * @return 
 */
SpriteContainer.prototype.getPath = function() {
    return this.image.path.join('/') + '/' + this.animation + ".png";;
};

SpriteContainer.prototype.toString = function() {
    var copy = {
        image: clone_json(this.image),
        animation: this.animation,
        flip: this.flip
    };

    return JSON.stringify(copy);
};
