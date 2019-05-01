

import java.awt.image.*;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class GameWorld {

            private Ship player;


            private static ArrayList<GameObject> worldList;

    public GameWorld(){

                worldList = new ArrayList<>();



        player = new Ship(tankImg, TRE.WORLD_WIDTH/4 - 25, TRE.WORLD_HEIGHT/4 - 25, 0);


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

    public BufferedImage getTankImg(){
        return this.tankImg;
    }

    public static void addGameObject(GameObject object){
        worldList.add(object);
    }

    public static ArrayList<GameObject> getWorldList(){return worldList;}

    private void drawLayout(Graphics2D buffer){
        buffer.clearRect(0,0, GME.SCREEN_WIDTH, GME.SCREEN_HEIGHT);
        buffer.drawImage(background, 0, 0, null);
    }


}
