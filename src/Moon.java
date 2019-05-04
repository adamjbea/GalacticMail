import java.awt.*;
import java.awt.image.BufferedImage;

public class Moon extends MovingObject {

    private boolean has_landed = false;


    public Moon(BufferedImage img, int x, int y){

        super(img, x, y, 0);

    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, this.getX(), this.getY(), null);
    }

    public void update(){

        if (!(has_landed)){
            this.moveForwards();
        }else{
            this.setExists(false);
        }

    }

}
