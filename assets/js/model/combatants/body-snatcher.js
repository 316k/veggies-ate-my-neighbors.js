/**
 * Rend le veggie attaqué gentil.
 * @author Guillaume Riou
 */
function BodySnatcher() {
    Combattant.apply(this);
    this.combattant_init();
    this.init();
}

BodySnatcher.prototype = new Combattant();

BodySnatcher.prototype.init = function() {
    this.vie = 100;
    this.gentil = true;
    this.hitbox.w = 80;
    this.hitbox.h = 80;
    this.lineOfSight.w = 80;
    this.lineOfSight.h = 80;
    this.animationFrameRate = 6;
    this.nbrImagesAnimation = {};
    this.nbrImagesAnimation[navigator.Etat.ATTENTE] = 1;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 2;
    this.etat = navigator.Etat.ATTENTE;

    this.vitesseAction[navigator.Action.ATTAQUE] = 1;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["plants", "body-snatcher", "waiting"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["plants", "body-snatcher", "attack"]);
};

BodySnatcher.prototype.tic = function() {
    switch (this.etat) {
        case navigator.Etat.ATTAQUE:
            this.attaquer(this.getNbrActions(navigator.Action.ATTAQUE));
            break;
    }

    return null;
};

/**
 * Rend le veggie attaqué gentil si le paramètre nbFois est >0.
 */
BodySnatcher.prototype.attaquer = function(nbFois) {
    if (nbFois > 0) {
        this.cibles[0].gentil = true;

        // Makes the opponent go backwards
        this.cibles[0].vitesseAction[navigator.Action.DEPLACEMENT] = (-this.cibles[0].vitesseAction[navigator.Action.DEPLACEMENT]);

        // Makes the opponent green (cause it's a plant now, get it ?)
        var colorisation = [123, 255, 123];
        for(var etat in this.cibles[0].sprites) {
            this.cibles[0].sprites[etat].colorisation = colorisation;
            this.cibles[0].sprites[etat].flipped = true;
        }

        this.cibles[0].setEtat(navigator.Etat.DEPLACEMENT);
        this.cibles[0].cibles = null;
        this.cibles.shift();
        this.vie -= 50; // Transforming a veggie costs life
    }

    if (!this.cibles.length) {
        this.setEtat(navigator.Etat.ATTENTE);
    }

    return;
};

BodySnatcher.prototype.action = function(times) {
    return null;
};
