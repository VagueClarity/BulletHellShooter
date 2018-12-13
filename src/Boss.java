import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Boss extends Enemy{


    private BufferedImage img;
    private int speed;



    public Boss(File file, int x, int y, int speed, int health){
        //super(x,y,200,200, "boss");
        super(null, x,y, health,1,1);

        try{
            this.img = read(file);
        }

        catch(IOException e){
            System.out.println("boss image is not found");
        }


        this.speed = speed;
        setVisible(false);

        setW(200);
        setH(200);
        setType("boss");
        setColor(Color.yellow);
    }



    public void update(){


        if(isVisible()) {

            setY(getY() + 1);
            if (getX() > GameWorld.SCREEN_W - img.getWidth()/3 || getX() < 0) {
                this.speed *= -1;
            }
            if(getY() >= 50){
                setY(50);
                setX(getX() + (speed));
            }


            setTimer(getTimer()-1);
            if(getTimer() <= 0 && isVisible() ){

                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 150));
                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 135));
                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 120));
                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 90));
                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 60));
                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 45));
                getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 30));

//                for(int i = 0; i < 50; i++){
//                    getBullets().add(new Bullet(null, getX() + getW()/2, getY() + getH(), 10, 3, 140 - i * 2));
//
//                }

                setTimer(40);
            }



        }




        for(Bullet b: getBullets()){
            if(b.isVisible()) {
                if(b.getY() > GameWorld.SCREEN_H ){

                    b.setVisible(false);
                }
                b.setColor(Color.magenta);
                b.update();
            }

        }


    }

    public void draw(Graphics g){

        Graphics2D g2d = (Graphics2D) g;


        drawBullets(g2d);

        if(isVisible()) {
            g2d.setColor(getColor());
            //g2d.fillOval(getX(), getY(), getW(), getH());
            g2d.drawImage(img, getX(), getY(), img.getWidth()/3, img.getHeight()/3, null);
        }




    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


}
