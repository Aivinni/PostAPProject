import java.awt.*;
import java.util.ArrayList;

public class Mushroom extends Sprite {
    public Mushroom(double x, double y, int width, int height, ArrayList<Sprite> collidables, Rectangle map) {
        super(x, y, width, height, collidables, map);

        collidables.add(this);
    }
}
