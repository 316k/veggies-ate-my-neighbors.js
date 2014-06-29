$(document).ready(function() {
    $('#screen').click(function(event) {
        navigator.TerrainController.clic(
            new Point(
                (event.pageX - $(this).offset().left) / $(this).width() * document.getElementById('screen').width,
                (event.pageY - $(this).offset().top) / $(this).height() * document.getElementById('screen').height
            )
        );
    });
});

function TerrainController() {
    navigator.Vague = new Vague();
    this.terrain = new Terrain();
    /**
     * Permet de pause et de dépause.
     * (Boolean)
     */
    this.continuerThread = false;

    this.tic();
}

/**
 * Thread contrôllant les tics du terrain.
 */
TerrainController.prototype.tic = function() {
    if(this.continuerThread && 'FenetreController' in navigator) {
        var evenement = this.terrain.tic();
        navigator.Joueur.tic();

        if (evenement == navigator.TerrainEvent.GAME_OVER) {
            navigator.FenetreController.gameOver();
        } else if (evenement == navigator.TerrainEvent.NOUVELLE_VAGUE) {
            navigator.FenetreController.nouvelleVague(this.terrain.vague);
        } else if (evenement == navigator.TerrainEvent.MASSIVE_ATTACK) {
            navigator.FenetreController.massiveAttack();
        }
    }

    var self = this;
    setTimeout(function() {
        self.tic();
    }, navigator.refresh_rate);
};

/**
 * fais recommencer ou continuer le Thread.
 */
TerrainController.prototype.jouer = function() {
    this.continuerThread = true;
};

/**
 * Met le thread en pause.
 */
TerrainController.prototype.pause = function() {
    this.continuerThread = false;
};

/**
 * Donne un SpriteContainer contenant l'information relative aux images et
 * positions des combatants en jeu
 *
 * @return PositionnedSpriteContainer Les informations relatives aux images des combatants en jeu
 */
TerrainController.prototype.getImages = function() {
    var combatants = this.terrain.combattants;
    var powerups = this.terrain.powerUps;

    // PositionnedSpriteContainers
    var images = [];

    // TODO : Trier par valeur de y croissante pour faire un truc plus beau
    for (var index in combatants) {
        var combatant = combatants[index];
        images.push(new PositionnedSpriteContainer(combatant.hitbox.getLocation(), combatant.getSprite(), combatant.getAnimationCompteur()));
    }

    /* FIXME Unintuitive code should not exist in classes like this one. I'll rewrite it later...
    var sortedImage[] = images.toArray(new PositionnedSpriteContainer[images.size()]);
    for (int i = 0; i < images.size(); i++) {
        for (int j = i; j > 1 && sortedImage[j].getCoordonnee().y < sortedImage[j - 1].getCoordonnee().y; j--) {
            PositionnedSpriteContainer tmp;
            tmp = sortedImage[j];
            sortedImage[j] = sortedImage[j - 1];
            sortedImage[j - 1] = tmp;
        }
    }

    images = new ArrayList<>(Arrays.asList(sortedImage));
    */

    for (var index in powerups) {
        var powerup = powerups[index];
        images.push(new PositionnedSpriteContainer(powerup.hitbox.getLocation(), powerup.getSprite(), powerup.getAnimationCompteur()));
    }

    return images;
};

/**
 * Donne une action au terrain
 * @param Point point l'endroit où l'action a été faite.
 */
TerrainController.prototype.clic = function(point) {
    if (!(point.y > Terrain.TAILLE_CASE_Y && point.x < Terrain.TAILLE_CASE_X * Terrain.CASES_X)) {
        if ((point.x - Terrain.OFFSET_ITEMS) % (Terrain.ITEM_WIDTH + Terrain.MARGIN_ITEMS) < 66
                && (point.x - Terrain.OFFSET_ITEMS) > 0
                && (point.x - Terrain.OFFSET_ITEMS) < (Terrain.ITEM_WIDTH + Terrain.MARGIN_ITEMS) * navigator.Joueur.items.length) {
            navigator.Joueur.setSelection(parseInt((point.x - Terrain.OFFSET_ITEMS) / (Terrain.ITEM_WIDTH + Terrain.MARGIN_ITEMS)));
        } else {
            navigator.Joueur.setSelection(null);
            this.terrain.action(point);
        }
    } else {
        this.terrain.action(point);
    }
};

/**
 * @return int la vague en cours.
 */
TerrainController.prototype.getVague = function() {
    return this.terrain.vague;
};

/**
 * Augmente le pourcentage d'augmentation de zombies par vagues.
 * @param double pourcentageAugmentationVeggies
 */
TerrainController.prototype.setPourcentageAugmentationVeggies = function(pourcentageAugmentationVeggies) {
    navigator.Vague.setPourcentageAugmentationVeggies(pourcentageAugmentationVeggies);
};
