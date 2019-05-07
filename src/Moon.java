import java.awt.*;
import java.awt.image.BufferedImage;

public class Moon extends MovingObject {

    private boolean starting_moon = false;
    private static int count;


    public Moon(BufferedImage img, int x, int y, int angle){

        super(img, x, y, angle);
        this.speed = 1;
        count++;

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

    public Boolean get_starting_moon(){
        return this.starting_moon;
    }

    public void toggle_starting_moon(){
        if (this.starting_moon){
            this.starting_moon = false;
        }else{
            this.starting_moon = true;
        }
    }

    public static int get_count(){
        return count;
    }

    public static void reduce_count(){
        count--;
    }

}
