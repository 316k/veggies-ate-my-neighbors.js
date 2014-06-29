function FenetreController() {
    this.fenetre;
    this.init();
}

/**
 * The "Thread" that tells the window to repaint itself
 */
FenetreController.prototype.tic = function() {
    this.fenetre.repaint();

    var self = this;

    setTimeout(function() {
        self.tic();
    }, navigator.refresh_rate);
};


/**
 * Initialisation de la fenêtre.
 */
FenetreController.prototype.init = function() {
    this.fenetre = new FenetrePrincipale();
    navigator.TerrainController.jouer();
    this.tic();
};

/**
 * Afficher un message d'attaque massive.
 */
FenetreController.prototype.massiveAttack = function() {
    this.fenetre.blink = true;
    this.fenetre.setMessage("Massive Attack !", 3000);
};

/**
 * Afficher un message de nouvelle vague.
 */
FenetreController.prototype.nouvelleVague = function(vague) {
    this.fenetre.blink = false;
    this.fenetre.setMessage("Vague " + vague + " !", 3000);
};

/**
 * Afficher un message de fin de partie
 */
FenetreController.prototype.gameOver = function() {
    this.fenetre.blink = true;
    this.fenetre.setMessage("You lost THE GAME", null);
    this.fenetre.display_restart();
    navigator.TerrainController.pause();
};

/**
 * Secret.
 *
 * @param String egg secret
 */
FenetreController.prototype.easterEgg = function(egg) {
    this.fenetre.setMessage(egg);
};

/**
 * Secret.
 */
FenetreController.prototype.easterEgg = function() {
    navigator.SpriteManager.easterEgg();
};
