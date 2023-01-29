package main;

import player.Player;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Functions {

    public static BufferedImage scaleImage(BufferedImage before, double scale) {
        int w = (int) (before.getWidth()*scale);
        int h = (int) (before.getHeight()*scale);
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        after = scaleOp.filter(before, after);

        return after;
    }

    public static BufferedImage loadImage(String str) {
        InputStream is = Functions.class.getResourceAsStream(str);
        if (is == null) {
            System.out.println("image could not be loaded!");

            return null;
        }

        try {
            return ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                is.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
