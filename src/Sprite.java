import java.awt.*;
import java.util.ArrayList;

public class Sprite {
    private double x, y;
    private Rectangle spriteRect;
    private ArrayList<ArrayList<Grid>> allGrids;
    private ArrayList<Grid> grids;
    private ArrayList<Sprite> collidables;
    private Rectangle map;
    private boolean collision;
    private boolean offMap;
    private ArrayList<Sprite> collidedObjects;


    public Sprite(double x, double y, int width, int height, ArrayList<Sprite> collidables, Rectangle map) {
        this.x = x;
        this.y = y;
        spriteRect = new Rectangle((int) x, (int) y, width, height);
        this.collidables = collidables;
        this.map = map;

        offMap = false;

        collidedObjects = new ArrayList<>();

        for (int i = 0; i < collidables.size(); i++) {
            Sprite object = collidables.get(i);
            if (object != null) {
                Rectangle rectangle = object.getSpriteRect();
                if (object != this && rectangle.intersects(spriteRect)){
                    collision = true;
                    object.collision = true;
                    collidedObjects.add(object);
                }
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public ArrayList<Sprite> getCollidables() {
        return collidables;
    }

    public Rectangle getMap() {
        return map;
    }

    public Rectangle getSpriteRect() {
        return spriteRect;
    }

    public void setSpriteRect(Rectangle spriteRect) {
        this.spriteRect = spriteRect;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean collision() {
        return collision;
    }

    public boolean isOffMap() {
        return offMap;
    }

    public ArrayList<Sprite> getCollidedObjects() {
        return collidedObjects;
    }

    public void moveHorizontally(double x) {
        this.x += x;
        spriteRect = new Rectangle((int) this.x - spriteRect.width / 2, (int) this.y - spriteRect.height / 2, spriteRect.width, spriteRect.height);
        detectCollision();
        if (!map.contains(spriteRect)) {
            this.x -= x;
            offMap = true;
        }
    }

    public void moveVertically(double y) {
        this.y += y;
        spriteRect = new Rectangle((int) this.x - spriteRect.width / 2, (int) this.y - spriteRect.height / 2, spriteRect.width, spriteRect.height);
        detectCollision();
        if (!map.contains(spriteRect)) {
            this.y -= y;
            offMap = true;
        }
    }

    public void detectCollision() {
        for (int i = 0; i < collidables.size(); i++) {
            Sprite object = collidables.get(i);
            if (object != null) {
                Rectangle rectangle = object.getSpriteRect();
                if (object != this && rectangle.intersects(spriteRect)){
                    collision = true;
                    object.collision = true;
                    collidedObjects.add(object);
                }
            }
        }
    }
}
