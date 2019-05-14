import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelController implements KeyListener {

    private ScreenManager sm;
    private final int accept;

    public LevelController(ScreenManager sm, int accept){
        this.sm = sm;
        this.accept = accept;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (sm.get_level_won()){
            if (keyPressed == accept){
                sm.set_next_level();
                sm.untoggle_level_won();
                GME.levelcount++;
            }
        }
        if (sm.get_game_over()){
            if (keyPressed == accept) {
                sm.start_game();
                sm.untoggle_game_over();
                GME.levelcount = 1;
            }
        }
        if (!(sm.get_game_start())){
            if (keyPressed == accept) {
                sm.start_game();
                sm.toggle_game_start();
            }
        }

    }





    @Override
    public void keyReleased(KeyEvent ke) {
    }




}
