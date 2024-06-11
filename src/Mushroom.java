import java.awt.*;
import java.util.ArrayList;

public class Mushroom extends Sprite {
    private int HP;
    public Mushroom(double x, double y, ArrayList<ArrayList<Grid>> allGrids, Rectangle map, int HP) {
        super(x, y, 2, 2, allGrids, map);
        this.HP = HP;

    }

    public int getHP() {
        return HP;
    }
}
