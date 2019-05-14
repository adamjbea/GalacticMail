import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Explosion extends GameObject {

    private ArrayList<BufferedImage> animation_list;
    private int initial_frame;
    private int animation_index = 0;

    public Explosion(ArrayList<BufferedImage> animation_list, int x, int y, int initial_frame){
        super(x, y, 0, animation_list.get(0));
        this.animation_list = animation_list;
        this.initial_frame = initial_frame;
    }

    public void update(){
        if (animation_index + 1 == animation_list.size()){
            this.setExists(false);
        }else if(GME.framecount - this.initial_frame == 10){
            this.animation_index++;
            this.setImg(this.animation_list.get(animation_index));
            this.initial_frame = GME.framecount;
        }
    }

    public void drawImage(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, this.getX(), this.getY(), null);
    }



}
