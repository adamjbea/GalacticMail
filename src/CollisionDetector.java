import java.awt.*;

public class CollisionDetector {

    GameWorld gameWorld;
    Boolean ship_death = false;

    public CollisionDetector(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }

    public void detect(){
        Ship ship = this.gameWorld.getShip();
        Rectangle ship_box = new Rectangle(ship.getX(), ship.getY(), ship.getImg().getWidth(), ship.getImg().getHeight());
        Rectangle obj_box;
        for (GameObject o : gameWorld.getWorldList()){
            if (!(o instanceof Ship)){
                obj_box = new Rectangle(o.getX(), o.getY(), o.getImg().getWidth(), o.getImg().getHeight());
                if (ship_box.intersects(obj_box)) {
                    if (!(ship.get_landed())) {
                        if (o instanceof Moon && o.exists) {
                            ship.set_landed_moon((Moon) o);
                        }
                        if (o instanceof Asteroid) {
                            o.setExists(false);
                            this.ship_death = true;
                            //ship.
                        }


                    }
                }
            }
        }

        if (ship_death){
            this.gameWorld.place_player();
            this.ship_death = false;
        }

    }

}
