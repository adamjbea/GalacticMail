import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class ScreenManager {

    private Player player;
    private GME gme;

    private boolean game_over = false;
    private boolean game_start = false;
    private boolean level_won = false;

    private BufferedImage ship_landed_img;
    private BufferedImage title_img;

    public ScreenManager(GME gme, Player player){

        this.player = player;
        this.gme = gme;
        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            title_img = ImageIO.read(getClass().getResource("/title.png"));
            ship_landed_img = ImageIO.read(getClass().getResource("/shipLanded.png"));
            System.out.println("images loaded");


        } catch (IOException ex) {
            System.out.println("yup cant read that shit");
            System.out.println(ex.getMessage());
        }

    }

    public void draw_screen(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        if (!(this.level_won) && this.game_start) {
            for (int i = 0; i < player.getLives(); i++){
                g2.drawImage(this.ship_landed_img, 20 + 48*i, 20, null);
            }
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 35));
            g2.drawString(("Score: $" + this.player.get_score()), GME.SCREEN_WIDTH / 2 - 75, 50);
        }
        if(this.level_won){
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g2.drawString(("DELIVERY COMPLETE"), GME.SCREEN_WIDTH / 4 - 45, 300);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g2.drawString(("Press Space To Continue"), GME.SCREEN_WIDTH / 4 + 75, 350);


        }
        if (this.game_over){
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g2.drawString(("GAME OVER"), GME.SCREEN_WIDTH / 4 + 50, 300);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g2.drawString(("Press Space To Restart"), GME.SCREEN_WIDTH / 4 + 75, 350);
        }

        if (!(game_start)){
            g2.setFont(new Font("TimesRoman", Font.PLAIN, 35));
            g2.drawImage(title_img, 175, 100, null);
            g2.drawString(("Press Space To Start"), GME.SCREEN_WIDTH / 2 - 150, 450);

        }

    }

    public void toggle_game_over(){
        this.game_over = true;
    }

    public void untoggle_game_over(){
        this.game_over = false;
    }

    public void toggle_game_start(){
        this.game_start = true;
    }

    public void toggle_level_won(){
        this.level_won = true;
    }

    public void untoggle_level_won(){
        this.level_won = false;
    }

    public Boolean get_game_over(){
        return this.game_over;
    }

    public Boolean get_game_start(){
        return this.game_start;
    }

    public Boolean get_level_won(){
        return this.level_won;
    }

    public void set_next_level(){
        this.gme.set_next_level();
    }

    public void start_game(){
        this.gme.start_game();
    }
}
