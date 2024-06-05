import java.awt.*;
import java.util.ArrayList;

public class Projectile extends Sprite{
    private double damage;
    private double maxDamage;
    private int minRange, optimalRange, maxRange;
    private double distance;
    private double speed;
    private double angle;
    private double drag;

    public Projectile(int maxDamage, int minRange, int optimalRange, int maxRange, double initialSpeed, double angle, double drag, int x, int y, ArrayList<Sprite> collidables, Rectangle hitBox, Rectangle map) {
        super(x, y, hitBox.width, hitBox.height, collidables, map);
        this.maxDamage = maxDamage;
        this.minRange = minRange;
        this.optimalRange = optimalRange;
        this.maxRange = maxRange;
        distance = 0;
        this.speed = initialSpeed;
        this.angle = angle;
        this.drag = drag;
    }

    public double getDamage() {
        return damage;
    }

    public void calcDamage() {
        if (distance > minRange && distance < optimalRange) {
            damage = maxDamage;
        } else if (distance > optimalRange && distance < maxRange) {
            damage = Math.min(maxDamage, (maxDamage - (distance - optimalRange)) * speed);
        } else {
            damage = 0;
        }
    }

    public void move() {
        moveHorizontally(speed * Math.cos(angle));
        moveVertically(speed * Math.sin(angle));
        distance += speed;
        if (speed > 0) {
            speed -= drag;
        }
    }
}
