

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
            private BufferedImage background_one;
            private BufferedImage background_two;
            private BufferedImage asteroidImg;
            private BufferedImage powerupImg;
            private ArrayList<BufferedImage> moon_img_list = new ArrayList<>();
            private ArrayList<BufferedImage> explosion_animation_list = new ArrayList<>();
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
            ship_flying_img = ImageIO.read(getClass().getResource("/shipFlying.png"));
            ship_landed_img = ImageIO.read(getClass().getResource("/shipLanded.png"));
            background_one = ImageIO.read(getClass().getResource("/Background.bmp"));
            background_two = ImageIO.read(getClass().getResource("/Background2.bmp"));
            moonImg = ImageIO.read(getClass().getResource("/moon.png"));
            asteroidImg = ImageIO.read(getClass().getResource("/asteroid.png"));
            powerupImg = ImageIO.read(getClass().getResource("/powerUp.png"));

            for (int i = 1; i <= 8; i++){
                moon_img_list.add(ImageIO.read(getClass().getResource("/moon" + i +".png")));
            }

            for (int i = 0; i <= 6; i++){
                explosion_animation_list.add(ImageIO.read(getClass().getResource("/explosion" + i + ".png")));
            }

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
        if (this.player.get_ship_death()){
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

        if (GME.levelcount %2 == 0) {
            buffer.drawImage(background_two, 0, 0, null);
        }else{
            buffer.drawImage(background_one, 0, 0, null);
        }
    }

    public void place_player(){
        Moon starter = new Moon(moonImg, GME.SCREEN_WIDTH/2 - 32, GME.SCREEN_HEIGHT/2 - 32, 0);
        starter.set_starting_moon(true);
        player.set_landed_moon(starter);
        this.addGameObject(starter);
        player.setExists(true);
        player.unToggleLaunchPressed();
    }

    public void set_up_level(){
        Moon.reset_count();
        Random rand = new Random();
        Moon temp_moon;
        Asteroid temp_asteroid;
        for(int i = 0; i < GME.levelcount + rand.nextInt(GME.levelcount); i++){
            temp_moon = new Moon(this.get_random_moon_img(), rand.nextInt(GME.SCREEN_WIDTH - 50) + 1, rand.nextInt(GME.SCREEN_HEIGHT - 50) + 1, rand.nextInt(359));
            this.addGameObject(temp_moon);
           this.roll_powerup();
        }
        for(int i = 0; i < GME.levelcount + rand.nextInt(GME.levelcount * 2) + 1; i++){
            temp_asteroid = new Asteroid(asteroidImg, rand.nextInt(GME.SCREEN_WIDTH - 50) + 1, rand.nextInt(GME.SCREEN_HEIGHT - 50) + 1, rand.nextInt(359));
            this.addGameObject(temp_asteroid);
           this.roll_powerup();
        }
    }
    public BufferedImage get_random_moon_img(){
        Random rand = new Random();
        return this.moon_img_list.get(rand.nextInt(7));
    }

    public void roll_powerup(){
        Random rand = new Random();
        Powerup powerup;
        if (rand.nextInt(99) + 1  <= 1 * GME.levelcount){
            powerup = new Powerup(powerupImg, rand.nextInt(GME.SCREEN_WIDTH - 50) + 1, rand.nextInt(GME.SCREEN_HEIGHT - 50) + 1, rand.nextInt(359));
            this.addGameObject(powerup);
        }
    }

    public void add_explosion(int x, int y){
        Explosion explosion = new Explosion(explosion_animation_list, x, y, GME.framecount);
        this.addGameObject(explosion);
    }


}
