import java.awt.*;
import java.util.ArrayList;

public class SniperRifle extends Weapon {
    public SniperRifle(int ammo) {
        super("src/Assets/pixelSniperNoBg.png", 0, 50000, 30, 2000, 5000, 2, 0.000001, 10, 3000, 1000, ammo);
    }

    public void fire(int x, int y, ArrayList<Sprite> collidables, Rectangle map) {
        if (getAmmo() > 0 && getRefire()) {
            fire = true;

            ArrayList<Projectile> projectiles = getProjectiles();
            SniperProjectile shell = new SniperProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle(), getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), collidables, new Rectangle(x, y, 10, 10), map);
            projectiles.add((shell));

            setAmmo(getAmmo() - 1);
            setRefire(false);
            refireTimer.start();
            fire = false;
        }
    }
}
