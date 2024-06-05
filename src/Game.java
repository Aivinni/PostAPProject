import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    private final int width, height;
    private ArrayList<Sprite> collidables;
    public final Player player;
    private Rectangle map;

    public Game() {
        width = MainFrame.windowWidth;
        height = MainFrame.windowHeight;

        collidables = new ArrayList<>();
        map = new Rectangle(width, height);
        player = new Player(600, 500, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SHIFT, new Shotgun(0), collidables, map);

        collidables.add(player);
    }

    public ArrayList<Sprite> getCollidables() {
        return collidables;
    }

    public Rectangle getMap() {
        return map;
    }
}
