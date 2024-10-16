import java.awt.*;
import java.util.*;

public class Shotgun extends Weapon {
    public Shotgun(int ammo) {
        super("src/Assets/pixelShotgunNoBg.png", 0, 500, 20, 300, 700, 1, 0.0001, 1, 1000, 100, ammo);
    }

    @Override
    public void fire(int x, int y, ArrayList<ArrayList<Grid>> allGrids, Rectangle map) {
        if (getAmmo() > 0 && getRefire()) {
            fire = true;
            for (int i = 0; i < (Math.random() * 10 + 95); i++) {
                ArrayList<Projectile> projectiles = getProjectiles();
                Random random = new Random();
                double angle = random.nextGaussian();
                ShotgunProjectile shell = new ShotgunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle() + (angle * 0.06) - 0.03, getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), allGrids, new Rectangle(x, y, 3, 3), map);
                projectiles.add((shell));
            }
            setAmmo(getAmmo() - 1);
            setRefire(false);
            refireTimer.start();
            fire = false;
        }
    }
}
