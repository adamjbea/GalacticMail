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




public boolean GameOver = false;

private CollisionDetector CD;
//just used for bullet range but could be used for enemy placement and game timeline effects
public static int framecount = 0;


public static void main(String[] args) {
        Thread x;
        GME gmex = new GME();
        gmex.init();
        try {

                while (!(gmex.GameOver)) {
                        //update the tanks
                        //in future games will have a more abstracted way of doing this to account
                        //for multiple objects with multiple update needs
                        for (GameObject o : gmex.gameWorld.getWorldList()){
                                o.update();
                        }
                        //collision detector checks for collisions.
                        // could be rolled up into the update() function possibly
                        //gmex.CD.checkCollision();

                        //repaint the JPanel that is our game
                        gmex.repaint();
                        //update the frame count, which is used for Bullet range
                        framecount++;
                        Thread.sleep(1000 / 144);
        /*if (trex.player1.getLives() == 0 || trex.player2.getLives() == 0) {
        trex.GameOver = true;
        }*/
        }
        } catch (InterruptedException ignored) {

        }

                }



private void init() {
        this.jf = new JFrame("Tank Rotation");
        this.world = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        gameWorld = new GameWorld();
        player = new Player(gameWorld.getShip());

        CD = new CollisionDetector();

        ShipControl sc = new ShipControl(player.getShip(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(sc);

        this.jf.setSize(GME.SCREEN_WIDTH + 1, GME.SCREEN_HEIGHT + 1);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

        this.setBackground(Color.black);
        this.setForeground(Color.GREEN);

        }

@Override
public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);
        //calls the function to draw every object to the GameWorld
        //each object has its own drawImage function
        gameWorld.drawWorld(buffer);
        g2.drawImage(world, 0, 0,  null);





        }


        }

