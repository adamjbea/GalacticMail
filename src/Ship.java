
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

    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean LaunchPressed;
    private Moon landed_moon;
    private boolean landed;


    Ship(BufferedImage img, int x, int y, int angle) {
        super(img, x, y, angle);


        this.speed = 2;
        this.rotationalSpeed = 1;

    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleLaunchPressed() {this.LaunchPressed = true;}

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleLaunchPressed() {this.LaunchPressed = false;}


    @Override
    public void update() {
        if (this.landed){
            System.out.println("made it to landed");
            this.setX(this.landed_moon.getX() + 9);
            this.setY(this.landed_moon.getY() + 3);
        }
        if (!(this.landed)){
            System.out.println("made it to launched)");
            this.moveForwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.LaunchPressed) {
                this.landed_moon.setExists(false);
                this.landed = false;
                this.unToggleLaunchPressed();
                System.out.println("launch pressed");
                System.out.println("landed: " + this.landed);
        }
    }

   public void set_landed_moon(Moon moon){
        this.landed_moon = moon;
        this.landed = true;
   }


    @Override

    public void drawImage(Graphics g){
        super.drawImage(g);
    }

    public Boolean get_landed(){
        return this.landed;
    }



}
