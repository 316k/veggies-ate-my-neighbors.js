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
    this.cheat_code += character;
    if (this.cheat_code.endsWith("galarneau")) {
        navigator.JoueurController.addSoleils(100);
    } else if (this.cheat_code.endsWith("Monsanté")) {
        navigator.TerrainController.setPourcentageAugmentationVeggies(1.5);
    } else if (this.cheat_code.match("^.*r[3|e][g|6][3|e]x+$")) {
        navigator.FenetreController.easterEgg("/You know RegEx \\?!/");
        navigator.JoueurController.addSoleils(9999999);
    } else if (this.cheat_code.endsWith("esrever")) {
        navigator.FenetreController.easterEgg();
    } else if (this.cheat_code.endsWith("It's alive !")) {
        navigator.JoueurController.easterEgg();
    }

    // Store only a portion of the characters typed
    if (this.cheat_code.length == this.max_chars) {
        this.cheat_code = this.cheat_code.substring(1, this.max_chars);
    }
};
