import java.awt.*;
import java.util.*;

public class Shotgun extends Weapon {
    public Shotgun(int ammo) {
        super("src/Assets/pixelShotgunNoBg.png", 0, 100, 20, 300, 700, 1, 0.0001, 2, 1500, 500, ammo);
    }

    @Override
    public void fire(int x, int y, ArrayList<Sprite> collidables, Rectangle map) {
        if (getAmmo() > 0 && getRefire()) {
            fire = true;
            for (int i = 0; i < (Math.random() * 10 + 45); i++) {
                ArrayList<Projectile> projectiles = getProjectiles();
                if (Math.random() < 0.68) {
                    ShotgunProjectile shell = new ShotgunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle() + (Math.random() * 0.1) - 0.05, getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), collidables, new Rectangle(x, y, 1, 1), map);
                    projectiles.add((shell));
                } else if (Math.random() < 0.92){
                    ShotgunProjectile shell = new ShotgunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle() + (Math.random() * 0.4) - 0.2, getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), collidables, new Rectangle(x, y, 1, 1), map);
                    projectiles.add((shell));
                } else {
                    ShotgunProjectile shell = new ShotgunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle() + (Math.random() * 0.6) - 0.3, getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), collidables, new Rectangle(x, y, 1, 1), map);
                    projectiles.add((shell));
                }
            }
            setAmmo(getAmmo() - 1);
            setRefire(false);
            refireTimer.start();
            fire = false;
        }
    }
}
