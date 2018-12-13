import java.awt.*;

public class GameObject {

    private int x;
    private int y;
    private int w;
    private int h;
    private String type;
    private boolean visible;
    private Color color;

    public GameObject(int x, int y, int w, int h, String type, boolean visible, Color color){

        this.x = x;
        this.y = y;
        this.w = w;
        this. h = h;
        this.type = type;
        this.visible = visible;
        this.color = color;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
