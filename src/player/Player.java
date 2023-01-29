package player;

import main.Functions;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.sqrt;

public class Player {

    private double POSITION_X, POSITION_Y = 0;
    private int DIRECTION_X, DIRECTION_Y = 0;
    private double ACCELERATION_X, ACCELERATION_Y = 0;
    private double IMAGE_SCALE = 2;
    private Status status = Status.IDLE;

    private BufferedImage img;

    private final int IDLE_ANIMATION_SPEED = 80;           // Lower is faster
    private BufferedImage[] idleAnimation;
    private int currentIdleAnimation;
    private int idleAnimationTick;

    private final int WALKING_ANIMATION_SPEED = 50;
    private BufferedImage[] walkingAnimation;
    private int currentWalkingAnimation;
    private int walkingAnimationTick;

    public Player(int x, int y, BufferedImage img) {
        this.POSITION_X = x;
        this.POSITION_Y = y;

        this.img = img;
        loadAnimation();
    }

    private void loadAnimation() {
        idleAnimation = new BufferedImage[8];
        walkingAnimation = new BufferedImage[8];

        for (int i = 0; i < idleAnimation.length; i++) {
            int x = 16 + (i/4)*48;
            int y = 16-48*i*(i/4-1) + 48*(i-4)*(i/4);

            BufferedImage idle = img.getSubimage(x, y, 16, 16);
            BufferedImage walking = img.getSubimage(x+96, y, 16, 16);

            idleAnimation[i] = Functions.scaleImage(idle, IMAGE_SCALE);
            walkingAnimation[i] = Functions.scaleImage(walking, IMAGE_SCALE);
        }
    }

    private void updateIdleAnimation() {
        idleAnimationTick++;

        // Check if rotated
        if (DIRECTION_X == -1 && currentIdleAnimation != 6) { currentIdleAnimation = 2; }
        else if (DIRECTION_X == 1 && currentIdleAnimation != 7) { currentIdleAnimation = 3; }
        else if (DIRECTION_Y == -1 && currentIdleAnimation != 5) { currentIdleAnimation = 1; }
        else if (DIRECTION_Y == 1 && currentIdleAnimation != 4) { currentIdleAnimation = 0; }

        if (idleAnimationTick < IDLE_ANIMATION_SPEED) { return; }
        idleAnimationTick = 0;

        if (currentIdleAnimation+4 >= idleAnimation.length) {
            currentIdleAnimation -= 4;
            return;
        }

        currentIdleAnimation += 4;
    }

    private void updateWalkingAnimation() {
        walkingAnimationTick++;

        if (DIRECTION_X == -1 && currentWalkingAnimation != 6) { currentWalkingAnimation = 2; }
        else if (DIRECTION_X == 1 && currentWalkingAnimation != 7) { currentWalkingAnimation = 3; }
        else if (DIRECTION_Y == -1 && currentWalkingAnimation != 5) { currentWalkingAnimation = 1; }
        else if (DIRECTION_Y == 1 && currentWalkingAnimation != 4) { currentWalkingAnimation = 0; }

        if (walkingAnimationTick < WALKING_ANIMATION_SPEED) { return; }
        walkingAnimationTick = 0;

        if (currentWalkingAnimation+4 >= walkingAnimation.length) {
            currentWalkingAnimation -= 4;
            return;
        }

        currentWalkingAnimation += 4;
    }

    public void WALK_UP(int pressed) {
        if (pressed == 0) {
            ACCELERATION_Y = 0;
            return;
        }

        status = Status.WALKING;
        ACCELERATION_Y = -1;
    }

    public void WALK_DOWN(int pressed) {
        if (pressed == 0) {
            ACCELERATION_Y = 0;
            return;
        }

        status = Status.WALKING;
        ACCELERATION_Y = 1;
    }

    public void WALK_LEFT(int pressed) {
        if (pressed == 0) {
            ACCELERATION_X = 0;
            return;
        }

        status = Status.WALKING;
        ACCELERATION_X = -1;
    }

    public void WALK_RIGHT(int pressed) {
        if (pressed == 0) {
            ACCELERATION_X = 0;
            return;
        }

        status = Status.WALKING;
        ACCELERATION_X = 1;
    }

    private void updatePosition() {
        if (ACCELERATION_X == 0 && ACCELERATION_Y == 0) { status = Status.IDLE; walkingAnimationTick = 0; }

        POSITION_X += ACCELERATION_X;
        POSITION_Y += ACCELERATION_Y;
    }

    private void fixWalking() {
        // Set status to walking
        if (ACCELERATION_X != 0 || ACCELERATION_Y != 0) {
            idleAnimationTick = 0;
        }

        // Update the direction
        switch ((int) ACCELERATION_X) {
            case 1 -> DIRECTION_X = 1;
            case -1 -> DIRECTION_X = -1;
            case 0 -> {
                if (ACCELERATION_Y != 0) { DIRECTION_X = 0; }
            }
        }
        switch ((int) ACCELERATION_Y) {
            case 1 -> DIRECTION_Y = 1;
            case -1 -> DIRECTION_Y = -1;
            case 0 -> {
                if (ACCELERATION_X != 0) { DIRECTION_Y = 0; }
            }
        }

        // Update Speed
        if (ACCELERATION_X != 0 && ACCELERATION_Y != 0) {
            if (ACCELERATION_X == 1.0 || ACCELERATION_X == -1.0) {
                ACCELERATION_X *= 1/sqrt(2);
            }
            if (ACCELERATION_Y == 1.0 || ACCELERATION_Y == -1.0) {
                ACCELERATION_Y *= 1/sqrt(2);
            }

            return;
        }

        if (ACCELERATION_X != 0 && ACCELERATION_X != -1 && ACCELERATION_X != 1) {
            ACCELERATION_X *= sqrt(2);
        }
        if (ACCELERATION_Y != 0 && ACCELERATION_Y != -1 && ACCELERATION_Y != 1) {
            ACCELERATION_Y *= sqrt(2);
        }
    }

    public void update() {
        switch (status) {
            case IDLE -> updateIdleAnimation();
            case WALKING -> {
                fixWalking();
                updateWalkingAnimation();
                updatePosition();
            }
        }
    }

    public void render(Graphics g) {
        switch (status) {
            case IDLE -> {
                g.drawImage(idleAnimation[currentIdleAnimation], (int) POSITION_X, (int) POSITION_Y, null);
            }

            case WALKING -> {
                g.drawImage(walkingAnimation[currentWalkingAnimation], (int) POSITION_X, (int) POSITION_Y, null);
            }
        }


    }
}
