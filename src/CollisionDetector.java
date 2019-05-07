import java.awt.*;

public class CollisionDetector {

    private GameWorld gameWorld;
    private Player player;
    Boolean just_landed = false;
    public CollisionDetector(GameWorld gameWorld, Player player){
        this.gameWorld = gameWorld;
        this.player = player;
    }

    public void detect(){
        this.just_landed = false;
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
                            this.just_landed = true;
                        }
                        if (o instanceof Asteroid) {
                            o.setExists(false);
                            this.gameWorld.ship_death = true;
                            //ship.
                        }


                    }
                }
            }
        }

    }


}
