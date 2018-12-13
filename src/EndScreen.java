import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class EndScreen {

    private BufferedImage img;
    private float opacity;
    private BufferedImage canvas;

//
//    public EndScreen(File file){
//
//
//        try {
//
//            this.img = read(file);
//
//        }
//        catch(IOException e ){
//            System.out.println("EndScreen picture not found");
//
//        }
//        this.opacity = 0;
//
//    }
//
    public void setImg(File file){
        try {

            this.img = read(file);

        }
        catch(IOException e ){
            System.out.println("EndScreen picture not found");

        }

    }


    public void drawLoseScreen(Graphics g, Color c){

        canvas = new BufferedImage(GameWorld.SCREEN_W, img.getHeight(), BufferedImage.TYPE_INT_RGB);
        //Font font = new Font("Century", Font.BOLD, 50);


        if(opacity <= 0.99) {
            opacity += 0.003;
        }
        Graphics2D g2d = canvas.createGraphics();
        Graphics2D graphics2D = (Graphics2D) g;
        //g2d.setFont(font);
        g2d.setColor(c);
        g2d.fillRect(0,0,GameWorld.SCREEN_W, GameWorld.SCREEN_H);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,opacity ));
        g2d.drawImage(img, canvas.getWidth()/2 - img.getWidth()/2, canvas.getHeight()/2 - img.getHeight() /2, img.getWidth(), img.getHeight(),null );
        //g2d.drawImage(img, 0,0, img.getWidth(), img.getHeight(),null );
        g2d.dispose();
        g.drawImage(canvas, 0,GameWorld.SCREEN_H/2 - img.getHeight() /2,null);
    }

}
