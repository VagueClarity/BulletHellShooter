import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends GameObject {


    private BufferedImage img;

    private ArrayList<Bullet> bullets;
    private int health;
    private boolean shoot;
    private int timer = 0;
    private int DEFAULT_BULLET_DELAY = 400;
    private int dir;
    private int type;
    private int angle;
    private int xv;
    private int yv;
    private boolean hit;


    // type 1 = normal
    // type 2 = magic
    // type 3 = rare

    public Enemy(BufferedImage img, int x, int y, int health, int type, int dir){

        super(x, y, 35 ,35, "enemy",true, Color.RED);
        this.img = img;
        this.health = health;

        this.shoot = false;
        this.bullets = new ArrayList<>();
        this.dir = dir;
        this.type = type;
        this.angle = 0;
    }


    public void update() {



        if(isVisible()) {

            if (this.type == 1) {

                //  setY(getY() + 2);
                setX((getX() + this.dir));


                if (getX() + getW() >= GameWorld.SCREEN_W || getX() <= 0) {
                    this.dir *= -1;
                }
                addingBullet(null, getX() + getW() / 2, getY() + getH(), 10, 3, 90,DEFAULT_BULLET_DELAY);

            }
            else if (this.type == 2) {
                setColor(Color.ORANGE);
                this.DEFAULT_BULLET_DELAY = 100;




                if(getY() >= 300) {
                    this.xv = (int) Math.round(2 * Math.cos(Math.toRadians(angle)));
                    this.yv = (int) Math.round(2 * Math.sin(Math.toRadians(angle)));
                    setX(getX() + this.xv);
                    setY(getY() + this.yv);

                    angle++;
                }
                else{
                    setY(getY() + 1);
                }
                addingBullet(null, getX() + getW() / 2, getY() + getH(), 10, 3, 90,DEFAULT_BULLET_DELAY);

            }
            else if(this.type == 3){



                setColor(Color.BLACK);

                this.DEFAULT_BULLET_DELAY = 50;
                setY(getY() + this.dir);
                setX(getX() + this.dir);
                timer -= 1;
                if(timer <= 0 && isVisible() ){

                    bullets.add(new Bullet(null,  getX() + getW() / 2, getY() + getH(), 10, 3, 135));
                    bullets.add(new Bullet(null,  getX() + getW() / 2, getY() + getH(), 10, 3, 90));
                    timer = DEFAULT_BULLET_DELAY;
                }
            }
            else if(this.type == 4){
                setColor(Color.BLACK);

                this.DEFAULT_BULLET_DELAY = 50;
                setY(getY() + this.dir);
                setX(getX() - this.dir);

                timer -= 1;
                if(timer <= 0 && isVisible() ){


                    bullets.add(new Bullet(null,  getX() + getW() / 2, getY() + getH(), 10, 3, 80));
                    bullets.add(new Bullet(null,  getX() + getW() / 2, getY() + getH(), 10, 3, 90));
                    timer = DEFAULT_BULLET_DELAY;
                }


            }
        }
       updateBullet();
    }



    public void updateBullet(){

        for(Bullet b: bullets){
            if(b.isVisible()) {
                if(b.getY() > GameWorld.SCREEN_H ){
                    //System.out.println("Gone");
                    b.setVisible(false);
                }
                b.update();
            }

        }
    }

    public void addingBullet(Image img, int x, int y, int damage, int speed, int angle, int delay ){

        timer -= 1;
        if(timer <= 0 && isVisible() ){

            bullets.add(new Bullet(null, x, y, damage, speed, angle));
            timer = delay;
        }

    }




    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    protected void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    protected int getTimer() {
        return timer;
    }

    protected void setTimer(int timer) {
        this.timer = timer;
    }

    protected int getDEFAULT_BULLET_DELAY() {
        return DEFAULT_BULLET_DELAY;
    }

    protected void setDEFAULT_BULLET_DELAY(int DEFAULT_BULLET_DELAY) {
        this.DEFAULT_BULLET_DELAY = DEFAULT_BULLET_DELAY;
    }

    protected void drawBullets(Graphics2D g2d){


        if(!bullets.isEmpty()){

            for (int i = 0; i < bullets.size(); i++) {

                if(bullets.get(i).isVisible()) {
                    bullets.get(i).draw(g2d);
                }
            }
        }
    }


    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;


        drawBullets(g2d);


        g2d.setColor(getColor());
        g2d.fillRect(getX(), getY(), getW(), getH());

    }



}
