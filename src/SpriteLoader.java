import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class SpriteLoader {

    private BufferedImage spriteSheet;
    private int TILE_SIZE = 62;

    public SpriteLoader(File file){

        spriteSheet = null;
        try{

            spriteSheet = read(file);

        }
        catch(IOException e){

            System.out.println("Image spritesheet not found");

        }
    }

    public BufferedImage getSprite(int x, int y){

        return spriteSheet.getSubimage(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }



}
