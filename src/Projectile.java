import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Sprite{
    private double damage;
    private double maxDamage;
    private int minRange, optimalRange, maxRange;
    public double distance;
    private double speed;
    private double angle;
    private double drag;

    public Projectile(int maxDamage, int minRange, int optimalRange, int maxRange, double initialSpeed, double angle, double drag, int x, int y, ArrayList<ArrayList<Grid>> allGrids, Rectangle hitBox, Rectangle map) {
        super(x, y, hitBox.width, hitBox.height, allGrids, map);
        this.maxDamage = maxDamage;
        this.minRange = minRange;
        this.optimalRange = optimalRange;
        this.maxRange = maxRange;
        distance = 0;
        this.speed = initialSpeed;
        this.angle = angle;
        this.drag = drag;
    }

    public double getAngle() {
        return angle;
    }

    public double getDamage() {
        return damage;
    }

    public void calcDamage() {
        if (distance < optimalRange) {
            damage = maxDamage;
        } else if (distance > optimalRange && distance < maxRange) {
            damage = maxDamage;
        } else {
            damage = 0;
        }
    }

    public void move() {
        moveHorizontally(speed * Math.cos(angle));
        moveVertically(speed * Math.sin(angle));
        distance += speed;
        if (speed > 0) {
            speed -= drag * speed;
        }
    }
}
