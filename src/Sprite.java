import java.awt.*;
import java.util.ArrayList;

public class Sprite {
    private double x, y;
    private Rectangle spriteRect;
    private ArrayList<Sprite> collidables;
    private Rectangle map;
    private boolean collision;
    private Sprite collidedObject;


    public Sprite(double x, double y, int width, int height, ArrayList<Sprite> collidables, Rectangle map) {
        this.x = x;
        this.y = y;
        spriteRect = new Rectangle((int) x - width / 2, (int) y - height / 2, width, height);
        this.collidables = collidables;
        this.map = map;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getSpriteRect() {
        return spriteRect;
    }

    public void setSpriteRect(Rectangle spriteRect) {
        this.spriteRect = spriteRect;
    }

    public boolean collision() {
        return collision;
    }

    public Sprite getCollidedObject() {
        return collidedObject;
    }

    public void moveHorizontally(double x) {
        this.x += x;
        spriteRect = new Rectangle((int) this.x - spriteRect.width / 2, (int) this.y - spriteRect.height / 2, spriteRect.width, spriteRect.height);
        for (Sprite object : collidables) {
            Rectangle rectangle = object.getSpriteRect();
            if (object != this && rectangle.intersects(spriteRect)){
                collision = true;
                collidedObject = object;
                collidedObject.collision = true;
                this.x -= x;
            }
        }
        if (!map.contains(spriteRect)) {
            this.x -= x;
            collision = true;
        }
    }

    public void moveVertically(double y) {
        this.y += y;
        spriteRect = new Rectangle((int) this.x - spriteRect.width / 2, (int) this.y - spriteRect.height / 2, spriteRect.width, spriteRect.height);
        for (Sprite object : collidables) {
            Rectangle rectangle = object.getSpriteRect();
            if (object != this && rectangle.intersects(spriteRect)){
                collision = true;
                collidedObject = object;
                collidedObject.collision = true;
                this.y -= y;
            }
        }
        if (!map.contains(spriteRect)) {
            this.y -= y;
            collision = true;
        }
    }
}
