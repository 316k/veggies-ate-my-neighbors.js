/**
 * Fenêtre de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function FenetrePrincipale() {
    this.WIDTH = (Terrain.CASES_X + 1) * Terrain.TAILLE_CASE_X;
    this.HEIGHT = (Terrain.CASES_Y + 1) * Terrain.TAILLE_CASE_Y;
    this.OFFSET_ITEMS = parseInt(1.2 * Terrain.TAILLE_CASE_X);
    this.ITEM_WIDTH = 75;

    this.canvas = document.getElementById('screen');
    this.canvas.width = this.WIDTH;
    this.canvas.height = this.HEIGHT;

    this.context = this.canvas.getContext('2d');
    this.blink = false;
    $('#notifications').hide();

    this.background = this.generate_background();
}

/**
 * Affiche le message passé en paramètre.
 * @param String message le message
 * @param int delais 
 */
FenetrePrincipale.prototype.setMessage = function(message, delais) {
    $('#notifications').text(message).show().fadeOut(delais);
};

FenetrePrincipale.prototype.repaint = function() {
    this.draw_background();
    this.draw_panel();
    this.draw_suns();
    this.draw_infos();
    this.draw_entities();
};

FenetrePrincipale.prototype.draw_sprite = function(sprite) {
    var image = navigator.SpriteManager.getImage(sprite);
    this.context.drawImage(navigator.SpriteManager.getImage(sprite), sprite.position.x, sprite.position.y);
};

FenetrePrincipale.prototype.draw_background = function() {
    for(var x = 0; x < this.background.length; x++) {
        for(var y = 0; y < this.background[x].length; y++) {
            this.draw_sprite(this.background[x][y]);
        }
    }
};

FenetrePrincipale.prototype.draw_panel = function() {
    this.draw_sprite(new PositionnedSpriteContainer(new Point(0, 0), new RepresentationImage(["panel"], null), 0));
    // Game panel items
    var items = navigator.Joueur.items;
    var position = new Point(this.OFFSET_ITEMS, parseInt(1/8 * Terrain.TAILLE_CASE_Y));
    var selection = navigator.Joueur.selection;

    for (var index in items) {
        var item = items[index];

        var itemSprite = new PositionnedSpriteContainer(position, new RepresentationImage(["panel", "box", item.nom], null), 0);

        this.draw_sprite(itemSprite);

        // Recharge
        this.context.fillStyle = 'black';
        this.context.fillRect(parseInt(position.x + 2/16*Terrain.TAILLE_CASE_X), parseInt(11 / 16.0 * Terrain.TAILLE_CASE_Y), parseInt(19/32*Terrain.TAILLE_CASE_X) - 1, parseInt(1/8*Terrain.TAILLE_CASE_Y));

        this.context.fillStyle = item.recharge == 1 ? 'green' : 'red';
        this.context.fillRect(parseInt(position.x + 2/16 * Terrain.TAILLE_CASE_X) + 2, parseInt(11/16 * Terrain.TAILLE_CASE_Y) + 2, parseInt(item.recharge*19/32*Terrain.TAILLE_CASE_X) - 5, parseInt(1/8*Terrain.TAILLE_CASE_Y) - 4);


        // Item cost (in gray for unavailable items)
        this.context.fillStyle = item.isUtilisable() ? 'black' : 'gray';
        this.context.font = '12px press-start';
        this.context.fillText(item.cout, position.x + this.ITEM_WIDTH / 5, parseInt(position.y * 5.5));

        // Item sélectionné
        /*
        if (selection != null && item.equals(items[selection])) {
            g.setColor(new Color(255, 170, 0, 170));
            g.fillRect(itemSprite.getCoordonnee().x, itemSprite.getCoordonnee().y, SpriteManager.getImage(sprite).getWidth(this), SpriteManager.getImage(sprite).getHeight(this));
        }
        */

        position.x += this.ITEM_WIDTH;
    }
};

FenetrePrincipale.prototype.draw_suns = function() {
    var path = ["panel", "box", "sun"];
    var ri = new RepresentationImage(path);
    var sprite = new PositionnedSpriteContainer(new Point(parseInt(0.1*Terrain.TAILLE_CASE_X), Terrain.TAILLE_CASE_Y/8), ri, 0);

    this.draw_sprite(sprite);

    // Number
    this.context.fillStyle = 'black';
    this.context.font = '10px press-start';
    this.context.fillText(navigator.Joueur.nbrSoleils, (0.2 * Terrain.TAILLE_CASE_X), 13 * Terrain.TAILLE_CASE_Y / 16);
};

FenetrePrincipale.prototype.draw_infos = function() {
    // Infos
    this.context.fillStyle = 'white';
    this.context.font = '22px press-start';
    var positionInfosX = this.WIDTH - parseInt(2.8 * Terrain.TAILLE_CASE_X);
    this.context.fillText("Wave : " + navigator.TerrainController.terrain.vague, positionInfosX, 4 * Terrain.TAILLE_CASE_Y / 8);
    this.context.font = '16px press-start';
    this.context.fillText("Kills : " + navigator.Joueur.kills, positionInfosX, 7 * Terrain.TAILLE_CASE_Y / 8);
};

FenetrePrincipale.prototype.draw_entities = function() {
    var sprites = navigator.TerrainController.getImages();

    for (var index in sprites) {
        var sprite = sprites[index];
        this.draw_sprite(sprite);
    }
};

/**
 * Génère une séquence de gazon aléatoire.
 */
FenetrePrincipale.prototype.generate_background = function() {
    var background = [];
    // Gazon
    for (var x = 0; x < Terrain.CASES_X; x++) {
        background[x] = [];
        for (var y = 0; y <= Terrain.CASES_Y; y++) {
            var position = new Point(x * Terrain.TAILLE_CASE_X, y * Terrain.TAILLE_CASE_Y);
            var ri = new RepresentationImage(["background", "grass"]);
            background[x][y] = new PositionnedSpriteContainer(position, ri, Math.round(rand(0, 3)));
        }
    }

    // Route
    var route = new RepresentationImage(["background", "road"], null);
    background[Terrain.CASES_X] = [];
    background[Terrain.CASES_X][0] = new PositionnedSpriteContainer(new Point((Terrain.CASES_X) * Terrain.TAILLE_CASE_X, 0), route, 0);

    return background;
};
