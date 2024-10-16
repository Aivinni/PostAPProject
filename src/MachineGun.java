import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MachineGun extends Weapon {
    public MachineGun(int ammo) {
        super("src/Assets/pixelMachineGunNoBg.png", 0, 2000, 20, 600, 1000, 1, 0.0001, 1000, 5000, 50, ammo);
    }

    @Override
    public void fire(int x, int y, ArrayList<ArrayList<Grid>> allGrids, Rectangle map) {
        if (getAmmo() > 0 && getRefire()) {
            fire = true;

            Random random = new Random();
            double angle = random.nextGaussian();
            ArrayList<Projectile> projectiles = getProjectiles();
            MachineGunProjectile shell = new MachineGunProjectile(getMaxDamage(), getMinRange(), getOptimalRange(), getMaxRange(), getInitialSpeed() + (Math.random() / 20) - .025, getAngle() + (angle * 0.02) - 0.01, getDrag() + (Math.random() / 10000) - 0.00005, x + (int) (Math.cos(getAngle()) * getMinRange()), y + (int) (Math.sin(getAngle()) * getMinRange()), allGrids, new Rectangle(x, y, 3, 3), map);
            projectiles.add((shell));

            setAmmo(getAmmo() - 1);
            setRefire(false);
            refireTimer.start();
            fire = false;
        }
    }
}
