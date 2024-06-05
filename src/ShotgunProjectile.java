import java.awt.*;
import java.util.ArrayList;

public class ShotgunProjectile extends Projectile {
    public ShotgunProjectile(int maxDamage, int minRange, int optimalRange, int maxRange, double initialSpeed, double angle, double drag, int x, int y, ArrayList<Sprite> collidables, Rectangle hitBox, Rectangle map) {
        super(maxDamage, minRange, optimalRange, maxRange, initialSpeed, angle, drag, x, y, collidables, hitBox, map);
    }
}
