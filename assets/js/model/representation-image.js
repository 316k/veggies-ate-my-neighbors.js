/**
 * Représentation abstraite du sprite d'un model.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 * @param String[] path
 * @param int[] colorisation
 */
function RepresentationImage(path, colorisation) {
    /**
     * Colorisation RGB à appliquer à l'image.
     */
    this.colorisation = colorisation || [];
    /**
     * Chemin à utiliser pour le blit de l'image. Chaque case est un step dans
     * la hiérarchie de fichier.
     */
    this.path = path || [];
    this.flipped = false;
}

RepresentationImage.prototype.clone = function() {
    return clone_json(this);
}
