import java.awt.*;
import java.awt.image.BufferedImage;

public class Moon extends MovingObject {

    private boolean ship_launched = false;
    private boolean starting_moon = false;


    public Moon(BufferedImage img, int x, int y, int angle){

        super(img, x, y, angle);
        this.speed = 1;

    }

    public void set_starting_moon(boolean set){

        this.starting_moon = set;

    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, this.getX(), this.getY(), null);
    }

    public void update(){


            if(!(starting_moon)) {

                this.moveForwards();

            }


    }

}
