package main;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private Thread gameThread;
    private final int FPS_LIMIT = 144;
    private final int UPS_LIMIT = 200;          // Updated per second

    private boolean running = false;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();               // Gives gamePanel input focus so inputs can be seen
        startGameLoop();

        running = true;
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        gamePanel.updateGame();
    }

    // main.Game Loop
    @Override
    public void run() {

        // UPS (Updates Per Second) Limit
        double timePerUpdate = 1000000000.0 / UPS_LIMIT;
        int updatesCount = 0;
        long lastUpdate = System.nanoTime();

        // FPS Limiter
        double timePerFrame = 1000000000.0 / FPS_LIMIT;
        long lastFrame = System.nanoTime();

        // FPS Counter
        int frameCount = 0;
        long framesStartTime = System.currentTimeMillis();

        double updateLag = 0;           // if > 1, there has been lag and the next update will happen sooner
        double framesLag = 0;           // if > 1, there has been lag and the next frame update will happen sooner

        while (running) {
            long currentTime = System.nanoTime();

            // Update the game
            updateLag += (currentTime-lastUpdate)/timePerUpdate;
            lastUpdate = currentTime;

            if (updateLag >= 1) {
                update();

                updatesCount++;
                updateLag--;
                lastUpdate = System.nanoTime();
            }

            // Render the game
            framesLag += (currentTime-lastFrame)/timePerFrame;
            lastFrame = currentTime;

            if (framesLag >= 1) {
                gamePanel.repaint();

                frameCount++;
                framesLag--;
            }

            // Count the FPS
            if (System.currentTimeMillis() - framesStartTime >= 1000) {
                System.out.println("FPS: " + frameCount + " | UPS: " + updatesCount);
                frameCount = 0;
                updatesCount = 0;
                framesStartTime = System.currentTimeMillis();
            }
        }
    }
}
