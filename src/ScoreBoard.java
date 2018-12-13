import java.awt.*;

public class ScoreBoard {

    private int score;

    private Player player;
    private GameWorld gw;

    public ScoreBoard(GameWorld gw){

        this.score = 0;
        this.gw = gw;
    }

    public void addScore(int amount){

        this.score += amount;

    }

    public void resetScore(){
        score = 0;
    }
    public void draw(Graphics g){


        Font font = new Font("Century", Font.BOLD, 20);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setFont(font);
        g2d.drawString("Score: " + score, (gw.SCREEN_W * 3) /4 , gw.SCREEN_H - 50);

    }

    public int getScore() {
        return score;
    }
}
