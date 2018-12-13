import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject{


    private Image img;
    private int damage;
    private int speed;
    private int xv;
    private int yv;
    private final int ROTATIONSPEED = 1;


    private int angle;

    public Bullet(Image img, int x, int y, int damage, int speed, int angle){

        super(x,y,7,7,"bullet",true, Color.WHITE);

        this.img = img;
        this.damage = damage;
        this.speed = speed;
        this.angle =  angle;
        this.xv = 0;
        this.yv = 0;

    }


    public void update(){


        this.xv =  (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        this.yv =  (int) Math.round(speed * Math.sin(Math.toRadians(angle)));


        if(isVisible()) {
            setY(getY() + this.yv);
            setX(getX() + this.xv);
        }
//        if(getY() < 0 || getY() > GameWorld.SCREEN_H || getX()< 0 || getX() > GameWorld.SCREEN_W) {
//            System.out.print("Bullet dissappearing");
//            setVisible(false);
//        }



        //this.angle += this.ROTATIONSPEED;
    }

    public void draw(Graphics g){

        //System.out.println(Math.toDegrees(Math.atan2(0,1)));

        Graphics2D g2d = (Graphics2D) g;
        if(isVisible()) {
            g2d.setColor(getColor());
            g2d.fillRect(getX(), getY(), getW(), getH());
        }
    }


    public int deflect(int x, int y){

       //if(object.getType().equals("player") ){

            this.angle = (int)  Math.toDegrees(Math.atan2(  getY() - y, getX() - x));
            System.out.println(this.angle);

            return this.angle;


     //   }


    }


    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
