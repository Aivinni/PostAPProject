import java.awt.*;
import java.util.*;

public class Shotgun extends Weapon {
    public Shotgun(double angle) {
        super("src/Assets/pixelShotgunNoBg.png", angle, 100, 20, 300, 700, 1, 0.000001, 2, 5000);
    }

    @Override
    public void fire(int x, int y, ArrayList<Sprite> collidables, Rectangle map) {
        if (getAmmo() > 0 && getRefire()) {
            fire = true;
            for (int i = 0; i < (Math.random() * 10 + 25); i++) {
                ArrayList<Projectile> projectiles = getProjectiles();

                ShotgunProjectile shell = new ShotgunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed(), getAngle() + (Math.random() * 0.1 - 0.05), getDrag(), x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), collidables, new Rectangle(x, y, 1, 1), map);
                projectiles.add((shell));
            }
            setAmmo(getAmmo() - 1);
            setRefire(false);
            refireTimer.start();
            fire = false;
        }
    }
}
