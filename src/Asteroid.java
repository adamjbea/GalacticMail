import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Asteroid extends MovingObject {

    private int rotational = 0;

    public Asteroid(BufferedImage img, int x, int y, int angle){
        super(img, x, y, angle);
        Random rand = new Random();
        this.rotationalSpeed = rand.nextInt(2) + 1;
        this.speed = rand.nextInt(1) + 1;
    }

    public void update(){
        moveForwards();
        if (this.rotational == 359){
            this.rotational = 0;
        }else{
            Random rand = new Random();
            if (rand.nextInt(1) == 1) {
                this.rotational++;
            }else{
                this.rotational--;
            }
        }
    }

    public void drawImage(Graphics g){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(rotational), 25, 22);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation, null);

    }


}
