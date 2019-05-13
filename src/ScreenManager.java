import java.awt.*;

public class ScreenManager {

    private Boolean game_over = false;
    private Boolean game_start = false;
    private Boolean level_won = false;

    public void draw_screen(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

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

    public void untoggle_game_start(){
        this.game_start = false;
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
}
