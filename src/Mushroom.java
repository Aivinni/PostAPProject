import java.awt.*;
import java.util.ArrayList;

public class Mushroom extends Sprite implements Comparable<Mushroom> {
    private int HP;
    public Mushroom(double x, double y, ArrayList<Sprite> collidables, Rectangle map, int HP) {
        super(x, y, 2, 2, collidables, map);
        this.HP = HP;

        collidables.add(this);
    }

    public int getHP() {
        return HP;
    }

    @Override
    public int compareTo(Mushroom o) {
        return 0;
    }
}
