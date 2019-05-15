

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

//basic abstraction for a moving object

public abstract class MovingObject extends GameObject {

    protected int vx = 0;
    protected int vy = 0;
    protected int speed = 0;
    protected int rotationalSpeed = 0;


    public MovingObject(BufferedImage img, int x, int y, int angle){
        super(x, y, angle, img);

    }

    protected void moveForwards() {
        vy = (int) Math.round(this.speed * Math.cos(Math.toRadians(this.getAngle())));
        vx = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.getAngle())));
        this.setX(this.getX() + this.vx);
        this.setY(this.getY() - this.vy);
        checkBorder();
    }

    protected void rotateLeft() {
        this.setAngle(this.getAngle() - this.rotationalSpeed);
    }

    protected void rotateRight() {
        this.setAngle(this.getAngle() + this.rotationalSpeed);
    }

    protected void moveBackwards() {
        this.vx = (int) Math.round(this.speed * Math.cos(Math.toRadians(this.getAngle())));
        this.vy = (int) Math.round(this.speed * Math.sin(Math.toRadians(this.getAngle())));
        this.setX(this.getX() - this.vx);
        this.setY(this.getY() - this.vy);
        checkBorder();
    }

    public void checkBorder(){
        if (this.getY() < 0){
            this.setY(GME.SCREEN_HEIGHT);
        }
        if (this.getY() > GME.SCREEN_HEIGHT){
            this.setY(0);
        }
        if (this.getX() < 0){
            this.setX(GME.SCREEN_WIDTH);
        }
        if (this.getX() > GME.SCREEN_WIDTH){
            this.setX(0);
        }
    }


    abstract public void update();

}
