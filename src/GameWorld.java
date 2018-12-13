import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static javax.imageio.ImageIO.read;

public class GameWorld extends JPanel {

    // Screen related things
    private JFrame jf;
    public static int SCREEN_W = 700;
    public static int SCREEN_H =  850;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;
  
    private KeyControl playerControl;
    private MouseInput mouseListener;

    // Timing and Delays
    private int spawnTimer = 0;
    private Random random;
    private int time = 0;
    private int DEFAULT_TIME_DELAY = 20;
    private SpriteLoader spriteLoader;

    //UI/Menu/Background
    private ScoreBoard score;
    private Menu menu;
    private UI gui;
    private Map map;
    private EndScreen endScreen;
    
    // Game entities
    private Player player;
    private ArrayList<Enemy> enemies;
    private Boss boss1 = new Boss(new File("Resources/deathstar.png"),SCREEN_W/2, -200, 2,500);
    private ArrayList<PowerUp> powerUps;
   

    // States and booleans
    public enum STATE{
        MENU,
        GAME,
        HELP,
        END,
        PAUSED,
        WIN
    }
    public static STATE state = STATE.MENU;
    private boolean collided;
    private boolean paused;




    public GameWorld(){

        setUp();

        for(int i =0; i< 7; i++){

            for(int j = 0; j < 2; j++) {
                enemies.add(new Enemy(null, (i + 1) * (75) - 50, (j + 1) * 75 , 5,1,1));
            }
        }



    }

    public void update(){

        if(state != STATE.END  && state != STATE.PAUSED) {
            map.update();
            if (state == STATE.GAME) {

                if (player.getLife() <= 0) {
                    state = STATE.END;
                }
                player.update();
                spawnEnemy();
                gui.update();
                boss1.update();

                if (!powerUps.isEmpty()) {
                    for (PowerUp p : powerUps) {
                        p.update();
                    }
                }
                spawnPowerUp();
                checkCollision();
            }
        }
    }

