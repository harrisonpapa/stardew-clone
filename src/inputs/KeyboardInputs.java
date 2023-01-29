package inputs;

import main.GamePanel;
import main.GameStatus;
import player.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    GameStatus status;

    GamePanel gamePanel;
    Player player;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        status = GameStatus.PLAYING;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (status) {

            case EDITOR -> {
            }

            case MENU -> {
            }

            case PLAYING -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> gamePanel.player.WALK_UP(1);
                    case KeyEvent.VK_A -> gamePanel.player.WALK_LEFT(1);
                    case KeyEvent.VK_S -> gamePanel.player.WALK_DOWN(1);
                    case KeyEvent.VK_D -> gamePanel.player.WALK_RIGHT(1);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (status) {

            case EDITOR -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SEMICOLON -> {
                        gamePanel.switchToPlaying();
                    }
                }
            }

            case MENU -> {
            }

            case PLAYING -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> gamePanel.player.WALK_UP(0);
                    case KeyEvent.VK_A -> gamePanel.player.WALK_LEFT(0);
                    case KeyEvent.VK_S -> gamePanel.player.WALK_DOWN(0);
                    case KeyEvent.VK_D -> gamePanel.player.WALK_RIGHT(0);

                    case KeyEvent.VK_SEMICOLON -> {
                        gamePanel.switchToEditor();
                    }
                }
            }
        }
    }

    public void switchToEditor() {
        status = GameStatus.EDITOR;
    }

    public void switchToPlaying() {
        status = GameStatus.PLAYING;
    }
}
