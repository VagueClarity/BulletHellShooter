import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUp extends GameObject{


    private int x;
    private int y;
    private int time;
    private BufferedImage img;
    private int timer;
    private Color color;

    private int delay;
    private int type;



    // type 1  -- shot gun
    // type 2  --
    // type 3


    public PowerUp(BufferedImage img,int x, int y, int duration, int type){

        super(x,y, 40,40,"powerup", true, Color.white);
        this.delay = duration;
        this.img = img;
        this.timer = 0;
        this.type = type;

        if(type == 1){

            color = Color.PINK;
        }
        if(type == 2){

            color = new Color(153,0,51);

        }
        if(type == 3){
            color = new Color(153,153,102);
        }

    }



    public void update(){

        if(isVisible()){

            setY(getY() + 2);

        }

    }

    public void executeEffect(Player p){

        if(type == 1 || type == 2) {
            p.setFireType(type);
        }
        else if(type == 3 ){

            if(p.getLife() < 3) {
                p.setLife(p.getLife() + 1);
            }
        }
    }




    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        if(isVisible()) {
            g2d.setColor(color);
            g2d.fillOval(getX(), getY(), getW(), getH());

        }
    }




}
