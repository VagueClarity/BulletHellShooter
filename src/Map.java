
import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

    private int x;
    private int y;
    private BufferedImage img;

    private int x2;
    private int y2;


    public Map(BufferedImage img, int x, int y ){


        this.img = img;
        this.x = x;
        this.y = y;
        this.x2 = 0;
        this.y2 = y - img.getHeight() * 2;


    }


    public void update(){


        this.y += 1;
        this.y2 +=1;

        if(y > GameWorld.SCREEN_H){
            y = y2 -img.getHeight() * 2;
        }
        if(y2 > GameWorld.SCREEN_H){
            y2 = y - img.getHeight() * 2;
        }

    }


    public void draw(Graphics g){


        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img, x,y, img.getWidth() * 2,img.getHeight()*2,null );
        g2d.drawImage(img, x,y2, img.getWidth() * 2,img.getHeight()*2,null );




    }



}
