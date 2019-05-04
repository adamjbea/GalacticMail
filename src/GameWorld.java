

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
            private BufferedImage background;


            private static ArrayList<GameObject> worldList;

    public GameWorld(){

                worldList = new ArrayList<>();
        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            shipImg = read(new File("newTank.png"));
            background = read(new File("Background.bmp"));
            System.out.println("images loaded");


        } catch (IOException ex) {
            System.out.println("yup cant read that shit");
            System.out.println(ex.getMessage());
        }



        player = new Ship(shipImg, GME.SCREEN_WIDTH/4 - 25, GME.SCREEN_HEIGHT/4 - 25, 0);


        this.addGameObject(player);

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

    public static void addGameObject(GameObject object){
        worldList.add(object);
    }

    public static ArrayList<GameObject> getWorldList(){return worldList;}

    public void drawLayout(Graphics2D buffer){

        //buffer.clearRect(0,0, GME.SCREEN_WIDTH, GME.SCREEN_HEIGHT);
        buffer.drawImage(background, 0, 0, null);
    }


}
