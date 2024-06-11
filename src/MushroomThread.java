public class MushroomThread implements Runnable {
    private final GraphicsPanel panel;
    public MushroomThread(GraphicsPanel panel) {
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

            if (delta >= 100000000) {
                panel.game.spread();
                delta = 0;
            }

            previousTime = currentTime;
        }
    }
}
