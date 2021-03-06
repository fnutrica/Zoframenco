/*
TIRES NOISE CREDITS
Title: Tires Squealing
About: Sound of a truck or large cars tires squealing loud and clear. sound recorded in stereo. great city, car, or similar sound effect.
Uploaded: 11.14.09 | License: Attribution 3.0 | Recorded by Mike Koenig | File Size: 163 KB


CRASH SOUND:

Title: Strong Punch
About: Nice strong punch or punching sound effect. nice for kung fu boxing or other fight scene or game.
Uploaded: 03.04.11 | License: Attribution 3.0 | Recorded by Mike Koenig | File Size: 192 K

 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Car {
    private Sprite sprite = new Sprite(new Texture("images/Car.png"));
    private boolean full;
    public static final float[] InitialPosition = {MyGdxGame.V_WIDTH / 2, MyGdxGame.V_HEIGHT / 2.3f};
    private float X_pos = InitialPosition[0];
    private float Y_pos = InitialPosition[1];
    private float[] velocity = new float[2];
    private Direction currentDirection;
    private Passenger passenger;
    public static Direction UP = new Direction(1, "UP", 0, 1, 360);
    public static Direction RIGHT = new Direction(2, "RIGHT", 1, 0, -270);
    public static Direction DOWN = new Direction(3, "DOWN", 0, -1, -180);
    public static Direction LEFT = new Direction(4, "LEFT", -1, 0, -90);
    public float currentAngle = 0;
    private int collidedXtimes = 0;
    private Direction oldDirection = currentDirection;


    public Car() {

        sprite.setSize(48, 48);
        sprite.setPosition(X_pos, Y_pos);
        sprite.setOrigin(24, 24);
        currentDirection = UP;

    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }


    private void accelerate(float acceleration) {

        if (this.velocity[0] == 0) {
            this.velocity[0] = (float) (currentDirection.x * 0.3);
        }
        if (this.velocity[1] == 0) {
            this.velocity[1] = (float) (currentDirection.y * 0.3);
        }


        if ((velocity[0] > -3) && ((velocity[0] < 3) && (velocity[1] < 3) && (velocity[1] > -3))) {
            this.velocity[0] += (Gdx.graphics.getDeltaTime() * acceleration) * currentDirection.x * 3;
            this.velocity[1] += (Gdx.graphics.getDeltaTime() * acceleration) * currentDirection.y * 3;
        }
        driveForward();
    }

    private void driveForward() {
        float oldX = X_pos;
        float oldY = Y_pos;

        if (!PlayScreen.checkCarCollisions()) {
            sprite.setPosition(X_pos, Y_pos);
            collidedXtimes = 0;
        } else if (collidedXtimes <= 2 && Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) && !(PlayScreen.isCellProperty(X_pos, Y_pos, "parking")) && (PlayScreen.isCellProperty(X_pos, Y_pos, "road"))) {

            collidedXtimes++;

            try {
                collide(oldX, oldY);
                velocity[0] = (float) 0.3 * oldDirection.x;
                velocity[1] = (float) 0.3 * oldDirection.y;
                driveForward();
            } catch (java.lang.StackOverflowError e) {
                collide(oldX, oldY);
            }

        } else {
            collide(oldX, oldY);
        }
    }

    public void collide(float oldX, float oldY) {
        PlayScreen.playCollisionNoise();
        velocity[0] = -velocity[0];
        velocity[1] = -velocity[1];
        X_pos = oldX;
        Y_pos = oldY;
    }

    public void turn(String direction) {
        applyFriction(0.6);

        move(10);
        Direction newDirection = getDirection(direction);
        if (currentAngle != newDirection.angle) {

            int rotationAngle = (int) (newDirection.angle - currentAngle);
            sprite.rotate(-rotationAngle);
            currentAngle = currentAngle + rotationAngle;

            if (Math.abs(currentAngle) > 360) {
                currentAngle = Math.abs(currentAngle) - 360;
            }

            if (currentAngle == newDirection.angle) {
                oldDirection = currentDirection;
                currentDirection = newDirection;
            }

        }
    }

    public Direction getDirection(String direction) {
        if (direction.equals("UP")) {
            return UP;
        } else if (direction.equals("DOWN")) {
            return DOWN;
        } else if (direction.equals("LEFT")) {
            return LEFT;
        } else return RIGHT;
    }

    public Sprite getSprite() {
        return sprite;
    }


    public void move(float acceleration) {
        accelerate(acceleration);
        applyFriction(0.1);
    }

    private void applyFriction(double friction) {
        if (velocity[0] > -10) {
            velocity[0] -= velocity[0] * friction;
        }
        if (velocity[1] > -10) {
            velocity[1] -= velocity[1] * friction;
        }
    }

    public boolean hasArrived(Location location) {
        return (this.getSprite().getX() + this.getSprite().getWidth() / 2 >= location.getX() - 30
                && this.getSprite().getX() + this.getSprite().getWidth() / 2 <= location.getX() + 30)
                && (this.getSprite().getY() + this.getSprite().getHeight() / 2 >= location.getY() - 15)
                && this.getSprite().getY() + this.getSprite().getHeight() / 2 <= location.getY() + 15;
    }


    public void setVelocity(float x, float y) {
        this.velocity[0] = x;
        this.velocity[1] = y;
    }

    public boolean isFull() {
        return full;
    }

    public void addPassenger(Passenger passenger) {
        this.passenger = passenger;
        passenger.getOrigin().removePassenger();
        full = true;
    }

    public void empty() {
        passenger = null;
        full = false;
    }

    public float[] getVelocity() {
        return velocity;
    }

    public float getX() {
        return X_pos;
    }

    public float getY() {
        return Y_pos;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }


    public void setX(float pos) {
        X_pos = pos;
    }

    public void setY(float pos) {
        Y_pos = pos;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}
