/**
 * Created by Kaś on 19.01.2017.
 */
//instructions to paint rectangles
public class Instr {
    private int startX = 0, startY = 0, width = 0, height = 0;
    private int canvasWidth = 0, canvasHeight = 0;


    public Instr(int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public Instr (int startX, int startY, int width, int height){
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    public void reset (){
        startX = 0;
        startY = 0;
        width = canvasWidth;
        height = canvasHeight;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setRect (int startX, int startY, int width, int height){
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

 /*   public void setInstr (Instr other){
        this.startX = other.getStartX();
        this.startY = other.getStartY();
        this.width = other.getWidth();
        this.height = other.getHeight();
    } */
}
