/**
 * Plante qui produit du soleil.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Sunflower() {
    Combattant.apply(this);
    this.vie = 10;
    this.initialise();
}

Sunflower.prototype = new Combattant();

Sunflower.prototype.initialise = function() {
    this.gentil = true;
    this.hitbox.w = 80;
    this.hitbox.h = 80;
    this.lineOfSight = new Rectangle();
    this.animationFrameRate = 6;

    this.nbrImagesAnimation[navigator.Etat.ATTENTE] = 4;
    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 5;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 1;

    this.vitesseAction[navigator.Action.ACTION] = 1/30;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["plants", "sunflower"]);

    this.etat = navigator.Etat.ATTENTE;
};

Sunflower.prototype.tic = function() {
    var soleil = null;
    switch (this.etat) {
        case navigator.Etat.ATTENTE:
            soleil = this.action(this.getNbrActions(navigator.Action.ACTION));
            break;
    }

    return soleil;
};

Sunflower.prototype.action = function(nbFois) {

//        for (int i = 0; i < nbFois; i++) {
//
//            long ts = System.currentTimeMillis();
//            Soleil soleil = null;
//
//            if (this.soleil != null && !this.soleil.isUsed()) {
//                dernierSoleilTimestamp = ts;
//            } else if (ts - dernierSoleilTimestamp >= tempsGenerationSoleil * 1000.0) {
//                soleil = new Soleil(25, hitbox.x, hitbox.y);
//                dernierSoleilTimestamp = ts;
//                this.soleil = soleil;
//            }
//        }
    if (nbFois > 0) {
        return new Soleil(25, this.hitbox.x, this.hitbox.y);
    } else {
        return null;
    }
};
