package environment;

import main.Functions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grass {

    private double IMAGE_SCALE = 2.0;

    private BufferedImage img;

    private BufferedImage[] scaledImage = new BufferedImage[GrassType.values().length];

    public Grass () {
        img = Functions.loadImage("/textures/tiles/ground/grass.png");

        int index = 0;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                BufferedImage temp = img.getSubimage(x*16, y*16, 16, 16);
                scaledImage[index] = Functions.scaleImage(temp, IMAGE_SCALE);

                index++;
            }
        }

        for (int i = 0; i < 3; i++) {
            BufferedImage temp = img.getSubimage(3*16, i*16, 16, 16);
            scaledImage[index] = Functions.scaleImage(temp, IMAGE_SCALE);

            index++;
        }

        for (int i = 0; i < 4; i++) {
            BufferedImage temp = img.getSubimage(i*16, 3*16, 16, 16);
            scaledImage[index] = Functions.scaleImage(temp, IMAGE_SCALE);

            index++;
        }
    }

    public void render(Graphics g, GrassType t, int x, int y) {
        g.drawImage(scaledImage[t.getLabel()], x, y, null);
    }
}
