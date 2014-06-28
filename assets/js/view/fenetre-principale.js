/**
 * Fenêtre de jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function FenetrePrincipale() {
    this.WIDTH = (Terrain.CASES_X + 1) * Terrain.TAILLE_CASE_X;
    this.HEIGHT = (Terrain.CASES_Y + 1) * Terrain.TAILLE_CASE_Y;

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
};

FenetrePrincipale.prototype.draw_sprite = function(sprite) {
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
    /*
    // Game panel items
    Item[] items = JoueurControlleur.getItems();
    Point position = new Point(OFFSET_ITEMS, (int) (1 / 8.0 * TAILLE_CASE_Y));
    Integer selection = JoueurControlleur.getSelection();
    for (Item item : items) {
        PositionnedSpriteContainer itemSprite = new PositionnedSpriteContainer(position, new RepresentationImage(new String[]{"panel", "box", item.getNom()}), 0);

        blitSpriteContainer(g, itemSprite);

        // Blit la recharge de l'item
        g.setColor(item.getRecharge() == 1 ? Color.green : Color.red);
        g.fillRect((int) (position.x + 2 / 16.0 * TAILLE_CASE_X), (int) (11 / 16.0 * TAILLE_CASE_Y), (int) (item.getRecharge() * 19 / 32.0 * TAILLE_CASE_X) - 1, (int) (1 / 8.0 * TAILLE_CASE_Y));

        g.setColor(Color.black);
        g.drawRect((int) (position.x + 2 / 16.0 * TAILLE_CASE_X), (int) (11 / 16.0 * TAILLE_CASE_Y), (int) (19 / 32.0 * TAILLE_CASE_X) - 1, (int) (1 / 8.0 * TAILLE_CASE_Y));

        // Blit le coût de l'item (en gris si l'item est non utilisable)
        g.setColor(item.isUtilisable() ? Color.black : Color.gray);
        g.setFont(sunFont);
        g.drawString("" + item.getCout(), position.x + ITEM_WIDTH / 5, (int) (position.y * 6.2));

        // Item sélectionné
        if (selection != null && item.equals(items[selection])) {
            g.setColor(new Color(255, 170, 0, 170));
            g.fillRect(itemSprite.getCoordonnee().x, itemSprite.getCoordonnee().y, SpriteManager.getImage(sprite).getWidth(this), SpriteManager.getImage(sprite).getHeight(this));
        }

        position.move((int) (position.x + ITEM_WIDTH + MARGIN_ITEMS), position.y);
    }*/
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
