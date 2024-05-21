import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener, MouseMotionListener {
    private Game game;
    private Timer timer;
    private boolean[] pressedKeys;



    public GraphicsPanel() {
        setBackground(Color.BLACK);

        game = new Game();

        pressedKeys = new boolean[128];

        timer = new Timer(1, this);
        timer.start();

        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        Rectangle r = game.player.getSpriteRect();
        g.fillRect(r.x, r.y, r.width, r.height);

        g.drawString(String.valueOf(game.player.sprintAmt), 100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            game.player.move(pressedKeys, 1.0);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }
    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            Point mouseClickLocation = e.getPoint();
        } else if (e.getButton() == MouseEvent.BUTTON3){
            Point mouseClickLocation = e.getPoint();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
    @Override
    public void mouseDragged(MouseEvent e) {
        e.getLocationOnScreen();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        e.getLocationOnScreen();
    }
}
