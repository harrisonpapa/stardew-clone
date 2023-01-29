package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;
import main.GameStatus;
import player.Player;

public class MouseInputs implements MouseListener, MouseMotionListener {

    GameStatus status;

    GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        status = GameStatus.PLAYING;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (status) {

            case EDITOR -> {
                if (e.getX() >= gamePanel.editor.TOOLBAR_X+8 &&
                        e.getX() <= gamePanel.RESOLUTION_X-8 &&
                        e.getY() >= 8 &&
                        e.getY() <= 200) {

                }
            }

            case MENU -> {
            }

            case PLAYING -> {

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
