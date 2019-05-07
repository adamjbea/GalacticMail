import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GME extends JPanel {

//these are the constant variables that define the world space and then the screen viewing space
public static final int SCREEN_WIDTH = 800;
public static final int SCREEN_HEIGHT = 600;


//every game engine has a game world and two players
private GameWorld gameWorld;
private Player player;

//came ready made with the TRE sample code
private BufferedImage world;
private Graphics2D buffer;
private JFrame jf;

public static int framecount = 0;
private Boolean level_won = false;
private Boolean game_start = false;




public boolean GameOver = false;

private CollisionDetector CD;
//just used for bullet range but could be used for enemy placement and game timeline effects



public static void main(String[] args) {
        Thread x;
        GME gmex = new GME();
        gmex.init();
        try {

                while (true) {
                        gmex.gameWorld.update();
                        gmex.CD.detect();

                        if (gmex.CD.just_landed){
                                gmex.player.add_score(100);
                        }
                        if (gmex.player.getShip().get_landed() && !(gmex.player.getShip().get_landed_moon().get_starting_moon()) && framecount % 10 == 0 && !(gmex.get_level_won())){
                                gmex.player.score_decay();
                        }
                        if (gmex.gameWorld.ship_death){
                                gmex.gameWorld.place_player();
                                gmex.gameWorld.ship_death = false;
                                gmex.player.loseLife();
                                if (gmex.player.getLives()==0){
                                        System.out.println("made it");
                                        gmex.GameOver = true;
                                        System.out.println("Game Over: " + gmex.GameOver);
                                }
                        }
                        if (Moon.get_count() == 1 && gmex.player.getShip().get_landed()){
                                gmex.level_won = true;
                        }
                        gmex.repaint();
                        framecount++;
                        Thread.sleep(1000 / 144);
        }
        } catch (InterruptedException ignored) {
        }

}

private void init() {
        this.jf = new JFrame("Galactic Mail");
        this.world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        gameWorld = new GameWorld();
        this.gameWorld.set_up_level();
        player = new Player(gameWorld.getShip());

        CD = new CollisionDetector(gameWorld, player);

        ShipControl sc = new ShipControl(player.getShip(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        LevelController kc = new LevelController(this, KeyEvent.VK_SPACE);
        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(sc);
        this.jf.addKeyListener(kc);

        this.jf.setSize(GME.SCREEN_WIDTH + 1, GME.SCREEN_HEIGHT + 1);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

        this.setBackground(Color.black);
        this.setForeground(Color.WHITE);

        }

@Override
public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);
        //calls the function to draw every object to the GameWorld
        //each object has its own drawImage function
        gameWorld.drawWorld(buffer);
        g2.drawImage(world, 0, 0, null);
        for (int i = 0; i < player.getLives(); i++){
                g2.drawImage(gameWorld.get_ship_landed_img(), 20 + 48*i, 20, null);
        }
        if (!(this.level_won) && this.game_start) {
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 35));
                g2.drawString(("Score: $" + this.player.get_score()), SCREEN_WIDTH / 2 - 75, 50);
        }
        if(this.level_won){
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                g2.drawString(("DELIVERY COMPLETE"), SCREEN_WIDTH / 4 - 45, 300);
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g2.drawString(("Press Space To Continue"), SCREEN_WIDTH / 4 + 75, 350);


        }
        if (this.GameOver){
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                g2.drawString(("GAME OVER"), SCREEN_WIDTH / 4 + 50, 300);
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g2.drawString(("Press Space To Restart"), SCREEN_WIDTH / 4 + 75, 350);
        }

        if (!(game_start)){

                g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                g2.drawString(("GALACTIC MAIL"), SCREEN_WIDTH / 4, 300);
                g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g2.drawString(("Press Space To Start"), SCREEN_WIDTH / 4 + 50, 350);

        }





        }

 public Boolean get_level_won(){

        return this.level_won;

}

public void set_level_won(Boolean bool){

        this.level_won = bool;

}

public void set_next_level(){
        this.gameWorld.getWorldList().clear();
        this.gameWorld.addGameObject(this.gameWorld.getShip());
        this.gameWorld.set_up_level();
        this.gameWorld.place_player();
}

public void start_game(){
        this.player.reset_player();
        this.set_next_level();
}

public Boolean get_game_start(){
        return this.game_start;
}

public void set_game_start(Boolean bool){
        this.game_start = bool;
}

}

