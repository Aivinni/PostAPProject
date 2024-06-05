import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Player extends Sprite implements ActionListener {
    private final int moveUpKey, moveDownKey, moveLeftKey, moveRightKey, sprintKey;
    private final Timer timer;
    private final int energyMax;
    public int energy;
    public boolean resting;
    public Weapon weapon;

    public Player(int x, int y, int moveUpKey, int moveDownKey, int moveLeftKey, int moveRightKey, int sprintKey, Weapon weapon, ArrayList<Sprite> collidables, Rectangle map) {
        super(x, y, 10, 10, collidables, map);
        this.moveUpKey = moveUpKey;
        this.moveDownKey = moveDownKey;
        this.moveLeftKey = moveLeftKey;
        this.moveRightKey = moveRightKey;
        this.sprintKey = sprintKey;
        energyMax = 100000;
        energy = energyMax;
        resting = false;
        this.weapon = weapon;

        timer = new Timer(10000, this);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void move(boolean[] keys, double boost) {
        double move_amt = 0.15 * boost;
        if (keys[sprintKey] && !resting) {
            if (energy > 15) {
                move_amt *= 2;
                energy -= 10;
            } else {
                resting = true;
                timer.start();
            }
        } else {
            if (energy < energyMax) {
                energy++;
            }
        }

        boolean movingVertically = keys[moveUpKey] ^ keys[moveDownKey];
        boolean movingHorizontally = keys[moveLeftKey] ^ keys[moveRightKey];

        if (movingHorizontally && movingVertically) {
            if (keys[moveUpKey]) {
                moveVertically(-Math.sqrt(2) * move_amt / 2);
                if (keys[moveLeftKey]) {
                    moveHorizontally(-Math.sqrt(2) * move_amt / 2);
                }
                if (keys[moveRightKey]) {
                    moveHorizontally(Math.sqrt(2) * move_amt / 2);
                }
            }
            if (keys[moveDownKey]) {
                moveVertically(Math.sqrt(2) * move_amt / 2);
                if (keys[moveLeftKey]) {
                    moveHorizontally(-Math.sqrt(2) * move_amt / 2);
                }
                if (keys[moveRightKey]) {
                    moveHorizontally(Math.sqrt(2) * move_amt / 2);
                }
            }
        } else {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            resting = false;
            timer.stop();
        }
    }
}