    public void setUp(){

        jf = new JFrame("Plane Game");
        bufferedImage = new BufferedImage(SCREEN_W, SCREEN_H, BufferedImage.TYPE_INT_RGB);

        // Loading assets
        try {
            map = new Map(read(new File("Resources/islands.png")), 0, 0);
        }
        catch(Exception e){
            System.out.println("Can't find background image");
        }
        spriteLoader = new SpriteLoader(new File("Resources/explosionSheet.png"));


        // GameObjects initialization
        this.paused = false;
        player = new Player(SCREEN_W/2,SCREEN_H - 150 ,null, this);
        menu = new Menu();
        endScreen = new EndScreen();
        score  = new ScoreBoard(this);
        enemies = new ArrayList<>();
        random = new Random();
        powerUps = new ArrayList<>();
        playerControl = new KeyControl(this.player, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, this);
        mouseListener = new MouseInput();
        gui = new UI(null, this);
        collided = false;

        // JFrame stuff
        jf.setLayout(new BorderLayout());
        jf.addKeyListener(playerControl);
        jf.addMouseListener(mouseListener);
        jf.getContentPane().add(this);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(SCREEN_W, SCREEN_H);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);
    }

    // Enemy Spawning

    private int enemyType = 1;

    public void spawnEnemy(){


        if(boss1.isVisible()){
            enemies.clear();
        }


        if(!boss1.isVisible() && spawnTimer % 800 == 0 && spawnTimer != 0){

            if(enemyType == 1) {
                enemies.add(new Enemy(null, -10, -50, 10, 3, 1));
                enemyType = 0;
            }
            else{
                enemies.add(new Enemy(null,SCREEN_W, -50, 10, 4, 1));
                enemyType = 1;
            }

        }

        if(!boss1.isVisible() && spawnTimer % 400 == 0 && spawnTimer != 0){

            enemies.add(new Enemy(null, random.nextInt(SCREEN_W - 10), -10, 10, 2, 1));
        }


        if(spawnTimer == 5000){
            boss1.setVisible(true);
        }

        for(int i = 0; i < enemies.size();i++){

            if(enemies.get(i).isVisible() && enemies.get(i).getY() > SCREEN_H ){
                System.out.println("Enemy going invisible");
                enemies.get(i).setVisible(false);
            }

            enemies.get(i).update();
        }

    }

    public void spawnPowerUp(){

        double rand = Math.random();
        double rand2 = Math.random();

        if(rand < 0.0010){
            if(rand2 <.3) {
                powerUps.add(new PowerUp(null, random.nextInt(SCREEN_W - 50), random.nextInt(20) + 30 * (-1), 50, 1));
            }
            if(rand2 >0.6) {
                powerUps.add(new PowerUp(null, random.nextInt(SCREEN_W - 50), random.nextInt(20) + 30 * (-1), 50, 2));
            }
            if(rand2 >0.9) {
                powerUps.add(new PowerUp(null, random.nextInt(SCREEN_W - 50), random.nextInt(20) + 30 * (-1), 50, 3));
            }
        }

    }

    public void drawEnemy(Graphics2D g){

        for(int i = 0; i < enemies.size();i++){

            if(enemies.get(i).isVisible()) {
                enemies.get(i).draw(g);
            }
        }

    }

    public void paintComponent(Graphics g){


        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = bufferedImage.createGraphics();

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0,0,SCREEN_W,SCREEN_H);
        map.draw(buffer);

        if(state == STATE.GAME || state == STATE.END || state == STATE.PAUSED || state == STATE.WIN) {
            player.draw(buffer);
           // buffer.setColor(Color.BLUE);
            drawEnemy(buffer);
            score.draw(buffer );

            if (!powerUps.isEmpty()) {
                for (PowerUp p : powerUps) {

                    p.draw(buffer);
                }
            }
            gui.draw(buffer);

            if (boss1.isVisible()) {
                boss1.draw(buffer);
            }
            if(state == STATE.PAUSED){
                menu.drawPaused(buffer);
            }

            spawnTimer++;
        }else if(state == STATE.MENU){

            menu.drawMenu(buffer);
        }else if(state == STATE.HELP){
            menu.drawHelp(buffer);
        }

        if(state == STATE.END){
            endScreen.setImg(new File("Resources/gameover.png"));
            endScreen.drawLoseScreen(buffer, Color.ORANGE);
        }
        else if( state == STATE.WIN){

            endScreen.setImg(new File("Resources/youWin.png"));
            endScreen.drawLoseScreen(buffer, Color.GREEN);
        }




        g2.drawImage(bufferedImage, 0, 0, null);
    }


    public void restart(){

        spawnTimer = 0;
        state = STATE.GAME;
        player.setX(GameWorld.SCREEN_W/2);
        player.setY(SCREEN_H - 150 );
        player.setLife(3);
        player.setHealth(100);
        player.setFireType(0);
        spawnTimer = 0;
        enemies.clear();
        score.resetScore();
        for(int i =0; i< 7; i++){

            for(int j = 0; j < 2; j++) {
                enemies.add(new Enemy(null, (i + 1) * (75) - 50, (j + 1) * 75 , 5,1,1));
            }
        }
        player.getBullets().clear();
        boss1.getBullets().clear();
        boss1.setHealth(500);

    }


    public void checkCollision(){



            // Player and Enemy Collision

            for(int i = 0; i < enemies.size(); i++){
                
                // Player and Enemy collision
                
                if(collide(this.player, enemies.get(i)) && this.player.isVisible() && enemies.get(i).isVisible()){
                    System.out.println("Collide");
                    this.player.setHealth(this.player.getHealth() - 100);
                }

                // Player and Enemy bullet collision
                
                for(int j =0; j < enemies.get(i).getBullets().size(); j++){

                    if(collide(this.player, enemies.get(i).getBullets().get(j)) && this.player.isVisible() && enemies.get(i).getBullets().get(j).isVisible()){

                        enemies.get(i).getBullets().get(j).setVisible(false);
                        this.player.setHealth(this.player.getHealth() - enemies.get(i).getBullets().get(j).getDamage());

                    }
                }

            }
            //time = DEFAULT_TIME_DELAY;
//        }



        for(int i = 0; i < player.getBullets().size(); i++){


            // Player bullet and enemy collision

            for(int j = 0; j < enemies.size(); j++) {
                if (collide(enemies.get(j), player.getBullets().get(i)) && enemies.get(j).isVisible() && player.getBullets().get(i).isVisible()) {

                    if (enemies.get(j).getHealth() < 0) {
                        enemies.get(j).setVisible(false);
                        score.addScore(100);
                    }

                    enemies.get(j).setHealth(enemies.get(j).getHealth() - player.getBullets().get(i).getDamage());
                   // enemies.get(j).setColor(Color.RED);
                    player.getBullets().get(i).setVisible(false);


                }
            }


            // Boss collision & player bullet collision

            if(collide(player.getBullets().get(i), this.boss1) && player.getBullets().get(i).isVisible() && boss1.isVisible()){

                if(boss1.getHealth() < 0){
                    boss1.setVisible(false);
                    score.addScore(1000);
                    state = STATE.WIN;
                }
                this.boss1.setHealth(this.boss1.getHealth() - player.getBullets().get(i).getDamage());
               // this.boss1.setHealth(0);
                player.getBullets().get(i).setVisible(false);

            }

        }

        // Bullet vs Bullet Collision

        for(int k = 0 ; k < player.getBullets().size(); k++){

             if(player.getBullets().get(k).isVisible()) {
                for (int i = 0; i < enemies.size(); i++) {

                    if(enemies.get(i).isVisible()) {
                        for (int j = 0; j < enemies.get(i).getBullets().size(); j++) {

                            if (collide(player.getBullets().get(k), enemies.get(i).getBullets().get(j)) && enemies.get(i).getBullets().get(j ).isVisible()) {


                                System.out.println("bullets colliding");

                                player.getBullets().get(k).setVisible(false);
                                enemies.get(i).getBullets().get(j).setColor(Color.yellow);
                                enemies.get(i).getBullets().get(j).deflect(this.player.getX() + this.player.getW()/2, this.player.getY() + this.player.getH()/2);

                                player.getBullets().add(enemies.get(i).getBullets().get(j));

                                enemies.get(i).getBullets().remove(enemies.get(i).getBullets().get(j));   //removes bullet


                                k++;
                                j--;
                                //enemies.get(i).getBullets().get(j).setVisible(false);
                            }
                        }
                    }
                }


                for(int i = 0; i < boss1.getBullets().size(); i++) {


                    if (collide(player.getBullets().get(k), boss1.getBullets().get(i)) && boss1.getBullets().get(i).isVisible()) {

                        System.out.println("boss bullets colliding");
                        player.getBullets().get(k).setVisible(false);
                        boss1.getBullets().get(i).setColor(Color.yellow);
                        boss1.getBullets().get(i).deflect(this.player.getX() + this.player.getW()/2, this.player.getY() + this.player.getH()/2);
                        player.getBullets().add(boss1.getBullets().get(i));
                        boss1.getBullets().remove(boss1.getBullets().get(i));
                        k++;
                        i--;

                    }
                }
             }
        }

        // Boss bullet and player collision

        for(Bullet b : boss1.getBullets() ){


            if(collide(player, b) && player.isVisible() && b.isVisible()){
                b.setVisible(false);
                player.setHealth(player.getHealth() - b.getDamage());
            }
        }


        // Power up collision

        for(PowerUp p : powerUps){

            if(collide(player, p) && p.isVisible()){

                p.executeEffect(this.player);
                p.setVisible(false);
            }
        }



        // Deleting out of bound bullets
        for(int i = 0; i < player.getBullets().size(); i++){
            if(player.getBullets().get(i).getY() < 0 || player.getBullets().get(i).getY() > GameWorld.SCREEN_H ||
                    player.getBullets().get(i).getX()< 0 || player.getBullets().get(i).getX() > GameWorld.SCREEN_W) {

                player.getBullets().remove(player.getBullets().get(i));
            }
        }

    }


    public void flashDamage(GameObject object){

    }


    public boolean collide(GameObject p1, GameObject p2){

        int pX = p2.getX();
        int pY = p2.getY();
        int pW = p2.getW();
        int pH = p2.getH();

        if(p1.getX()< pX + pW && p1.getX() + p1.getW() > pX && p1.getY() < pY + pH && p1.getY() + p1.getH() > pY ){

            return true;
        }

        return false;
    }


    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Boss getBoss1() {
        return boss1;
    }

    public SpriteLoader getSpriteLoader() {
        return spriteLoader;
    }

    public Player getPlayer() {
        return player;
    }

    public static void main(String []args){


        GameWorld gameWorld = new GameWorld();

        try {

            while (true) {
                gameWorld.update();
                gameWorld.repaint();

                //System.out.println(gm.t1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }

    }


}