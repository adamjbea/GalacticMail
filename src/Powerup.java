import java.awt.*;
import java.awt.image.BufferedImage;

public class Powerup extends MovingObject {

    public Powerup(BufferedImage img, int x, int y, int angle){
        super(img, x, y , angle);
        this.speed = 2;
    }

    public void update(){
        this.moveForwards();
    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, this.getX(), this.getY(), null);
    }
}
