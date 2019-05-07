

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

        }
    }

    public void reset_player(){
        this.lives = 3;
        this.score = 0;
    }

    public void add_score(int score){
        this.score += score;
    }

    public void score_decay(){
        if (score != 0) {
            this.score -= 1;
        }
    }

    public int get_score(){
        return this.score;
    }

}
