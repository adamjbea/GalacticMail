
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author anthony-pc
 */
public class ShipControl implements KeyListener {

    private Ship s;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;


    public ShipControl(Ship s, int up, int down, int left, int right, int shoot) {
        this.s = s;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;

    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.s.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.s.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.s.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.s.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.s.toggleLaunchPressed();
        }
    }





    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.s.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.s.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.s.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.s.unToggleRightPressed();
        }
        //if (keyReleased == shoot){
            //this.s.toggleShootPressed();
            //this.t1.unToggleShootPressed();
        }


    }



