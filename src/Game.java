import java.awt.event.KeyEvent;

public class Game {
    private int width, height;
    public final Player player;

    public Game() {
        width = MainFrame.windowWidth;
        height = MainFrame.windowHeight;

        player = new Player(50, height - 200, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SHIFT);
    }
}
