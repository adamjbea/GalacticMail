import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelController implements KeyListener {

    private StateSelector ss;
    private final int accept;

    public LevelController(StateSelector ss, int accept){
        this.ss = ss;
        this.accept = accept;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (ss.get_level_won()){
            if (keyPressed == accept){
                ss.set_next_level();
                ss.untoggle_level_won();
                GME.levelcount++;
            }
        }
        if (ss.get_game_over()){
            if (keyPressed == accept) {
                GME.levelcount = 1;
                ss.start_game();
                ss.untoggle_game_over();
            }
        }
        if (!(ss.get_game_start())){
            if (keyPressed == accept) {
                ss.start_game();
                ss.toggle_game_start();
            }
        }

    }





    @Override
    public void keyReleased(KeyEvent ke) {
    }




}
