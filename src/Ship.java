
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
    private boolean LaunchPressed;


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

    void toggleLaunchPressed() {this.LaunchPressed = true;}

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

    void unToggleShootPressed() {this.LaunchPressed = false;}


    @Override
    public void update() {
        /*if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }*/

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.LaunchPressed) {
            this.moveForwards();

        }
    }

    private void launch() {

        }



    @Override

    public void checkBorder(){
        int rads = this.getAngle() % 360;
        if (this.getY() < 0 && ((rads > 270 && rads < 360) || (rads > 0 && rads <= 90 ))) {
            this.setY(GME.SCREEN_HEIGHT);
        }
        if (this.getY() > GME.SCREEN_HEIGHT && rads > 90 && rads < 270) {
            this.setY(0);
        }
        if (this.getX() < 0 && rads > 180 && rads < 360 ) {
            this.setX(GME.SCREEN_WIDTH);
        }
        if (this.getX() > GME.SCREEN_WIDTH && rads >= 0 && rads < 180) {
            System.out.println("check");
            this.setX(0);
        }
    }




    @Override

    public void drawImage(Graphics g){
        super.drawImage(g);
    }



}
