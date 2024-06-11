import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Weapon implements ActionListener {
    private final Image image;
    public double angle;
    private final ArrayList<Projectile> projectiles;
    private final int maxDamage;
    private final int minRange, optimalRange, maxRange;
    private final double initialSpeed;
    private final double drag;
    public boolean fire;
    public int maxAmmo;
    private int ammo;
    private int reload;
    private int reloadCounter;
    public final Timer refireTimer;
    private final Timer reloadTimer;
    private boolean refire;


    public Weapon(String imageFile, double angle, int maxDamage, int minRange, int optimalRange, int maxRange, double initialSpeed, double drag, int maxAmmo, int reload, int refireTime, int ammo) {
        ImageIcon imageIcon = new ImageIcon(imageFile);
        image = imageIcon.getImage();
        this.angle = angle;
        this.maxDamage = maxDamage;
        this.minRange = minRange;
        this.optimalRange = optimalRange;
        this.maxRange = maxRange;
        this.initialSpeed = initialSpeed;
        this.drag = drag;
        fire = false;

        projectiles = new ArrayList<>();

        this.maxAmmo = maxAmmo;
        this.ammo = ammo;
        this.reload = reload;
        reloadCounter = 0;


        refireTimer = new Timer(refireTime, this);
        reloadTimer = new Timer(1, this);

        refire = true;
    }

    public Image getImage() {
        return image;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getOptimalRange() {
        return optimalRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public double getInitialSpeed() {
        return initialSpeed;
    }

    public double getDrag() {
        return drag;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setRefire(boolean refire) {
        this.refire = refire;
    }

    public boolean getRefire() {
        return refire;
    }

    public int getReload() {
        return reload;
    }

    public int getReloadCounter() {
        return reloadCounter;
    }

    public void fire(int x, int y, ArrayList<ArrayList<Grid>> allGrids, Rectangle map) { }

    public void reload() {
        reloadTimer.start();
        ammo = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refireTimer) {
            refire = true;
        } else if (e.getSource() == reloadTimer) {
            reloadCounter++;
            if (reloadCounter >= reload) {
                reloadCounter = 0;
                ammo = maxAmmo;
                reloadTimer.stop();
            }
        }
    }
}
