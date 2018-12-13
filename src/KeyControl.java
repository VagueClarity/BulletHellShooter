import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyControl implements KeyListener {

    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    private GameObject object;
    private Player player;
    private GameWorld gameWorld;

    public KeyControl(Player player,int up, int down, int left, int right, int shoot, GameWorld gameWorld){
       
        this.player = player;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
        this.gameWorld = gameWorld;
    }


    @Override
    public void keyTyped(KeyEvent ke) {



    }


    @Override
    public void keyPressed(KeyEvent ke) {

        int keyPressed = ke.getKeyCode();

        if (keyPressed == up) {
            this.player.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.player.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.player.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.player.toggleRightPressed();
        }
        if(keyPressed == shoot){

            this.player.toggleShoot();
        }
        if(keyPressed == KeyEvent.VK_P){

            if(GameWorld.state == GameWorld.STATE.GAME) {
                GameWorld.state = GameWorld.STATE.PAUSED;
            }else if(GameWorld.state == GameWorld.STATE.PAUSED){
                GameWorld.state = GameWorld.STATE.GAME;
            }
        }
        if(keyPressed == KeyEvent.VK_R){

            if(GameWorld.state == GameWorld.STATE.END || GameWorld.state == GameWorld.STATE.WIN) {
                gameWorld.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();

        if (keyReleased  == up) {
            this.player.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.player.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.player.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.player.unToggleRightPressed();
        }
        if (keyReleased  == shoot) {
            this.player.unToggleShoot();
        }

    }
}
