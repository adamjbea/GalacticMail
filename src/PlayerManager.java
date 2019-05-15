public class PlayerManager extends Manager {

    private GME gme;
    private Player player;
    private GameWorld world;

    public PlayerManager(Player player, GameWorld world, GME gme){
        this.gme = gme;
        this.player = player;
        this.world = world;
    }

    public void update(){
        if (this.player.getShip().get_just_landed()){
            this.player.add_score(100);
        }
        if (this.player.getShip().get_landed() && !(this.player.getShip().get_landed_moon().get_starting_moon()) && GME.framecount % 10 == 0 && !(gme.get_level_won())){
            this.player.score_decay();
        }
        if (this.player.getShip().get_ship_death()){
            this.world.place_player();
            this.player.getShip().set_ship_death(false);
            this.player.loseLife();
            this.player.reduce_score(100);
            if (this.player.getLives()==0){
                this.gme.toggle_game_over();
            }
        }
        if (Moon.get_count() == 1 && this.player.getShip().get_landed()){
            this.gme.toggle_level_won();
        }
    }

}
