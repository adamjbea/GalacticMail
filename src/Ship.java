
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import static javax.imageio.ImageIO.read;
import java.io.File;

/**
 *
 * @author anthony-pc
 */
public class Ship extends MovingObject{



    private BufferedImage bulletImg;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;


    Ship(BufferedImage img, int x, int y, int angle) {
        super(img, x, y, angle);


        this.speed = 2;
        this.rotationalSpeed = 2;

    }


    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {this.ShootPressed = true;}

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {this.ShootPressed = false;}


    @Override
    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed) {
            this.unToggleShootPressed();

        }
    }

    private void shoot() {

        }



    @Override

    public void checkBorder(){
        if (this.getX() < 30) {
            this.setX(30);
        }
        if (this.getX() >= GME.SCREEN_WIDTH - 88) {
            this.setX(GME.SCREEN_WIDTH - 88);
        }
        if (this.getY() < 30) {
            this.setY(30);
        }
        if (this.getY() >= GME.SCREEN_HEIGHT - 80) {
            this.setY(GME.SCREEN_HEIGHT - 80);
        }
    }

    public Boolean getUpPressed(){
        return this.UpPressed;
    }
    public Boolean getDownPressed(){
        return this.DownPressed;
    }


    @Override

    public void drawImage(Graphics g){
        super.drawImage(g);
    }



}
