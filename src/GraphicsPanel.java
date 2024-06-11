import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
    public final Game game;
    private final boolean[] pressedKeys;
    private Point mouseLocation;
    private long lastFrameTime;
    private long thisFrameTime;
    private int minRange, optimalRange, maxRange;
    private boolean leftMouseHeld;

    public GraphicsPanel() {
        setBackground(Color.BLACK);

        game = new Game();
        Weapon weapon = game.player.getWeapon();
        minRange = weapon.getMinRange();
        optimalRange = weapon.getOptimalRange();
        maxRange = weapon.getMaxRange();

        pressedKeys = new boolean[128];

        leftMouseHeld = false;

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        addMouseMotionListener(this);
        requestFocusInWindow(); // see comment above
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Player player = game.player;

        g.setColor(Color.WHITE);

        // FPS counter
        thisFrameTime = System.nanoTime();
        int FPS = (int) Math.pow((thisFrameTime - lastFrameTime) / Math.pow(10, 9), -1);
        g.drawString("FPS: " + FPS, 0, 10);
        lastFrameTime = thisFrameTime;

        //Sprint amount counter
        Color gold = new Color(194, 162, 0);
        g.setColor(gold);
        g.fillRect(MainFrame.windowWidth - 80, MainFrame.windowHeight - 420, 70, 40);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 22));
        g.drawString("Energy", MainFrame.windowWidth - 79, MainFrame.windowHeight - 390);
        g.setColor(gold);
        g.fillRect(MainFrame.windowWidth - 60, MainFrame.windowHeight - 380, 30, 340);
        g.setColor(Color.GREEN);
        g.fillRect(MainFrame.windowWidth - 55, MainFrame.windowHeight - (50 + (player.energy / 312)), 20, player.energy / 312);

        Color transparentBrown = new Color(91, 63, 0, 90);
        g.setColor(transparentBrown);
        g.fillRect(MainFrame.windowWidth - 375, 25, 350, 100);
        if (player.weapon instanceof Shotgun) {
            g.drawImage(player.weapon.getImage(),MainFrame.windowWidth - 375, 0, null);
        } else if (player.weapon instanceof MachineGun) {
            g.drawImage(player.weapon.getImage(),MainFrame.windowWidth - 375, -50, null);
        } else if (player.weapon instanceof SniperRifle) {
            g.drawImage(player.weapon.getImage(),MainFrame.windowWidth - 375, 30, null);

        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 12));
        g.drawString(player.weapon.getAmmo() + "/" + player.weapon.maxAmmo, MainFrame.windowWidth - 75, 110);

        // Player sprite
        Rectangle r = player.getSpriteRect();
        g.fillRect(r.x, r.y, r.width, r.height);

        if (mouseLocation != null) {
            Color transparentGreen = new Color(0, 255, 0, 100);
            g.setColor(transparentGreen);

            double lineDistance = Math.hypot(mouseLocation.x - (r.x + (double) r.width / 2), mouseLocation.y - (r.y + (double) r.height / 2));
            double cosine = (mouseLocation.x - (r.x + (double) r.width / 2)) / lineDistance;
            double sine = (mouseLocation.y - (r.y + (double) r.height / 2)) / lineDistance;

            if (sine > 0) {
                player.weapon.setAngle(Math.acos(cosine));
            } else {
                player.weapon.setAngle(-Math.acos(cosine));
            }

            if (lineDistance < minRange) {
                g.setColor(Color.GREEN);
                g.fillOval(mouseLocation.x - 2, mouseLocation.y - 2, 4, 4);
            } else if (lineDistance < optimalRange ) {
                g.drawLine((int) player.getX() + (int) (minRange * cosine), (int) player.getY() + (int) (minRange * sine), mouseLocation.x, mouseLocation.y);
                g.setColor(Color.GREEN);
                g.fillOval(mouseLocation.x - 2, mouseLocation.y - 2, 4, 4);
            } else if (lineDistance > optimalRange) {
                g.drawLine((int) player.getX() + (int) (minRange * cosine), (int) player.getY() + (int) (minRange * sine), (int) (mouseLocation.x - (lineDistance - optimalRange) * (cosine)), (int) (mouseLocation.y - (lineDistance - optimalRange) * (sine)));
                Color transparentRed = new Color(255, 0, 0, 100);
                g.setColor(transparentRed);

                int endX;
                if (mouseLocation.x > player.getX()) {
                    endX = (int) Math.min(mouseLocation.x - (lineDistance - maxRange) * (cosine), mouseLocation.x);
                } else {
                    endX = (int) Math.max(mouseLocation.x - (lineDistance - maxRange) * (cosine), mouseLocation.x);
                }

                int endY;
                if (mouseLocation.y > player.getY()) {
                    endY = (int) Math.min(mouseLocation.y - (lineDistance - maxRange) * (sine), mouseLocation.y);
                } else {
                    endY = (int) Math.max(mouseLocation.y - (lineDistance - maxRange) * (sine), mouseLocation.y);
                }

                g.drawLine((int) (mouseLocation.x - (lineDistance - optimalRange) * (cosine)), (int) (mouseLocation.y - (lineDistance - optimalRange) * (sine)), endX, endY);
                g.setColor(Color.RED);
                g.fillOval(mouseLocation.x - 2, mouseLocation.y - 2, 4, 4);
            }
            if (player.weapon.getReloadCounter() > 0) {
                g.setColor(Color.GREEN);
                g.drawArc(mouseLocation.x - 4, mouseLocation.y - 4, 8, 8, 0, (int) (((double) player.weapon.getReloadCounter() / player.weapon.getReload()) * 360));
            }
        }

        // Draw projectiles
        g.setColor(Color.YELLOW);
        ArrayList<Projectile> projectiles = player.weapon.getProjectiles();
        if (projectiles != null) {
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile shell = projectiles.get(i);
                if (player.weapon instanceof SniperRifle) {
                    g.fillRect((int) shell.getX(), (int) shell.getY(), 2, 2);
                } else {
                    g.fillRect((int) shell.getX(), (int) shell.getY(), 1, 1);
                }

            }
        }

        // Draw mushrooms
        Color brown = new Color(255, 255, 255);
        g.setColor(brown);
        ArrayList<ArrayList<Mushroom>> mushrooms = game.mushrooms;
        for (int i = 0; i < mushrooms.size(); i++) {
            for (int j = 0; j < mushrooms.get(i).size(); j++) {
                Mushroom mushroom = mushrooms.get(i).get(j);
                Rectangle mushroomRect = mushroom.getSpriteRect();
                g.fillRect(mushroomRect.x, mushroomRect.y, mushroomRect.width, mushroomRect.height);
            }
        }
    }

    public void playerMove() {
        game.player.move(pressedKeys, 1.0);
    }
    public void shellMove() {
        ArrayList<Projectile> projectiles = game.player.weapon.getProjectiles();
        if (projectiles != null) {
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile projectile = projectiles.get(i);
                projectile.move();
                if (projectile.isOffMap()){
                    projectiles.remove(i);
                    i--;
                } else {
                    if (projectile.collision()) {
                        ArrayList<Sprite> collidedObjects = projectile.getCollidedObjects();
                        for (int j = 0; j < collidedObjects.size(); j++) {
                            if (collidedObjects.get(j) instanceof Mushroom collidedMushroom) {
                                projectile.calcDamage();

                                if (Math.random() < ((double) collidedMushroom.getHP() / projectile.getDamage())) {
                                    game.player.weapon.getProjectiles().remove(projectile);
                                    if (projectile instanceof SniperProjectile) {
                                    }
                                    break;
                                } else {
                                    for (int k = 0; k < game.mushrooms.size(); k++) {
                                        game.mushrooms.get(k).remove(collidedMushroom);
                                    }
                                    game.getCollidables().remove(collidedMushroom);
                                    projectile.setCollision(false);
                                    projectile.calcDamage();
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        pressedKeys[key] = true;
        if (pressedKeys[KeyEvent.VK_R]) {
            game.player.weapon.reload();
        } else if (pressedKeys[KeyEvent.VK_E]) {
            game.player.weapon.getProjectiles().clear();
            if (game.player.weapon instanceof Shotgun) {
                game.player.weapon = game.player.machineGun;
            } else if (game.player.weapon instanceof MachineGun) {
                game.player.weapon = game.player.sniperRifle;
            } else if (game.player.weapon instanceof SniperRifle) {
                game.player.weapon = game.player.shotgun;
            }
            minRange = game.player.weapon.getMinRange();
            optimalRange = game.player.weapon.getOptimalRange();
            maxRange = game.player.weapon.getMaxRange();
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            leftMouseHeld = true;
        } else if (e.getButton() == MouseEvent.BUTTON3){
            Point mouseClickLocation = e.getPoint();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            leftMouseHeld = false;
        } else if (e.getButton() == MouseEvent.BUTTON3){
            Point mouseClickLocation = e.getPoint();
        }
    }

    public boolean isLeftMouseHeld() {
        return leftMouseHeld;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

        setCursor(blankCursor);
        mouseLocation = e.getPoint();
    }
    @Override
    public void mouseExited(MouseEvent e) {
        mouseLocation = null;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseLocation = e.getPoint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseLocation = e.getPoint();
    }
}
