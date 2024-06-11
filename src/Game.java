import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    private final int width, height;
    private ArrayList<Sprite> collidables;
    public final Player player;
    private Rectangle map;
    public final ArrayList<Mushroom> mushrooms;

    public Game() {
        width = MainFrame.windowWidth;
        height = MainFrame.windowHeight;

        collidables = new ArrayList<>();
        map = new Rectangle(width, height);
        player = new Player(600, 500, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SHIFT, new Shotgun(2), collidables, map);
        mushrooms = new ArrayList<>();
        Mushroom initialSpore = new Mushroom(500, 500, collidables, map, 100);
        mushrooms.add(initialSpore);

        collidables.add(player);
    }

    public ArrayList<Sprite> getCollidables() {
        return collidables;
    }

    public Rectangle getMap() {
        return map;
    }

    public void spread() {
        for (int i = 0; i < mushrooms.size(); i++) {
            if (Math.random() < 1.0 / Math.sqrt(mushrooms.size())) {
                Mushroom mushroom = new Mushroom((Math.random() * 10 - 5) + mushrooms.get(i).getX(), (Math.random() * 10 - 5) + mushrooms.get(i).getY(), getCollidables(), getMap(), 25);
                if (!mushroom.collision()) {
                    mushrooms.add(mushroom);
                }
            }
        }
    }
}
