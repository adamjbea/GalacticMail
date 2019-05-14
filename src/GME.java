import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

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
public static int levelcount = 1;

public boolean GameOver = false;

private CollisionDetector CD;
private ScreenManager SM;
private PlayerManager PM;
//just used for bullet range but could be used for enemy placement and game timeline effects



public static void main(String[] args) {
        Thread x;
        GME gmex = new GME();
        gmex.init();
        try {

                while (true) {
                        gmex.gameWorld.update();
                        gmex.CD.detect();
                        gmex.PM.update();
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

        CD = new CollisionDetector(gameWorld, player, this);
        SM = new ScreenManager(this, player);
        PM = new PlayerManager(player, gameWorld, this);

        ShipControl sc = new ShipControl(player.getShip(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        LevelController kc = new LevelController(SM, KeyEvent.VK_SPACE);
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

       SM.draw_screen(g2);
}

 public Boolean get_level_won(){ return this.SM.get_level_won(); }


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

public boolean get_game_over(){
        return this.SM.get_game_over();
}

public void toggle_game_over(){
        this.SM.toggle_game_over();
}

public void toggle_level_won(){
        this.SM.toggle_level_won();
}

public boolean get_game_start(){return this.SM.get_game_start();}


}

