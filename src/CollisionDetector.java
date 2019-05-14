import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;

public class CollisionDetector {

    private GameWorld gameWorld;
    private Player player;
    private GME gme;
    private int explosion_x = 0;
    private int explosion_y = 0;
    private boolean explosion_occured = false;
    public CollisionDetector(GameWorld gameWorld, Player player, GME gme){
        this.gameWorld = gameWorld;
        this.player = player;
        this.gme = gme;
    }

    public void detect(){
        this.player.getShip().set_just_landed(false);
        Ship ship = this.gameWorld.getShip();
        Rectangle ship_box = new Rectangle(ship.getX(), ship.getY(), ship.getImg().getWidth(), ship.getImg().getHeight());
        Rectangle obj_box;
        for (GameObject o : gameWorld.getWorldList()){
            if (!(o instanceof Ship)){
                obj_box = new Rectangle(o.getX(), o.getY(), o.getImg().getWidth(), o.getImg().getHeight());
                if (ship_box.intersects(obj_box)) {
                    if (!(ship.get_landed()) && this.gme.get_game_start()) {
                        if (o instanceof Moon && o.exists) {
                            ship.set_landed_moon((Moon) o);
                            this.player.getShip().set_just_landed(true);
                        }
                        if (o instanceof Asteroid) {
                            o.setExists(false);
                            this.player.getShip().set_ship_death(true);
                            this.explosion_occured = true;
                            this.explosion_x = this.player.getShip().getX();
                            this.explosion_y = this.player.getShip().getY();
                        }
                        if (o instanceof Powerup) {
                            o.setExists(false);
                            this.player.gain_life();
                        }


                    }
                }
            }
        }
        if (this.explosion_occured){
            this.gameWorld.add_explosion(explosion_x, explosion_y);
            this.explosion_occured = false;
            this.explosion_x = 0;
            this.explosion_y = 0;
        }

    }


}
