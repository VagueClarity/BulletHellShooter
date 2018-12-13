import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {







    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        //  public Rectangle playButton = new Rectangle(GameWorld.SCREEN_W/2 - 50, 400, 100, 50);
        if((mx > Menu.playButton.getX() && mx < Menu.playButton.getX() + Menu.playButton.getWidth()) && GameWorld.state == GameWorld.STATE.MENU){

            // if(my > Menu.playButton.getY() && my < Menu.playButton.getY() + Menu.playButton.getHeight()){


            if(my > 425 && my < 475) {
                GameWorld.state = GameWorld.STATE.GAME;
            }
            //  }
        }

        if((mx > Menu.helpButton.getX() && mx < Menu.helpButton.getX() + Menu.helpButton.getWidth())&& GameWorld.state == GameWorld.STATE.MENU){

            // if(my > Menu.playButton.getY() && my < Menu.playButton.getY() + Menu.playButton.getHeight()){


            if(my > Menu.helpButton.getY() + 25 && my < Menu.helpButton.getY() + Menu.helpButton.getHeight() +25) {
                GameWorld.state = GameWorld.STATE.HELP;
            }
            //  }
        }

        if((mx > Menu.backButton.getX() && mx < Menu.backButton.getX() + Menu.backButton.getWidth())&& GameWorld.state == GameWorld.STATE.HELP){

            // if(my > Menu.playButton.getY() && my < Menu.playButton.getY() + Menu.playButton.getHeight()){


            if(my > Menu.backButton.getY() + 25 && my < Menu.backButton.getY() + Menu.backButton.getHeight() +25) {
                GameWorld.state = GameWorld.STATE.MENU;
            }
            //  }
        }




    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        System.out.println("entered");

        if((mx > Menu.playButton.getX() && mx < Menu.playButton.getX() + Menu.playButton.getWidth())){

            // if(my > Menu.playButton.getY() && my < Menu.playButton.getY() + Menu.playButton.getHeight()){


            if(my > 425 && my < 475) {
                System.out.println("Entered");
                Menu.setColor1(new Color(38, 38, 38));
                //  }
            }
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        System.out.println("Exited");
        if((mx > Menu.playButton.getX() && mx < Menu.playButton.getX() + Menu.playButton.getWidth())){

            // if(my > Menu.playButton.getY() && my < Menu.playButton.getY() + Menu.playButton.getHeight()){


            if(my > 425 && my < 475) {
                System.out.println("Exited");
                Menu.setColor1(new Color(74, 78, 84));
                //  }
            }
        }

    }
}
