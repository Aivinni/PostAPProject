import java.awt.*;
import java.util.ArrayList;

public class SniperProjectile extends Projectile {
    public SniperProjectile(int maxDamage, int minRange, int optimalRange, int maxRange, double initialSpeed, double angle, double drag, int x, int y, ArrayList<ArrayList<Grid>> allGrids, Rectangle hitBox, Rectangle map) {
        super(maxDamage, minRange, optimalRange, maxRange, initialSpeed, angle, drag, x, y, allGrids, hitBox, map);
    }

    @Override
    public void detectCollision() {
        for (int i = 0; i < grids.size(); i++) {
            for (int j = 0; j < grids.get(i).getCollidables().size(); j++) {
                Sprite object = grids.get(i).getCollidables().get(j);
                if (object != null) {
                    if (object != this && Math.sqrt(Math.pow((getX() - (Math.cos(getAngle()) * getSpriteRect().width)) - object.getX(), 2) + Math.pow((getY() - (Math.sin(getAngle()) * getSpriteRect().height)) - object.getY(), 2)) < ((double) (getSpriteRect().width + getSpriteRect().height) / 2)) {
                        setCollision(true);
                        object.setCollision(true);
                        getCollidedObjects().add(object);
                    }
                }
            }
        }

    }
}
