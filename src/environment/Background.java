package environment;

import main.Functions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Background {

    private BufferedImage img;
    private BufferedImage tiledImg;
    private double[] waterVelocity;
    private double[] waterPosition;

    private final double WATER_SPEED_MULTIPLIER = 0.4;
    private int RESOLUTION_X;
    private int RESOLUTION_Y;

    private final double IMAGE_SCALE = 2.0;

    public Background(int x, int y) {
        RESOLUTION_X = x;
        RESOLUTION_Y = y;

        img = Functions.loadImage("/textures/tiles/water.png");

        tileBackground();
        randomiseWaterMovement();
    }

    private void randomiseWaterMovement() {
        waterVelocity = new double[RESOLUTION_Y/tiledImg.getHeight()];
        waterPosition = new double[RESOLUTION_Y/tiledImg.getHeight()];

        Random rand = new Random();
        for (int i = 0; i < waterVelocity.length; i++) {
            waterVelocity[i] = rand.nextDouble();
            waterPosition[i] = 0;
        }
    }

    private void tileBackground() {
        tiledImg = new BufferedImage(RESOLUTION_X, (int) (img.getHeight()*IMAGE_SCALE), BufferedImage.TYPE_INT_RGB);
        Graphics g = tiledImg.getGraphics();

        Image scaled = Functions.scaleImage(img, IMAGE_SCALE);

        for (int x = 0; x < tiledImg.getWidth(); x += scaled.getWidth(null)) {
            g.drawImage(scaled, x, 0, null);
        }
        g.dispose();
    }

    public void update() {
        for (int i = 0; i < waterVelocity.length; i++) {
            waterPosition[i] += waterVelocity[i]*WATER_SPEED_MULTIPLIER;

            if (waterPosition[i] >= RESOLUTION_X) {
                waterPosition[i] = 0;
            } else if (waterPosition[i] <= -RESOLUTION_X) {
                waterPosition[i] = 0;
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < waterVelocity.length; i++) {
            g.drawImage(tiledImg, (int) waterPosition[i], i*tiledImg.getHeight(), null);
            g.drawImage(tiledImg, (int) waterPosition[i] + RESOLUTION_X, i*tiledImg.getHeight(), null);
            g.drawImage(tiledImg, (int) waterPosition[i] - RESOLUTION_X, i*tiledImg.getHeight(), null);
        }
    }
}
