import java.awt.*;
import java.util.ArrayList;

public class Sprite {
    private double x, y;
    private Rectangle spriteRect;
    public final ArrayList<ArrayList<Grid>> allGrids;
    public final ArrayList<Grid> grids;
    private final Rectangle map;
    private boolean collision;
    private boolean offMap;
    private ArrayList<Sprite> collidedObjects;


    public Sprite(double x, double y, int width, int height, ArrayList<ArrayList<Grid>> allGrids, Rectangle map) {
        this.x = x;
        this.y = y;
        spriteRect = new Rectangle((int) x, (int) y, width, height);
        this.allGrids = allGrids;
        grids = new ArrayList<>();
        this.map = map;

        offMap = false;

        collidedObjects = new ArrayList<>();

        for (int i = 0; i < allGrids.size(); i++) {
            for (int j = 0; j < allGrids.get(i).size(); j++) {
                if (spriteRect.intersects(allGrids.get(i).get(j))) {
                    grids.add(allGrids.get(i).get(j));
                }
            }
        }

        for (int i = 0; i < grids.size(); i++) {
            grids.get(i).getCollidables().add(this);
        }

        detectCollision();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getMap() {
        return map;
    }

    public Rectangle getSpriteRect() {
        return spriteRect;
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
        findGridCollision();
        detectCollision();
        if (!map.contains(spriteRect)) {
            this.x -= x;
            offMap = true;
        }
    }

    public void moveVertically(double y) {
        this.y += y;
        spriteRect = new Rectangle((int) this.x - spriteRect.width / 2, (int) this.y - spriteRect.height / 2, spriteRect.width, spriteRect.height);
        findGridCollision();
        detectCollision();
        if (!map.contains(spriteRect)) {
            this.y -= y;
            offMap = true;
        }
    }

    public void detectCollision() {
        for (int i = 0; i < grids.size(); i++) {
            Grid grid = grids.get(i);
            if (grid != null) {
                for (int j = 0; j < grid.getCollidables().size(); j++) {
                    Sprite object = grid.getCollidables().get(j);
                    if (object != null) {
                        Rectangle rectangle = object.getSpriteRect();
                        if (rectangle != null && object != this && rectangle.intersects(spriteRect)){
                            collision = true;
                            object.collision = true;
                            collidedObjects.add(object);
                        }
                    }
                }
            }
        }
    }

    public void findGridCollision() {
        grids.clear();
        for (int i = (int) this.x / 100; i < Math.min(allGrids.size(), ((int) this.x / 100) + 2); i++) {
            for (int j = (int) this.y / 100; j < Math.min(allGrids.get(i).size(), ((int) this.y / 100) + 2); j++) {
                if (spriteRect.intersects(allGrids.get(i).get(j))) {
                    grids.add(allGrids.get(i).get(j));
                }
            }
        }
    }
}
