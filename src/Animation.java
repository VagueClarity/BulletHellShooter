import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {

    private int frameCounter;
    private int currentFrame;
    private int frameDelay;
    private int animDirection;
    private ArrayList<Frame> frames = new ArrayList<>();
    private boolean stopped;
    private int totalFrames;

    public Animation(BufferedImage[] frames, int delay){

        this.stopped = true;
        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], delay);
        }
        this.frameCounter = 0;
        this.frameDelay = delay;
        this.animDirection = 1;
        this.totalFrames = frames.length;

    }


    public void update(){
        if (!stopped) {
            frameCounter++;

            if (frameCounter > frameDelay) {
                frameCounter = 0;
                currentFrame += animDirection;

                if (currentFrame > totalFrames - 1) {
                    currentFrame = 0;
                }
                else if (currentFrame < 0) {
                    currentFrame = totalFrames - 1;
                }
            }
        }
        
    }

    public void start() {
        if (!stopped) {
            return;
        }

        if (frames.size() == 0) {
            return;
        }

        stopped = false;
    }

    public void stop() {
        if (frames.size() == 0) {
            return;
        }

        stopped = true;
    }

    public void restart() {
        if (frames.size() == 0) {
            return;
        }

        stopped = false;
        currentFrame = 0;
    }

    public void reset() {
        this.stopped = true;
        this.frameCounter = 0;
        this.currentFrame = 0;
    }


    public boolean isStopped() {
        return stopped;
    }

    public void addFrame(BufferedImage frame, int duration){

        if (duration <= 0) {
            System.err.println("Invalid duration: " + duration);
            throw new RuntimeException("Invalid duration: " + duration);
        }
        frames.add(new Frame(frame, duration));
        currentFrame = 0;


    }


    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public BufferedImage getSprite(){

        return frames.get(currentFrame).getFrame();
    }

}
