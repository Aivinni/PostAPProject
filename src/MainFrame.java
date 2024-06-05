import javax.swing.*;

public class MainFrame implements Runnable {
    public static final int windowWidth = 1470, windowHeight = 864;
    private final GraphicsPanel panel;

    public MainFrame() {
        JFrame frame = new JFrame("Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null); // auto-centers frame in screen

        // create and add panel
        panel = new GraphicsPanel();
        frame.add(panel);

        // display the frame
        frame.setVisible(true);

        // start thread, required for animation
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long currentTime = System.nanoTime();
        long previousTime = currentTime;
        double delta = 0.0;
        double delta2 = 0.0;
        double delta3 = 0.0;
        double FPS = 90;
        double drawInterval = 1000000000.0 / FPS;
        while (true) {
            currentTime = System.nanoTime();

            double timePassed = (double) (currentTime - previousTime);
            // the time between now and the last time this looped
            delta += timePassed / drawInterval;
            delta2 += timePassed;
            delta3 += timePassed;

            if (delta >= 1) {
                panel.repaint();  // we don't ever call "paintComponent" directly, but call this to refresh the panel
                delta = 0;
            }
            if (delta2 >= 1000000) {
                panel.playerMove();
                delta2 = 0;
            }
            if (delta3 >= 100000) {
                panel.shellMove();
                delta3 = 0;
            }

            previousTime = currentTime;
        }
    }
}
