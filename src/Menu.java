import java.awt.*;

public class Menu {


    public static Rectangle playButton = new Rectangle(GameWorld.SCREEN_W/2 - 50, 400, 100, 50);
    public static Rectangle helpButton = new Rectangle(GameWorld.SCREEN_W/2 - 50, 500, 100, 50);
    public static Rectangle backButton = new Rectangle(GameWorld.SCREEN_W/2 - 50, 600, 100, 50);

    private static Color color1 = new Color(74, 78, 84);


    public static Color getColor1() {
        return color1;
    }

    public static void setColor1(Color c) {
        color1 = c;
    }

    public void drawMenu(Graphics g){

        Graphics2D g2d  = (Graphics2D) g;
        Font font1 = new Font("Century", Font.BOLD, 50);

        // Centering the string
        String text = "Game";
        FontMetrics metrics = g2d.getFontMetrics(font1);

        g2d.setFont(font1);

        // Title Text
        g2d.setColor(new Color(74, 78, 84));
        g2d.fillRoundRect(GameWorld.SCREEN_W/2 - metrics.stringWidth(text)/2 - 20 ,(GameWorld.SCREEN_H )/4 - 75, metrics.stringWidth(text) + 40, 120,40,40 );
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(GameWorld.SCREEN_W/2 - metrics.stringWidth(text)/2 ,(GameWorld.SCREEN_H )/4 - 55, metrics.stringWidth(text), 80,40,40 );
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, GameWorld.SCREEN_W/2 - metrics.stringWidth(text)/2 ,(GameWorld.SCREEN_H )/4);


        // First button

        Font font2 = new Font("Century", Font.BOLD, 30);
        g2d.setFont(font2);
        g2d.setColor(color1);
        g2d.fillRect(playButton.x, playButton.y, playButton.width, playButton.height);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Play", playButton.x + 15 , playButton.y + 35);
        g2d.draw(playButton);


        // Help Button
//        Font font3 = new Font("Century", Font.BOLD, 30);
//        g2d.setFont(font3);
        g2d.setColor(color1);
        g2d.fillRect(helpButton.x, helpButton.y, helpButton.width, helpButton.height);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Help", helpButton.x + 15 , helpButton.y + 35);
        g2d.draw(helpButton);



    }

    public void drawHelp(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        String text = "LEFT AND RIGHT ARROW KEYS";
        String text3 = "KEYS TO MOVE";
        String text2  = "SPACEBAR TO SHOOT";

        int w = (GameWorld.SCREEN_W * 3) / 4;
        int h = (GameWorld.SCREEN_H * 2) / 4;
        int x = GameWorld.SCREEN_W/2 - w/2;
        int y = GameWorld.SCREEN_H /4;
        Font font = new Font("Century", Font.BOLD, 25);

        g2d.setColor(Color.gray);
        g2d.fillRect(x,y,w,h);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, x + w/2 - 200, y + 80);
        g2d.drawString(text2, x + w/2 - 150, y + 220);
        g2d.drawString(text3, x + w/2 - 100, y + 145);


        // back button
        g2d.setColor(color1);
        g2d.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Back", backButton.x + 15 , backButton.y + 35);
        g2d.draw(backButton);



    }

    public void drawPaused(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("Century", Font.BOLD, 50);
        g2d.setFont(font);
        g2d.setColor(Color.RED);
        g2d.drawString("PAUSED", GameWorld.SCREEN_W/2 - 95, GameWorld.SCREEN_H/2 );

    }


}
