import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    private final int width, height;
    public final Player player;
    private Rectangle map;
    public final ArrayList<ArrayList<Grid>> grids;
    public final ArrayList<ArrayList<Mushroom>> mushrooms;

    public Game() {
        width = MainFrame.windowWidth;
        height = MainFrame.windowHeight;

        map = new Rectangle(width, height);

        grids = new ArrayList<>();
        for (int i = 0; i < MainFrame.windowWidth; i += 100) {
            ArrayList<Grid> newGridRow = new ArrayList<>();
            for (int j = 0; j < MainFrame.windowHeight; j += 100) {
                Grid grid = new Grid(i, j);
                newGridRow.add(grid);
            }
            grids.add(newGridRow);
        }

        player = new Player(600, 500, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SHIFT, new Shotgun(1), grids, map);
        mushrooms = new ArrayList<>();
        Mushroom initialSpore = new Mushroom((int) (Math.random() * 500), (int) (Math.random() * 500), grids, map, 100);
        ArrayList<Mushroom> initialCluster = new ArrayList<>();
        initialCluster.add(initialSpore);
        mushrooms.add(initialCluster);

    }

    public Rectangle getMap() {
        return map;
    }

    public void spread() {
        for (int i = 0; i < mushrooms.size(); i++) {
            for (int j = 0; j < mushrooms.get(i).size(); j++) {
                if (Math.random() < 1.0 / Math.sqrt(mushrooms.get(i).size()) && mushrooms.get(i).size() < 100) {
                    Mushroom mushroom = new Mushroom((int) (Math.random() * 10 - 5) + mushrooms.get(i).get(j).getX(), (int) (Math.random() * 10 - 5) + mushrooms.get(i).get(j).getY(), grids, getMap(), 25);
                    if (!mushroom.collision() && !mushroom.isOffMap()) {
                        mushrooms.get(i).add(mushroom);
                    }
                }
            }
            if ((Math.random() < 1.0 / (mushrooms.get(i).size() * mushrooms.size())) || (mushrooms.size() == 1 && mushrooms.get(0).size() < 20)) {
                Mushroom startingSpore = mushrooms.get(i).get((int) (Math.random() * mushrooms.get(i).size()));
                Mushroom newSpore = new Mushroom((int) (Math.random() * 400 - 200) + startingSpore.getX(), (int) (Math.random() * 400 - 200) + startingSpore.getY(), grids, getMap(), 100);
                if (Math.hypot(Math.abs(newSpore.getX() - startingSpore.getX()), Math.abs(newSpore.getY() - startingSpore.getY())) > 100 && !newSpore.collision() && !newSpore.isOffMap()) {
                    ArrayList<Mushroom> newCluster = new ArrayList<>();
                    newCluster.add(newSpore);
                    mushrooms.add(newCluster);
                }
            }
        }
    }
}
