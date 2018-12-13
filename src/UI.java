import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {


    private int x;
    private int y;

    private BufferedImage img;
    private GameWorld gw;
    private Player player1;
    private Player player2;
    private int greenValue = 255;
    private int redValue = 255;

    public UI(BufferedImage img, GameWorld gw){

        this.gw = gw;
        this.player1 =  gw.getPlayer();


    }

    public void update(){
        if(player1.getHealth() >= 0) {
            greenValue = player1.getHealth() * 2;
            redValue = 255 - player1.getHealth() - 55;
        }

    }

    public void draw(Graphics g){


        Graphics2D g2d = (Graphics2D) g;



       // g2d.fillRect(0, 900-25, GameWorld.SCREEN_W, 100 );

        for(int i =0; i < player1.getLife(); i++){

            g2d.setColor(Color.magenta);
            g2d.fillOval((i*30) + 30, gw.SCREEN_H - 50, 20,20);

        }
        g2d.setColor(new Color(redValue, greenValue, 0));
        g2d.fillRoundRect(120, gw.SCREEN_H - 50, 2 * player1.getHealth(),20,30,30);

    }



}

