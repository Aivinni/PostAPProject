import java.awt.*;

public class Sprite {
    private double x, y;
    private Rectangle spriteRect;

    public Sprite(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        spriteRect = new Rectangle((int) x, (int) y, width, height);
    }

    public void moveHorizontally(double x) {
        this.x += x;
        spriteRect = new Rectangle((int) this.x, (int) this.y, spriteRect.width, spriteRect.height);
    }
    public void moveVertically(double y) {
        this.y += y;
        spriteRect = new Rectangle((int) this.x, (int) this.y, spriteRect.width, spriteRect.height);
    }

    public Rectangle getSpriteRect() {
        return spriteRect;
    }
}
