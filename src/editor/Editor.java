package editor;

import java.awt.*;
import environment.Grass;
import environment.GrassType;

public class Editor {

    private int RESOLUTION_X;
    private int RESOLUTION_Y;

    public int TOOLBAR_X;

    private Grass grass;

    public Editor(int x, int y) {
        RESOLUTION_X = x;
        RESOLUTION_Y = y;

        TOOLBAR_X = RESOLUTION_X - 208;

        grass = new Grass();
    }

    private void renderToolBar(Graphics g) {
        Color backgroundColour = new Color(255, 255, 255, 64);
        g.setColor(backgroundColour);
        g.fillRect(TOOLBAR_X, 0, 208, RESOLUTION_Y);
    }

    public void render(Graphics g) {
        renderToolBar(g);

        int index = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grass.render(g, GrassType.values()[index], TOOLBAR_X +16 + 48*x, 16+48*y);
                index++;
            }
        }

        for (int i = 0; i < 3; i++) {
            grass.render(g, GrassType.values()[index], TOOLBAR_X + 16 + 48*3, 16+48*i);
            index++;
        }

        for (int i = 0; i < 4; i++) {
            grass.render(g, GrassType.values()[index], TOOLBAR_X + 16 + 48*i, 16+48*3);
            index++;
        }
    }
}
