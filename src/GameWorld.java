

import javax.imageio.ImageIO;
import java.awt.image.*;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class GameWorld {

            private Ship player;
            private BufferedImage shipImg;
            private BufferedImage moonImg;
            private BufferedImage background;


            private ArrayList<GameObject> worldList;

    public GameWorld(){

                worldList = new ArrayList<>();
        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            shipImg = read(new File("shipFlying.png"));
            background = read(new File("Background.bmp"));
            moonImg = read(new File("moon01.png"));
            System.out.println("images loaded");


        } catch (IOException ex) {
            System.out.println("yup cant read that shit");
            System.out.println(ex.getMessage());
        }



        player = new Ship(shipImg, GME.SCREEN_WIDTH/2 - 24, GME.SCREEN_HEIGHT/2 - 24, 0);


        this.addGameObject(player);
        this.place_player();
        this.set_up_level();

    }



    public void drawWorld(Graphics2D buffer){
        this.drawLayout(buffer);
        for (int i = 0; i < worldList.size(); i++){
            if(worldList.get(i).exists){
                worldList.get(i).drawImage(buffer);
            }else{
                worldList.remove(i);

            }
        }
    }
    public Ship getShip(){
       return player;

    }

    public void addGameObject(GameObject object){
        worldList.add(0, object);
    }

    public ArrayList<GameObject> getWorldList(){return worldList;}

    public void drawLayout(Graphics2D buffer){

        //buffer.clearRect(0,0, GME.SCREEN_WIDTH, GME.SCREEN_HEIGHT);
        buffer.drawImage(background, 0, 0, null);
    }

    public void place_player(){
        Moon starter = new Moon(moonImg, GME.SCREEN_WIDTH/2 - 32, GME.SCREEN_HEIGHT/2 - 32, 0);
        starter.set_starting_moon(true);
        player.set_landed_moon(starter);
        this.addGameObject(starter);
    }

    public void set_up_level(){
        Random rand = new Random();
        Moon temp;
        for(int i = 0; i < rand.nextInt(10) + 1; i++){
            temp = new Moon(moonImg, rand.nextInt(GME.SCREEN_WIDTH - 32) + 1, rand.nextInt(GME.SCREEN_HEIGHT - 32) + 1, rand.nextInt(359));
            this.addGameObject(temp);
        }
    }


}
