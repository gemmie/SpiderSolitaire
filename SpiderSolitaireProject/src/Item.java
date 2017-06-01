import java.awt.*;

/**
 * Created by Kaś on 19.01.2017.
 */
//item in menu button
public class Item {
    private int startX = 0, startY =0;
    private String txt = "";
    private boolean itemMouseIsOver = false, previousItemIsMouseOver = false, lineSep = false;

    private final Color txtColour = new Color(0,0,0);
    private final Color backgroundColour = new Color(255,255,255);
    private final Color highlightColour = new Color(204,207,217);
    private final Font font = new Font("Arial", Font.PLAIN, 13);
    private final int width = 210, height = 30;

    public Item (int startX, int startY, String txt, boolean lineSep){
        this.startX = startX;
        this.startY = startY;
        this.txt = txt;
        this.lineSep = lineSep;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getHeight() {
        return height;
    }

    public void removeMouseOver (){
        itemMouseIsOver = false;
        previousItemIsMouseOver = false;
    }

    public boolean isMouseOver (int mouseX, int mouseY){
        previousItemIsMouseOver = itemMouseIsOver;
        if((mouseX > startX && mouseX < (startX + width)) && (mouseY > startY && mouseY < (startY + height)))
            itemMouseIsOver = true;
        else
            itemMouseIsOver = false;
        return itemMouseIsOver;
    }

    public void drawMenuItem (Graphics gra){
        if(itemMouseIsOver)
            gra.setColor(highlightColour);
        else
            gra.setColor(backgroundColour);
        gra.fillRect(startX, startY, width, height);
        gra.setColor(txtColour);
        gra.setFont(font);
        gra.drawString(txt, (startX + 5), startY + 17);

        if(lineSep) {
            gra.setColor(highlightColour);
            gra.drawLine(startX + 10, ((startY + height) - 1), (startX + 70), ((startY + height) - 1));
        }
    }
}
