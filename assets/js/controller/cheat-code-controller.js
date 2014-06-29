/**
 * Controller for cheat codes
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function CheatCodeController() {
    this.cheat_code = "";
    this.max_chars = 12;

    var self = this;
    $('body').keydown(function(e) {
        self.onkeytyped(String.fromCharCode(e.which));
    });
}

CheatCodeController.prototype.onkeytyped = function(character) {
    if(!character.match("\\w")) {
        return;
    }


    this.cheat_code += character;
    console.log(this.cheat_code);
    if (this.cheat_code.toLowerCase().endsWith("helios")) {
        navigator.Joueur.nbrSoleils += 100;
    } else if (this.cheat_code.toLowerCase().endsWith("monsinti")) {
        navigator.TerrainController.setPourcentageAugmentationVeggies(1.5);
    } else if (this.cheat_code.toLowerCase().match("^.*r[3|e][g|6][3|e]x+$")) {
        navigator.FenetreController.fenetre.setMessage("/You know RegEx \\?!/", 5000);
        navigator.Joueur.nbrSoleils += 9999999;
    } else if (this.cheat_code.toLowerCase().endsWith("esrever")) {
        navigator.SpriteManager.sprites = {};
        navigator.SpriteManager.easter_egg = !navigator.SpriteManager.easter_egg;
    } else if (this.cheat_code.toLowerCase().endsWith("alive")) {
        var items = navigator.Joueur.items;
        for (var item in items) {
            items[item].vitesseRechargement = 100000;
        }
    }

    // Store only a portion of the characters typed
    if (this.cheat_code.length == this.max_chars) {
        this.cheat_code = this.cheat_code.substring(1, this.max_chars);
    }
};
