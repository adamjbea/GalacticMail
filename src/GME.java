import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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



private CollisionManager CM;
private PlayerManager PM;
private ArrayList<Manager> manager_list = new ArrayList<>();

private StateSelector SS;


public static void main(String[] args) {
        Thread x;
        GME gmex = new GME();
        gmex.init();
        try {

                while (true) {
                        framecount++;
                        gmex.update();
                        gmex.repaint();
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

        CM = new CollisionManager(gameWorld, player, this);
        PM = new PlayerManager(player, gameWorld, this);

        manager_list.add(gameWorld);
        manager_list.add(CM);
        manager_list.add(PM);

        SS = new StateSelector(this, player);

        ShipControl sc = new ShipControl(player.getShip(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        LevelController kc = new LevelController(SS, KeyEvent.VK_SPACE);
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

       SS.draw_screen(g2);
}

 public Boolean get_level_won(){ return this.SS.get_level_won(); }


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
        return this.SS.get_game_over();
}

public void toggle_game_over(){
        this.SS.toggle_game_over();
}

public void toggle_level_won(){
        this.SS.toggle_level_won();
}

public boolean get_game_start(){return this.SS.get_game_start();}

public void update(){
        for (Manager m : manager_list){
                m.update();
        }
}


}

