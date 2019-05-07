

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
            private BufferedImage ship_flying_img;
            private BufferedImage ship_landed_img;
            private BufferedImage moonImg;
            private BufferedImage background;
            private BufferedImage asteroidImg;

            public Boolean ship_death = false;


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
            ship_flying_img = read(new File("shipFlying.png"));
            ship_landed_img = read(new File("shipLanded.png"));
            background = read(new File("Background.bmp"));
            moonImg = read(new File("moon01.png"));
            asteroidImg = read(new File("asteroid.png"));
            System.out.println("images loaded");


        } catch (IOException ex) {
            System.out.println("yup cant read that shit");
            System.out.println(ex.getMessage());
        }



        player = new Ship(ship_flying_img, GME.SCREEN_WIDTH/2 - 24, GME.SCREEN_HEIGHT/2 - 24, 0, ship_landed_img);

    }

    public void update(){
        for (GameObject o : worldList){
            o.update();
        }
    }

    public void drawWorld(Graphics2D buffer){
        this.drawLayout(buffer);
        if (this.ship_death){
            this.place_player();
        }
        for (int i = 0; i < worldList.size(); i++){
            if(worldList.get(i).exists){
                worldList.get(i).drawImage(buffer);
            }else{
                if (worldList.get(i) instanceof Moon){
                    Moon.reduce_count();
                }
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
        player.setExists(true);
    }

    public void set_up_level(){
        this.addGameObject(player);
        this.place_player();
        Random rand = new Random();
        Moon temp_moon;
        Asteroid temp_asteroid;
        for(int i = 0; i < rand.nextInt(10) + 1; i++){
            temp_moon = new Moon(moonImg, rand.nextInt(GME.SCREEN_WIDTH - 32) + 1, rand.nextInt(GME.SCREEN_HEIGHT - 32) + 1, rand.nextInt(359));
            this.addGameObject(temp_moon);
        }
        for(int i = 0; i < rand.nextInt(15) + 5; i++){
            temp_asteroid = new Asteroid(asteroidImg, rand.nextInt(GME.SCREEN_WIDTH - 32) + 1, rand.nextInt(GME.SCREEN_HEIGHT - 32) + 1, rand.nextInt(359));
            this.addGameObject(temp_asteroid);
        }
    }


}
