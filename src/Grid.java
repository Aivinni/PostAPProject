import java.awt.*;
import java.util.ArrayList;

public class Grid extends Rectangle {
    private ArrayList<Sprite> collidables;
    public Grid(int x, int y) {
        super(x, y, 100, 100);
        collidables = new ArrayList<>();
    }

    public ArrayList<Sprite> getCollidables() {
        return collidables;
    }
}
