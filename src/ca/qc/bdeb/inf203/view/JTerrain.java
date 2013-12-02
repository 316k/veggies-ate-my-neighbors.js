package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.controller.JoueurControlleur;
import ca.qc.bdeb.inf203.model.Item;
import ca.qc.bdeb.inf203.controller.TerrainControlleur;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JTerrain extends JPanel {

    private final int CASES_X = 10;
    private final int CASES_Y = 6;
    private final int TAILLE_CASE_X = 80;
    private final int TAILLE_CASE_Y = 80;
    private final int WIDTH = CASES_X * TAILLE_CASE_X;
    private final int HEIGHT = CASES_Y * TAILLE_CASE_Y;
    private final int ITEM_WIDTH = 65;
    private final int OFFSET_ITEMS = (int) (1.2 * TAILLE_CASE_X);
    private final int MARGIN_ITEMS = (int) (0.2 * ITEM_WIDTH);
    private PositionnedSpriteContainer[][] background;
    private PositionnedSpriteContainer panel;
    /**
     * Les coordonnées du point correspondent à un nombre de cases en x et y
     */
    private Point caseSelectionnee = new Point(-1, -1);
    private Font sunFont;
    private Font infosFont;
    private Font scoreFont;

    public JTerrain() {
        try {
            GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/PressStart2P.ttf")));
            this.sunFont = new Font("Press Start 2P", Font.PLAIN, 13);
            this.infosFont = new Font("Press Start 2P", Font.BOLD, 20);
            this.scoreFont = new Font("Press Start 2P", Font.PLAIN, 16);
        }
        catch (FontFormatException | IOException ex) {
            Logger.getLogger(JTerrain.class.getName()).log(Level.SEVERE, null, ex);
            // Fallback
            this.sunFont = new Font("Arial", Font.PLAIN, 18);
            this.infosFont = new Font("Arial", Font.BOLD, 32);
            this.scoreFont = new Font("Arial", Font.BOLD, 26);
        }

        this.setLayout(null);
        background = new PositionnedSpriteContainer[CASES_X][CASES_Y];


        generateBackground();

        String path[] = {"panel"};
        panel = new PositionnedSpriteContainer(new Point(0, 0), new RepresentationImage(path), 0);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                refreshCaseSelectionnee(e);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getY() > TAILLE_CASE_Y) {
                    // Le clic est dans le terrain
                    TerrainControlleur.clic(e.getPoint());
                    refreshCaseSelectionnee(e);
                } else {
                    // Le clic est dans le panel
                    if ((e.getPoint().x - OFFSET_ITEMS) % (ITEM_WIDTH + MARGIN_ITEMS) < 66 && (e.getPoint().x - OFFSET_ITEMS) > 0 && (e.getPoint().x - OFFSET_ITEMS) < (ITEM_WIDTH + MARGIN_ITEMS) * JoueurControlleur.getItems().length) {
                        JoueurControlleur.setSelection((e.getPoint().x - OFFSET_ITEMS) / (ITEM_WIDTH + MARGIN_ITEMS));
                    } else {
                        JoueurControlleur.setSelection(null);
                    }
                }
            }
        });

        this.setVisible(true);
    }

    protected void blitSpriteContainer(Graphics g, PositionnedSpriteContainer sprite) {
        g.drawImage(SpriteManager.getImage(sprite),
                sprite.getCoordonnee().x, sprite.getCoordonnee().y, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        for (PositionnedSpriteContainer[] spriteRow : background) {
            for (PositionnedSpriteContainer sprite : spriteRow) {
                if (sprite != null) {
                    blitSpriteContainer(g, sprite);
                }
            }
        }

        // Game Panel
        blitSpriteContainer(g, panel);

        // Soleils
        String[] path = {"panel", "box", "sun"};
        RepresentationImage ri = new RepresentationImage(path);
        PositionnedSpriteContainer sprite = new PositionnedSpriteContainer(new Point((int) (0.1 * TAILLE_CASE_X), TAILLE_CASE_Y / 8), ri, 0);

        blitSpriteContainer(g, sprite);

        g.setColor(Color.black);
        g.setFont(sunFont);
        g.drawString("" + JoueurControlleur.getSoleils(), (int) (0.2 * TAILLE_CASE_X), 13 * TAILLE_CASE_Y / 16);

        // Infos
        g.setColor(Color.white);
        g.setFont(infosFont);
        int positionInfosX = WIDTH - (int) (2.8 * TAILLE_CASE_X);
        g.drawString("Vague 1", positionInfosX, 4 * TAILLE_CASE_Y / 8);
        g.setFont(scoreFont);
        g.drawString("Kills : " + JoueurControlleur.getKills(), positionInfosX, 7 * TAILLE_CASE_Y / 8);

        // Game panel items
        Item[] items = JoueurControlleur.getItems();
        Point position = new Point(OFFSET_ITEMS, (int) (1 / 8.0 * TAILLE_CASE_Y));
        Integer selection = JoueurControlleur.getSelection();
        for (Item item : items) {
            String[] itemPath = {"panel", "box", item.getNom()};
            RepresentationImage itemRi = new RepresentationImage(itemPath);
            PositionnedSpriteContainer itemSprite = new PositionnedSpriteContainer(position, itemRi, 0);

            blitSpriteContainer(g, itemSprite);

            g.setColor(item.getRecharge() == 1 ? Color.green : Color.red);
            g.fillRect((int) (position.x + 2 / 16.0 * TAILLE_CASE_X), (int) (11 / 16.0 * TAILLE_CASE_Y), (int) (item.getRecharge() * 19 / 32.0 * TAILLE_CASE_X) - 1, (int) (1 / 8.0 * TAILLE_CASE_Y));

            g.setColor(Color.black);
            g.drawRect((int) (position.x + 2 / 16.0 * TAILLE_CASE_X), (int) (11 / 16.0 * TAILLE_CASE_Y), (int) (19 / 32.0 * TAILLE_CASE_X) - 1, (int) (1 / 8.0 * TAILLE_CASE_Y));

            g.setColor(item.getRecharge() == 1 ? Color.black : Color.gray);
            g.setFont(sunFont);
            g.drawString("" + item.getCout(), position.x + ITEM_WIDTH / 3, (int) (position.y * 6.2));

            if (selection != null && item.equals(items[selection])) {
                g.setColor(new Color(255, 170, 0, 170));
                g.fillRect(itemSprite.getCoordonnee().x, itemSprite.getCoordonnee().y, SpriteManager.getImage(sprite).getWidth(this), SpriteManager.getImage(sprite).getHeight(this));
            }

            position.move((int) (position.x + ITEM_WIDTH + MARGIN_ITEMS), position.y);
        }

        // Combatants
        PositionnedSpriteContainer[] sprites = TerrainControlleur.getImages();

        for (PositionnedSpriteContainer combatantSprite : sprites) {
            blitSpriteContainer(g, combatantSprite);
        }

        // Case sélectionnée
        if (caseSelectionnee != null) {
            g.setColor(new Color(255, 170, 0));
            g.drawRect(caseSelectionnee.x * TAILLE_CASE_X, caseSelectionnee.y * TAILLE_CASE_Y, TAILLE_CASE_X, TAILLE_CASE_Y);
        }

        // Refresh
        try {
            Thread.sleep(40);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(JTerrain.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.repaint();
    }

    private void generateBackground() {
        Random rnd = new Random();

        // Gazon
        for (int x = 0; x < CASES_X - 1; x++) {
            for (int y = 0; y < CASES_Y; y++) {
                Point position = new Point(x * TAILLE_CASE_X, y * TAILLE_CASE_Y);
                String path[] = {"background", "grass"};
                int[] colorization = {255, 0, 0};
                RepresentationImage ri = new RepresentationImage(colorization, path);
                background[x][y] = new PositionnedSpriteContainer(position, ri, rnd.nextInt(4));
            }
        }

        // Route
        String path[] = {"background", "road"};
        RepresentationImage route = new RepresentationImage(null, path);
        background[CASES_X - 1][0] = new PositionnedSpriteContainer(new Point((CASES_X - 1) * TAILLE_CASE_X, 0), route, 0);
    }

    private void refreshCaseSelectionnee(MouseEvent e) {
        if (caseSelectionnee != null && e.getPoint().distance(caseSelectionnee) < 20) {
            return;
        }
        // Si un item est sélectionné, on affiche la case sous la souris
        if (JoueurControlleur.getSelection() != null && e.getPoint().x < TAILLE_CASE_X * (CASES_X - 1) && e.getPoint().y > TAILLE_CASE_Y * 1) {
            caseSelectionnee = new Point(e.getPoint().x / TAILLE_CASE_X, e.getPoint().y / TAILLE_CASE_Y);
        } else {
            caseSelectionnee = null;
        }
    }
}
