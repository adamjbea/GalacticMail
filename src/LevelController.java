import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelController implements KeyListener {

    GME engine;
    private final int accept;

    public LevelController(GME engine, int accept){
        this.engine = engine;
        this.accept = accept;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (engine.get_level_won()){
            if (keyPressed == accept){
                engine.set_next_level();
                engine.set_level_won(false);
                System.out.println("level controller pressed");
            }
        }

    }





    @Override
    public void keyReleased(KeyEvent ke) {
    }




}
