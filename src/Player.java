import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject{



    private BufferedImage img;
    private int speed;
    private int health;
    private int life;


    // 0 = default;
    // 1 = shotgun;
    // 2 = speed;

    private int fireType = 0;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shoot;
    private boolean died;
    private boolean canRespawn;
    private int time = 0;
    private int DEFAULT_BULLET_DELAY = 20;
    private GameWorld gw;
    private SpriteLoader playerSprite;
    private BufferedImage[] explosion;



    private Animation death;
    private Animation curAnim = null;

    private ArrayList<Bullet> bullets;



    public Player(int x, int y, BufferedImage img, GameWorld gw){

        super(x, y, 35,35, "player", true, Color.GREEN);
        explosion = null;
        this.playerSprite = gw.getSpriteLoader();
        loadFrames();
        this.death = new Animation(explosion, 10);

        this.img = img;
        this.speed = 3;
        this.bullets = new ArrayList<>();
        this.gw = gw;
        this.health = 100;
        this.died = false;
        this.life = 3;
        this.canRespawn = false;
    }


    public void loadFrames(){


       BufferedImage[] temp = { playerSprite.getSprite(0,0), playerSprite.getSprite(1,0), playerSprite.getSprite(2,0), playerSprite.getSprite(3,0),
               playerSprite.getSprite(0,1), playerSprite.getSprite(1,1), playerSprite.getSprite(2,1), playerSprite.getSprite(3,1),
               playerSprite.getSprite(0,2), playerSprite.getSprite(1,2), playerSprite.getSprite(2,2), playerSprite.getSprite(3,2),
               playerSprite.getSprite(0,3), playerSprite.getSprite(1,3), playerSprite.getSprite(2,3), playerSprite.getSprite(3,3)
       };
       explosion = temp;

    }
    public void update(){


        movement();
        collision();
        checkDeath();


        time -= 1;
        if(time <= 0 && this.shoot && isVisible() ){
            fire();
            time = DEFAULT_BULLET_DELAY;
        }


        for(Bullet b: bullets){
            if(b.isVisible()) {
                b.update();
            }
        }


    }

    public void checkDeath(){

        if(curAnim != null) {
            curAnim.update();
            if(curAnim.getCurrentFrame() >= curAnim.getTotalFrames() -1 && curAnim == death){

                System.out.println("stopped");
                curAnim.stop();
                curAnim.restart();
                curAnim = null;
                this.canRespawn = true;

            }

        }

        if(this.health <= 0 && !this.died){
            this.life--;
            setVisible(false);
            this.died = true;
            curAnim = death;
        }


        if(this.life >0 && this.died && this.canRespawn){
            respawn();
        }
    }


    public void respawn(){

        setX(gw.SCREEN_W/2);
        setY(gw.SCREEN_H - 150);
        this.setVisible(true);
        this.died = false;
        this.health = 100;
        this.canRespawn = false;
        this.bullets = new ArrayList<>();

    }

    public void collision(){

        if(getX() > GameWorld.SCREEN_W - getW() - 5){
            setX(GameWorld.SCREEN_W - getW() - 5);
        }
        if(getX() < 0){
            setX(0);
        }
        if(getY() < 0){
            setY(0);
        }
        if(getY() > GameWorld.SCREEN_H - 80){
            setY(GameWorld.SCREEN_H - 80);
        }

    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void toggleShoot(){
        this.shoot = true;

    }

    void unToggleShoot(){
        this.shoot = false;
    }


    public void fire(){

        // default

        if(fireType == 0){
            bullets.add(new Bullet(null, getX() + getW()/2 - 2, getY(),2,-5, 90));
        }
        // shot gun
        if(fireType == 1) {
            bullets.add(new Bullet(null, getX() + getW() / 2 - 2, getY(), 2, -5, 90));
            bullets.add(new Bullet(null, getX() + getW() / 2 - 2, getY(), 2, -5, 70));
            bullets.add(new Bullet(null, getX() + getW() / 2 - 2, getY(), 2, -5, 110));
        }

        // speed
        if(fireType == 2) {
            this.DEFAULT_BULLET_DELAY = 10;
            bullets.add(new Bullet(null, getX() + getW()/2 - 2, getY(),2,-5, 90));
        }

    }


    public void movement(){

        if(curAnim == death && curAnim.isStopped()) {
            System.out.println("starting");
            curAnim.start();
        }
        if(isVisible()) {


//            if (this.UpPressed) {
//                setY(getY() - speed);
//
//            }
//            if (this.DownPressed) {
//
//
//                setY(getY() + speed);
//            }
            if (this.RightPressed) {


                setX(getX() + speed);
            }
            if (this.LeftPressed) {

                setX(getX() - speed);
            }
        }
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public BufferedImage getImg() {
        return img;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getFireType() {
        return fireType;
    }

    public void setFireType(int fireType) {
        this.fireType = fireType;
    }

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        //g2d.drawImage(img, x,y,null);
        if(isVisible()) {
            g2d.setColor(getColor());
            g2d.fillRect(getX(), getY(), getW(), getH());
           // g2d.drawImage(explosion[0], getX(), getY(),null);
        }

        if(died && !(curAnim == null)){
            //curAnim = death;
            g2d.drawImage(curAnim.getSprite(),getX(),getY(),null);
        }

        if(!bullets.isEmpty()){

            for (int i = 0; i < bullets.size(); i++) {

                if(bullets.get(i).isVisible()) {
                    bullets.get(i).draw(g2d);
                }
            }
        }

    }
}
