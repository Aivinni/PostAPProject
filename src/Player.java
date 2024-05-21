import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends Sprite implements ActionListener {
    private final int moveUpKey, moveDownKey, moveLeftKey, moveRightKey, sprintKey;
    private Timer timer;
    private int sprintMax;
    public int sprintAmt;
    public boolean resting;

    public Player(int x, int y, int moveUpKey, int moveDownKey, int moveLeftKey, int moveRightKey, int sprintKey) {
        super(x, y, 10, 10);
        this.moveUpKey = moveUpKey;
        this.moveDownKey = moveDownKey;
        this.moveLeftKey = moveLeftKey;
        this.moveRightKey = moveRightKey;
        this.sprintKey = sprintKey;
        sprintMax = 100000;
        sprintAmt = sprintMax;
        resting = false;

        timer = new Timer(10000, this);
    }

    public void move(boolean[] keys, double boost) {
        double move_amt = 0.2 * boost;
        if (keys[sprintKey] && !resting) {
            if (sprintAmt > 15) {
                move_amt *= 2;
                sprintAmt -= 10;
            } else {
                resting = true;
                timer.start();
            }
        } else {
            if (sprintAmt < sprintMax) {
                sprintAmt++;
            }
        }

        if (keys[moveUpKey]) {
            moveVertically(-move_amt);
        }
        if (keys[moveDownKey]) {
            moveVertically(move_amt);
        }
        if (keys[moveLeftKey]) {
            moveHorizontally(-move_amt);
        }
        if (keys[moveRightKey]) {
            moveHorizontally(move_amt);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            resting = false;
            timer.stop();
        }
    }
}
