

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Player {

    private int lives;
    private int score;


    private Ship ship;

    public Player(Ship ship){
        this.lives = 3;
        this.score = 0;
        this.ship = ship;
    }

    public Ship getShip(){
        return this.ship;
    }


    public int getLives(){
        return this.lives;
    }

    public void loseLife(){
        if (this.lives != 1){
            this.lives--;
        }else{
            this.lives = 0;
            this.getShip().setExists(false);
        }
    }


}
