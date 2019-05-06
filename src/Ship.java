
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
    private Moon landed_moon = null;


    Ship(BufferedImage img, int x, int y, int angle) {
        super(img, x, y, angle);


        this.speed = 3;
        this.rotationalSpeed = 1;

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
            if (this.landed_moon != null){
                this.landed_moon.setExists(false);
            }

        }
    }

   public void set_landed_moon(Moon moon){
        this.landed_moon = moon;
   }


    @Override

    public void drawImage(Graphics g){
        super.drawImage(g);
    }



}
