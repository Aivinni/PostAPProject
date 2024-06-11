import java.awt.*;
import java.util.ArrayList;

public class Mushroom extends Sprite {
    private int HP;
    public Mushroom(double x, double y, ArrayList<Sprite> collidables, Rectangle map, int HP) {
        super(x, y, 2, 2, collidables, map);
        this.HP = HP;

        collidables.add(this);
    }

    public int getHP() {
        return HP;
    }
}
