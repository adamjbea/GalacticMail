
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
    private boolean on_starter_moon;
    private boolean just_landed;
    private BufferedImage landedImg;
    private BufferedImage flyingImg;
    private boolean ship_death;


    Ship(BufferedImage flyingImg, int x, int y, int angle, BufferedImage landedImg) {
        super(flyingImg, x, y, angle);
        this.landedImg = landedImg;
        this.flyingImg = flyingImg;
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
            this.img = this.landedImg;
            this.setX(this.landed_moon.getX() + 9);
            this.setY(this.landed_moon.getY() + 3);
        }
        if (!(this.landed)){
            this.moveForwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.LaunchPressed) {
                this.img = flyingImg;
                this.landed_moon.setExists(false);
                this.landed = false;
                this.unToggleLaunchPressed();
               if (this.on_starter_moon){
                   this.on_starter_moon = false;
               }
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

    public boolean get_landed(){
        return this.landed;
    }

    public boolean get_just_landed(){return this.just_landed;}

    public void set_just_landed(boolean bool){this.just_landed = bool;}

    public Moon get_landed_moon(){
        return this.landed_moon;
    }

    public boolean get_ship_death(){
        return this.ship_death;
    }

    public void set_ship_death(boolean bool){
        this.ship_death = bool;
    }



}
