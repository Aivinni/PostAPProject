import java.awt.*;
import java.util.ArrayList;

public class MachineGun extends Weapon {
    public MachineGun(int ammo) {
        super("src/Assets/pixelMachineGunNoBg.png", 0, 50, 20, 700, 1200, 1, 0.0001, 1000, 5000, 10, ammo);
    }

    @Override
    public void fire(int x, int y, ArrayList<Sprite> collidables, Rectangle map) {
        if (getAmmo() > 0 && getRefire()) {
            fire = true;

            ArrayList<Projectile> projectiles = getProjectiles();
            MachineGunProjectile shell = new MachineGunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle() + (Math.random() * 0.01) - 0.005, getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), collidables, new Rectangle(x, y, 1, 1), map);
            projectiles.add((shell));

            setAmmo(getAmmo() - 1);
            setRefire(false);
            refireTimer.start();
            fire = false;
        }
    }
}
