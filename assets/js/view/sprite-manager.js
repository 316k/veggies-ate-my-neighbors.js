/**
 * Gestionnaire d'images, de colorisation et de cache.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function SpriteManager() {
    /**
     * Hashmap : JSON du SpriteContainer, Sprite de l'animation.
     */
    this.sprites = {};
    this.easterEgg = false;
}

/**
 * Donne le sprite d'une image voulue
 * @todo Implémenter la colorisation
 *
 * @param ri Représentation de l'image
 * @param animation numéro d'animation
 * @return Image le sprite à blitter
 */
SpriteManager.prototype.getImage = function(sprite) {
    var image;
    if (sprite.toString() in this.sprites) {
        image = this.sprites[sprite.toString()];
    } else {
        image = this.loadSprite(sprite);
    }

    return image;
};

/**
 * Charge en cache une image.
 *
 * @param SpriteContainer sprite
 * @return BufferedImage Le sprite loadé
 */
SpriteManager.prototype.loadSprite = function(sprite) {
    var fichier = "assets/img/";

//    console.log("Loading " + sprite);

    fichier += "/" + sprite.getPath();

    var image = new Image();
    image.src = fichier;

    /*
    // TODO : Colorisation
    if (sprite.representationImage.colorisation != null) {
    }

    // TODO : Flip
    if (sprite.isFlipped() || easterEgg) {
    }
    */

    this.sprites[sprite.toString()] = image;

    return image;
};

    /**
     * Secret.
     */
SpriteManager.prototype.easterEgg = function() {
    this.sprites = {};
    easterEgg = !easterEgg;
};
