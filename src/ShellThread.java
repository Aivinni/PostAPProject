public class ShellThread implements Runnable {
    private final GraphicsPanel panel;
    public ShellThread(GraphicsPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        long currentTime = System.nanoTime();
        long previousTime = currentTime;
        double delta = 0.0;
        while (true) {
            currentTime = System.nanoTime();

            double timePassed = (double) (currentTime - previousTime);
            // the time between now and the last time this looped
            delta += timePassed;

            if (delta >= 500000) {
                panel.shellMove();
                delta = 0;
                if (panel.isLeftMouseHeld()) {
                    Game game = panel.game;
                    Player player = panel.game.player;
                    if (player.weapon.getRefire() && player.weapon.getAmmo() > 0) {
                        if (player.weapon instanceof Shotgun) {
                            player.moveHorizontally(-3 * Math.cos(player.getWeapon().angle));
                            player.moveVertically(-3 * Math.sin(player.getWeapon().angle));
                        } else if (player.weapon instanceof MachineGun) {
                            player.moveHorizontally(-0.2 * Math.cos(player.getWeapon().angle));
                            player.moveVertically(-0.2 * Math.sin(player.getWeapon().angle));
                        } else if (player.weapon instanceof  SniperRifle) {
                            player.moveHorizontally(-4 * Math.cos(player.getWeapon().angle));
                            player.moveVertically(-4 * Math.sin(player.getWeapon().angle));
                        }

                    }
                    player.weapon.fire((int) player.getX(), (int) player.getY(), game.getCollidables(), game.getMap());
                }
            }

            previousTime = currentTime;
        }
    }
}
