package main;

import editor.Editor;
import environment.Background;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import player.Player;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel {

    public final int RESOLUTION_X = 1280;
    public final int RESOLUTION_Y = 800;
    private GameStatus status = GameStatus.PLAYING;

    private KeyboardInputs keyboardInputs;
    private MouseInputs mouseInputs;

    public Player player;
    private Background background;
    public Editor editor;

    public GamePanel() {

        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        editor = new Editor(RESOLUTION_X, RESOLUTION_Y);

        setPanelSize();
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        background = new Background(RESOLUTION_X, RESOLUTION_Y);
        setupPlayer();
    }

    private void setupPlayer() {
        BufferedImage img = Functions.loadImage("/textures/characters/sprite.png");
        player = new Player(20, 20, img);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(RESOLUTION_X, RESOLUTION_Y);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void switchToEditor() {
        status = GameStatus.EDITOR;
        keyboardInputs.switchToEditor();
        mouseInputs.switchToEditor();
    }

    public void switchToPlaying() {
        status = GameStatus.PLAYING;
        keyboardInputs.switchToPlaying();
        mouseInputs.switchToPlaying();
    }

    public void updateGame() {
        switch (status) {
            case PLAYING -> {
                background.update();
                player.update();
            }

            case EDITOR -> {
                background.update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (status) {
            case PLAYING -> {
                background.render(g);
                player.render(g);
            }

            case EDITOR -> {
                background.render(g);
                editor.render(g);
            }
        }
    }
}
